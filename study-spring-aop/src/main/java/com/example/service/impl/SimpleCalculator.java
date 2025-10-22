package com.example.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.aop.Profile;
import com.example.service.Calculator;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Service
@Lazy
public class SimpleCalculator implements Calculator {

	@Override
	public double add(double x, double y) {
		return x + y;
	}

	@Override
	public double sub(double x, double y) {
		return x - y;
	}

	@Override
	@Profile(TimeUnit.MICROSECONDS)
	public double mul(double x, double y) {
		return x * y;
	}

	@Override
	@Profile(TimeUnit.MICROSECONDS)
	public double div(double x, double y) {
		if (y == 0.0)
			throw new IllegalArgumentException("divisor cannot be zero!");
		return x / y;
	}

}
