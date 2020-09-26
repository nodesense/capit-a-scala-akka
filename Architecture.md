Akka Http Actors
Akka Streams
Kafka - AMQP
Play Framework
Akka Actors

Clustered Environment

Http Actor  - Cluster 1 - frontend
    Post 
        { Order (....) 
        
            router1.forward(order)
            
        }
            
    implicit  val system = ActorSystem("ClusterSystem", config1)
    
            
Akka Actors - Cluster 2 - reusable, can serve http request, gRPC, SOAP based api
    OrderProcessingActor - backend - will register with frontend
    
        implicit  val system = ActorSystem("ClusterSystem", config2)
        
       Future[result] =  Source[] (order)
        .mapAsync(1) (orderValidateActor ? order) - 1 ms, 5 seconds, 20 seconds
        .mapAsymc(1) (balancetransfer ? order)
        bank account
        fraud check
        invoiceActor
        ...
        
        future.onComplete {
        }
        
        future.mapTo[Invoice]
        
Akka Stream Analytics 
    Cluster
    
    implicit  val system = ActorSystem("ClusterSystem", config3)
