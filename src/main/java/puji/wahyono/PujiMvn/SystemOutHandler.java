/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puji.wahyono.PujiMvn;

import java.util.logging.LogRecord;

/**
 *
 * @author ashura
 */
public class SystemOutHandler extends java.util.logging.Handler{
    @Override
    public void publish(LogRecord record) {
        StringBuilder buf = new StringBuilder();
        buf.append("[").append(record.getLevel().getName()).append("] ");
        String logname = record.getLoggerName();
        int idx = logname.lastIndexOf('.');
        if (idx > 0)
        {
            logname = logname.substring(idx + 1);
        }
        buf.append(logname);
        buf.append(": ");
        buf.append(record.getMessage());

        System.out.println(buf.toString());
        if (record.getThrown() != null)
        {
            record.getThrown().printStackTrace(System.out);
        }
    }

    @Override
    public void flush() {
        //
    }

    @Override
    public void close() throws SecurityException {
        //
    }
}
