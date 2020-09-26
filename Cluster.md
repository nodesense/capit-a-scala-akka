Cluster

    HA - High Availably
    LB - Load Balancer
    FT - Fault Tolerance
    
Akka Cluster
    started with Akka remoting
    remoting client --> akka server remotely -  deprecated, instead use cluster
    .. cluster

Different Machine

Akka Actor System on Computer 1 hostname1, 2554
Akka Actor System on Computer 2 hostname2, 2554
Akka Actor System on Computer 3 hostname2, 2554

---
Local machine

Akka Actor System on Computer 1 hostname1, 2554
Akka Actor System on Computer 1 hostname1, 2555
Akka Actor System on Computer 1 hostname1, 2556


Gossip Based Membership
Seed - permanent members
Guest/Other nodes - if any guest joins, how it will join cluster?
            Use Seed nodes


Akka Clusters

Gossip - Nodes, they interact each other
         whenever new member added to cluster, its to seed nodes
         then the node gossip to others member up/member down
         
         Heartbeat - continous connection
         
         
Cluster can consist of 100 of akka nodes - system/program

but not all need to be in seed node.

seed node? the one always up, availble for lookup when new member joined

seed nodes are basically allways available to receive connection
3 or 4 seed nodes -

Akka choose the one as the leader

Backend Worker
    when the backend worker started, it should register with front end
    saying I am here, in this cluster [1,]

Front worker

front end worker --> Backend worker

