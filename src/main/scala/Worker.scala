import akka.actor.Actor

/**
 * Worker Actor
 */
class Worker extends Actor {
  def calculatePiFor(start:Int, nrOfElements:Int) = {
    (for (k <- start until nrOfElements) yield (4 * math.pow(-1, k) / (2 * k + 1))).sum
  }

  def receive = {
    case Work(start, nrOfElements) => sender ! Result(calculatePiFor(start, nrOfElements)) // perform the work
  }
}
