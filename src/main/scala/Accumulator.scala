import akka.actor.{ActorRef, Props, Actor}
import akka.event.Logging
import akka.routing.{FromConfig}
import scala.concurrent.duration._
/**
 * Accumulator Actor to gather the results of the worker actors
 */
class Accumulator(nrOfMessages: Int, nrOfElements: Int, listener: ActorRef) extends Actor{

  val workerRouter = context.actorOf(Props[Worker].withRouter(FromConfig()), name = "workerRouter")
  val log = Logging(context.system, this)
  var pi: Double = _
  var nrOfResults: Int = _
  val start: Long = System.currentTimeMillis


  def receive = {
    case Calculate => for (i <- 0 until nrOfMessages) workerRouter ! Work(i * nrOfElements, nrOfElements)
    case Result(value) => {
      pi += value
      nrOfResults += 1
      if (nrOfResults == nrOfMessages) {
        // Send the result to the listener
        log.info("returning result")
        listener ! PiApproximation(pi, duration = (System.currentTimeMillis - start).millis)
        // Stops this actor and all its supervised children
        //context.stop(self)
      }
    }

  }


}
