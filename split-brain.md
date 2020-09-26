    akka.cluster.downing-provider-class = "akka.cluster.sbr.SplitBrainResolverProvider"


Cluster Events
    message across cluster

    split brain - was a commercial future, now is free
    when a cluster node is assumed to be dead before annoncing
    30 seconds

Network

 System1
        - MB failed - chance of recover is 0 percent.
 System2
        - The Actor/the process failed
        - reovery, kubernetes/service watch dog- restart the process
        - recovery take 5 seconds
 System3
        - Network outage
        - 2 seconds - gateway/lan device/switch had failed - 
 System4
        - HW failed
        -- program moved to system5 - 30 seconds
        
        
Cluster - HA
    Workers across multiple systems - 1 system fails, other system still available
    
Load Balancing
    How to handle a lot of messages?
        Routers
            round robin
            based on low mail box queue
            ...
            
            10000 msg per second
            
            router -> forward to worker 1 on system 10 ]
            router -> forward to worker 2 on system 5 ]

            