package puji.wahyono.PujiMvn;
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
        	SocketConnector sc=new SocketConnector();
        	sc.setPort(2101);
        	s.setConnectors(new Connector[] {sc});
        	WebAppContext wac=new WebAppContext();
        	wac.setContextPath("/");
        	wac.setWar("src/webapp");
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
}
