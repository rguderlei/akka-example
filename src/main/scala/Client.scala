import akka.actor.{Props, ActorSystem, Actor}
import com.typesafe.config.ConfigFactory

/**
 * client class to start the computation
 */
class ResultListener extends Actor{

    def receive = {
      case PiApproximation(pi, duration) =>
        println("\n\tPi approximation: \t\t%s\n\tCalculation time: \t%s"
        .format(pi, duration))
      context.system.shutdown()
    }
}


object Client extends App {
  calculate(nrOfElements = 10000, nrOfMessages = 10000)

  // actors and messages ...

  def calculate(nrOfElements: Int, nrOfMessages: Int) {
    // Create an Akka system
    val system = ActorSystem("PiClient", ConfigFactory.load.getConfig("client"))

    // create the result listener, which will print the result and shutdown the system
    val listener = system.actorOf(Props[ResultListener], name = "listener")

    // create the master
    val master = system.actorOf(Props(new Accumulator(nrOfMessages, nrOfElements, listener)),
    name = "master")

    // start the calculation
    master ! Calculate
  }
}


