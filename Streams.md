Stream - unbounded collection. Continuos, event

Akka Stream internally uses Akka Actor - reduce the complexities in actor model

Not a batch processing, process single record
Atomic transaction - efficient
backpressure - if the bottom layer, can handle 1 msg per second, push only one message
Source <-- input starts [console input, azure event hub, awa kinesis, std in, socket, db, amqp, mqtt, kafka,....api calls]
  Processors
  transformation
  actions
  mapTo to actors
  ....
Sink <-- output [console output, std out socket,azure event hub, awa kinesis, db, kafka, ...mqtt, amqp]