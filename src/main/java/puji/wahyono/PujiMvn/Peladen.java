/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puji.wahyono.PujiMvn;

import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author ashura
 */
public class Peladen {
    private org.eclipse.jetty.server.Server s;
    private org.eclipse.jetty.servlet.ServletContextHandler ctx;
    private String host2;
    private int port;

    public Peladen(String host2, int port) {
        this.host2 = host2;
        this.port = port;
        s=new org.eclipse.jetty.server.Server();
    }

    public void start() throws URISyntaxException, Exception {
        genConnector();
        homeBase();
        enableJSP();
        s.start();
        s.join();
    }

    private void genConnector() {
        org.eclipse.jetty.server.ServerConnector sc=new org.eclipse.jetty.server.ServerConnector(s),
                sc2=new org.eclipse.jetty.server.ServerConnector(s);
        sc.setHost("localhost");
        sc2.setHost(host2);
        sc.setPort(port);
        sc2.setPort(port);
        s.setConnectors(new org.eclipse.jetty.server.Connector[]{sc,
            sc2});
    }

    private void homeBase() throws URISyntaxException {
        URI baseURI=getWebContextURI();
        org.eclipse.jetty.webapp.Configuration.ClassList cl=org.eclipse.jetty.webapp.Configuration.ClassList.setServerDefault(s);
        cl.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", 
                "org.eclipse.jetty.annotations.AnnotationConfiguration");
        ctx=new org.eclipse.jetty.servlet.ServletContextHandler(org.eclipse.jetty.servlet.ServletContextHandler.SESSIONS);
        ctx.setContextPath("/");
        ctx.setResourceBase(baseURI.toASCIIString());
    }

    private URI getWebContextURI() throws URISyntaxException {
        java.net.URL u=this.getClass().getResource("/webapp/");
        return u.toURI();
    }

    private void enableJSP() {
        positioning();
    }

    private void positioning() {
        java.io.File tmp=new java.io.File(System.getProperty("java.io.tmpdir")),emp
                =new java.io.File(tmp, "PujiMVN");
        if(!emp.exists())emp.mkdirs();
        ctx.setAttribute("javax.servlet.context.tempdir", emp);
    }
}
