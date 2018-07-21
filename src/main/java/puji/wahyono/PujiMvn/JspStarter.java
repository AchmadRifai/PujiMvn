/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puji.wahyono.PujiMvn;

import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 *
 * @author ashura
 */
public class JspStarter extends org.eclipse.jetty.util.component.AbstractLifeCycle 
        implements ServletContextHandler.ServletContainerInitializerCaller{
    private ServletContextHandler ctx;
    private org.eclipse.jetty.apache.jsp.JettyJasperInitializer init;

    public JspStarter(ServletContextHandler ctx) {
        this.ctx = ctx;
        init=new org.eclipse.jetty.apache.jsp.JettyJasperInitializer();
        this.ctx.setAttribute("org.apache.tomcat.JarScanner", new org.apache.tomcat.util.scan.StandardJarScanner());
    }

    @Override
    protected void doStart() throws Exception {
        ClassLoader old = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(ctx.getClassLoader());
        try{
            init.onStartup(null, ctx.getServletContext());
            super.doStart();
        }finally{
            Thread.currentThread().setContextClassLoader(old);
        }
    }
}
