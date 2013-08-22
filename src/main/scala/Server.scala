import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

/**
 * Created with IntelliJ IDEA.
 * User: rguderlei
 * Date: 22.08.13
 * Time: 17:15
 * To change this template use File | Settings | File Templates.
 */
object Server extends App {
  val system = ActorSystem("PiServer", ConfigFactory.load.getConfig("server"))
}
