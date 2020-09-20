Fold,
Reduce,
Flattening
Partially applied functions
tail recursion



https://www.slideshare.net/hermannhueck/whats-new-in-scala-213




Classical OOP MODEL

class System {
    State state;
    
    void doSomething()
    void doSomethingElse1(Transaction k)..
  
    void doSomethingElse1(Messsage a)..
    ...
    ...
    
    void doSomethingElse100(Command b)..
}

Caller 

sys = new System()
sys.doSomething() // talk to object
sys.doSomething1() // talk to object
sys.doSomething() // talk to object
sys.doSomething() // talk to object
sys.doSomething() // talk to object
sys.doSomething1000() // talk to object


--


=== ACTOR WAY ===

class Actor {
    Queue<Message> mailBox;
    
    onReceive(msh: Message) { // calback, called evenever new message come
        case Message1
        Case Message2.
        
        
        .......
    }
    
}

caller , never invoke obj by function, instad only send message

actor.mailBox.push(new Message1())
actor.mailBox.push(new Message1())
actor.mailBox.push(new Message3())
actor.mailBox.push(new Message1())