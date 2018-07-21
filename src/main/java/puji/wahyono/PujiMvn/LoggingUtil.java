/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puji.wahyono.PujiMvn;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 *
 * @author ashura
 */
public class LoggingUtil {
    public static void config() {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URL url = cl.getResource("logging.properties");
        if(url!=null)try{
            java.io.InputStream i=url.openStream();
            LogManager.getLogManager().readConfiguration(i);
            i.close();
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(LoggingUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
