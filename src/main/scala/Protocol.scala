import scala.concurrent.duration.Duration

/**
 * Case classes used as messages to be passed to the different actors
 */
sealed trait PiMessage
case object Calculate extends PiMessage
case class Work(start: Int, nrOfElements: Int) extends PiMessage
case class Result(value: Double) extends PiMessage
case class PiApproximation(pi: Double, duration: Duration)

