import akka.actor.Actor
import akka.event.Logging
/**
 * Worker Actor
 */
class Worker extends Actor {
  val log = Logging(context.system, this)
  def calculatePiFor(start:Int, nrOfElements:Int) = {
    log.debug("working")
    (start until nrOfElements).map(k => 4 * math.pow(-1, k) / (2 * k + 1)).sum
  }

  def receive = {
    case Work(start, nrOfElements) => sender ! Result(calculatePiFor(start, nrOfElements)) // perform the work
  }
}
