package workshop.example

import workshop.models.Order

// Seq
// Array in other programming language, indexed seq, Vector, Array, String
    // Memory, problem,
    // reserve block of memory, 1000 items
    // when collection grows beyond 1000?
    // create new memory of 2000 items
    // copy the references/values to new location
    // create new memory of 3000 items
    // copy the references/values to new location

  // Access
     // a[0], a(0) - Big O (1)

// LinkedLink in DS, linear seq
//   struct Node { value, next: Node }
    // not  continuos by memory
    // grow on need basic

    // {node} ==> {node} ==> {node}

  // problem: Access at index location
   //  a(10) Big O - O(n)





// Seq: List of elements
// Map - Dictionary
// Set - {collection of elements, not nessary to follow seq}, Sorted, No Duplicate

// Immutable - Scala is known for it. Rich in immutable collection
             // collection can't be changed, cannot new element, cannot update existing one
            // cannot delete

// Mutable - change, append, update, delete etc
object CollectionsListExample extends  App {
  // List is immutable, default package
  val brands: List[String] = List("Apple", "Google", "Samsung")
  val orders: List[Order] = List( Order(1, 1, 1000),
                                  Order(2, 34, 2000),
                                  Order(3, 340, 3000),
                                  Order(4, 340, 4000),
                                  Order(5, 340, 5000)
                                  )

  println("Head ", brands.head); // Apple
  println("Tail ", brands.tail); // List(Google, Samsung)
  println("length", brands.length)
  println("isEmpty", brands.isEmpty)

  println("Brands ", brands)

  // right associativeity :: with list
  val nextBrands =  "Moto" :: "Jio"  :: Nil

  println("nextBrands", nextBrands)

  // list is put into another list, not CONCAT
  // List(List(Apple, Google, Samsung), Moto, Jio)
  println( brands :: nextBrands)

  // List(Apple, Google, Samsung, Moto, Jio)
  val newBrands = brands ::: nextBrands
  println( newBrands)


  // add item

  println("take 3", orders.take(3))
  // last 2
  println("takeRight 2", orders.takeRight(2))

  // split the data, >= 0 in one partition
  // rest in another partition
  val partitionTuple = orders.partition( order => order.amount >= 3000 )
  println(partitionTuple)

  partitionTuple._1 // contains all the matching values >= 3000
  partitionTuple._2 // contains all left over value


  val discountOrders = orders.map ( order => {
                                        Order(order.id, order.customerId,  order.amount * .9)
                                        })

  println("Orders", orders)
  println("discountOrders", discountOrders)

  // convert this code to foreach
  for (order <- orders) {
    println("Order data", order)
  }

}
