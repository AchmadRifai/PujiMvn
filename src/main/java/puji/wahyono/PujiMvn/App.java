package puji.wahyono.PujiMvn;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.webapp.Configuration;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        LoggingUtil.config();
    }

    private static String thisIP() {
        String s="127.0.0.1";
        try {
            java.net.InetAddress ia=java.net.Inet4Address.getLocalHost();
            s=ia.getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
}
