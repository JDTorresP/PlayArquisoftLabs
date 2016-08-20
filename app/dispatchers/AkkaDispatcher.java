package dispatchers;

/**
 * Created by jd.torres11 on 20/08/2016.
 */
import akka.dispatch.MessageDispatcher;
import play.libs.Akka;
public class AkkaDispatcher {
    public static MessageDispatcher jdbcDispatcher =  Akka.system().dispatchers().lookup("contexts.jdbc-dispatcher");
}


