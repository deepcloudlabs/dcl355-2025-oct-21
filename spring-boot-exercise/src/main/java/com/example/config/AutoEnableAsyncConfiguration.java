package com.example.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncAnnotationBeanPostProcessor;
import org.springframework.util.ClassUtils;

//@Configuration(proxyBeanMethods = false)
public class AutoEnableAsyncConfiguration {

  @Bean
  static BeanDefinitionRegistryPostProcessor autoEnableAsyncBpp() {
    return new AutoEnableAsyncBpp();
  }

  static class AutoEnableAsyncBpp implements BeanDefinitionRegistryPostProcessor,
      PriorityOrdered {

    private final MetadataReaderFactory mrf =
        new CachingMetadataReaderFactory(new PathMatchingResourcePatternResolver());

    @Override
    public int getOrder() { return Ordered.HIGHEST_PRECEDENCE; }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
        throws BeansException {

      // If already enabled (e.g., via @EnableAsync), do nothing
      String bppBeanName = "";//AnnotationConfigUtils.ASYNC_ANNOTATION_PROCESSOR_BEAN_NAME;
      if (registry.containsBeanDefinition(bppBeanName)) return;

      if (hasAnyAsyncAnnotatedBean(registry)) {
        RootBeanDefinition def = new RootBeanDefinition(AsyncAnnotationBeanPostProcessor.class);
        //def.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        registry.registerBeanDefinition(bppBeanName, def);
      }
    }

    private boolean hasAnyAsyncAnnotatedBean(BeanDefinitionRegistry registry) {
      for (String name : registry.getBeanDefinitionNames()) {
        BeanDefinition bd = registry.getBeanDefinition(name);
        String className = bd.getBeanClassName();
        if (className == null) continue; // factory-bean or generated; skip
        try {
          MetadataReader mr = mrf.getMetadataReader(className);
          AnnotationMetadata am = mr.getAnnotationMetadata();
          // class-level @Async or meta-annotation
          if (am.hasAnnotation(Async.class.getName()) ||
              am.hasMetaAnnotation(Async.class.getName())) return true;
          // method-level @Async
          if (!am.getAnnotatedMethods(Async.class.getName()).isEmpty()) return true;
        } catch (Throwable ignored) {
          // Fallback via reflection if metadata read failed
          try {
            Class<?> type = ClassUtils.forName(className, null);
            if (type.isAnnotationPresent(Async.class)) return true;
            for (var m : type.getDeclaredMethods())
              if (m.isAnnotationPresent(Async.class)) return true;
          } catch (Throwable ignored2) { /* best-effort */ }
        }
      }
      return false;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
        throws BeansException {
      // no-op
    }
  }
}
