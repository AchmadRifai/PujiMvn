package puji.wahyono.PujiMvn;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Server s=null;try {
        	s=new Server();
        	SocketConnector sc=new SocketConnector(),sc2=new SocketConnector();
                String ip=thisIP();
                System.out.println(ip);
                sc.setHost(ip);
        	sc.setPort(2101);
                sc2.setPort(2101);
                sc2.setHost("localhost");
        	s.setConnectors(new Connector[] {sc,sc2});
        	WebAppContext wac=new WebAppContext();
        	wac.setContextPath("/");
        	wac.setWar("src/main/webapp");
        	s.setHandler(wac);
        	s.start();
        }catch(Exception e) {
        	if(s!=null)
				try {
					s.stop();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        }
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
