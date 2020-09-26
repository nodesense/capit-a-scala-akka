
Seperation of concerns

# Unit Test Actor

class OrderActor {

state {
}

receive (Order ==>  {
    state update
 )


 val orderAcor = sytem.actorOf[..., """]
 orderAcor ! order1 // what you try to test / system testing
 
 expectMsg(order1)
 
 
 orderActor = new OrderActor.receive(Order(....))
 expect(orderActor.state === 100)
 
 
 
 # Monad
 
 identity
 bind
 flatMap 
 
 
 # Macro example
 
 onTimeCompilation
 
 def processOrder(T :< OrderProcessing)  = macro  {...}
 
 processOrder(order)
    .map ()
    .filter(..)
    
    
 processOrder(retailOrder)
    .map ()
    .filter(..)