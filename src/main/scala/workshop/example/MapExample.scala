package workshop.example

import scala.collection.mutable.{Map, HashMap}

object MapExample  extends  App {
  // Map is mutable, since import from mutable collection
  var map = Map[String, Int] ( ("iPhone", 50000),
                               "Galaxy" -> 40000 )

  map += "Moto X" -> 20000
  map += Tuple2("Moto 5",  25000)
  map.put("Redme", 15000)
  map.get("Moto 5")
  map -= ("Redme")
  map.remove("Redme")

  println(map.keys)
  println(map.contains("iPhone"))
  println(map)

  for (product <- map) {
    println(product._1) // key
    println(product._2) // value
  }

  val partitions = map.partition(t => t._2 <=45000)
  println(partitions)
}
