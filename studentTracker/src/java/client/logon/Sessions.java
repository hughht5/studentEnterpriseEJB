/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client.logon;

import ejb.sessions.UserSessionRemote;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author mm336
 */
public class Sessions {

    private UserSessionRemote userSession;

    String host;
    String port;

    public Sessions() {
        host = "blue98.ex.ac.uk";
        port = "20911";

        Properties props = new Properties();
        props.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        props.put("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        props.put("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        props.put("org.omg.CORBA.ORBInitialHost", host);
        props.put("org.omg.CORBA.ORBInitialPort", port);

        userSession = lookupUserSessionRemote(props);

    }

    private UserSessionRemote lookupUserSessionRemote(Properties props) {
        try {
            System.out.println("Initialising context");
            Context c = new InitialContext(props);
            System.out.println("Context initialised");
            String jndiName = "java:global/studentTracker/UserSession!" + "ejb.sessions.UserSessionRemote";
            return (UserSessionRemote) c.lookup(jndiName);
        } catch (NamingException ne) {
            //throw new RuntimeException(ne);
            ne.printStackTrace();
            return null;
        }
    }

    public UserSessionRemote userSession()
    {
        return userSession;
    }


}
