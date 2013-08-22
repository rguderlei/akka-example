import akka.actor.{Props, ActorSystem, Actor}

/**
 * client class to start the computation
 */
class Client extends Actor{

    def receive = {
      case PiApproximation(pi, duration) ⇒
        println("\n\tPi approximation: \t\t%s\n\tCalculation time: \t%s"
        .format(pi, duration))
      context.system.shutdown()
    }
}


object Pi extends App {
  calculate(nrOfElements = 10000, nrOfMessages = 10000)

  // actors and messages ...

  def calculate(nrOfElements: Int, nrOfMessages: Int) {
    // Create an Akka system
    val system = ActorSystem("PiSystem")

    // create the result listener, which will print the result and shutdown the system
    val listener = system.actorOf(Props[Client], name = "listener")

    // create the master
    val master = system.actorOf(Props(new Accumulator(nrOfMessages, nrOfElements, listener)),
    name = "master")

    // start the calculation
    master ! Calculate
  }
}


