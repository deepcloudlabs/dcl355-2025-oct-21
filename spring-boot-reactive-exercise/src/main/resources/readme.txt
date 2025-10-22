REST over HTTP -> sync
	sync/async client:
	RestTemplate, AsyncRestTemplate -> no longer available since spring boot 4
	WebClient -> reactive client -> tomcat (since spring boot 4)
	
	server:
	WebFlux -> reactive server -> Netty -> reactive container	 
	
REST over WebSocket
       async -> tomcat -> async container
       reactive -> Netty -> reactive container
     
API:
  semantic versioning
         
Messaging
  Broker: Apache Kafka, Rabbit, RocketMQ, ...
          Cluster -> Scalability
          Kafka -> Sharding/Partitioning
          Partition -> Consumer -> Microservice -> Instance
          Kafka: Producer -> Topic -> Consumers
                 Multicast
                 Availability -> File -> Brokerage Logic
                 Scala -> JVM
                 Actor Model
                 Apache
                 Native Protocol/API
          		 Spring Kafka 
          		 Transactional Messsaging (2PC)
          		        
          Rabbit: Producer -> Exchange -- routing --> Queue
                  Consumer Instances -> Queue -> Load Balancing  
                  Queue -> Memory -> File
                  Replication -> Scalability
                  Low Latency  
                  Erlang -> Erlang VM
                  Actor Model
                  Broadcom
                  AMQP
                  Spring AMQP/RabbitMQ
                  Plugins: MQTT, STOMP, WebSocket -> Online gaming, IoT
                  
           RocketMQ: Alibaba
                  Cloud-Native      
                  Transactional Messsaging (2PC)
                  Delayed Messaging
                  Queue
                  Java
                  Clusterred
                  Native API
                  Spring Boot
                     
  Brokerless: Apache ZeroMQ -> Low Latency
                  C/C++ 
              Peer-to-Peer
              Spring Boot    
  
  Message: Command -> POST, PUT -> Rest over HTTP/WebSocket -> Request/Response
           Event   -> REST over WebSocket, Messaging    
       	
  REST over HTTP -> Textual -> JSON, XML, Plain/Text, CSV, ...
            WebSocket -> Text
                         Binary -> Efficient
  Messaging -> Message -> Text, Binary
  
  JSON -> JSON Schema -> Minor Version  
  Google -> Protocol Buffer -> Schema -> Minor Version
  Apache Avro -> Schema       
  Apache Thrift -> DSL -> RPC  (https://thrift.apache.org/)                  	 
                           
API: 
- Document Based: REST over HTTP: POST, URL
                  JSON -> Resource
                  Client: Desktop, Web, Mobile, ...
                          Resource -> Aggregate
                  GraphQL -> Client -> Schema -> Resource
                    Query -> CQRS -> Read Model
                              
Composite API, Edge Pattern -> Resource 

- RPC -> CNCF -> gRPC -> Protocol Buffer
      	


Consistency Pattern
Transaction Pattern
How manage Transaction in a Reactive MS 
