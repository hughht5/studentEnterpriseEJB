/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package studenttrackerclient;

import ejb.sessions.StudentSessionRemote;
import ejb.sessions.UserSessionRemote;

import ejb.entities.Users;
import ejb.entities.Student;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.sql.Date;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 *
 * @author mm336
 */
public class Sessions {

    private static UserSessionRemote userSession;
    private static StudentSessionRemote studentSession;

    String host;
    String port;

    public Sessions() {

        //Get the hostname (bluexxx.ex.ac.uk)
        try {
            InetAddress addr = InetAddress.getLocalHost();
            host = addr.getHostName();
        } catch (UnknownHostException e) {
            System.out.println("Could not get the host name");
        }

        //Set the IIOP Listener port for the Glassfish server
        //Do this by:
            //In Netbeans click: Services -> Servers -> Glassfish, rightclick 'Admin Console'
            //On the management console: ORB -> IIOP Listeners -> 'orb-listener-1'
            //Change port to 20911
        port = "20911";

        System.out.println("Connecting to glassfish on "+host+":"+port);

        Properties props = new Properties();
        props.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        props.put("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        props.put("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        props.put("org.omg.CORBA.ORBInitialHost", host);
        props.put("org.omg.CORBA.ORBInitialPort", port);

        userSession = lookupUserSessionRemote(props);
        studentSession = lookupStudentSessionRemote(props);
    }

    private UserSessionRemote lookupUserSessionRemote(Properties props) {
        try {
            Context c = new InitialContext(props);
            String jndiName = "java:global/studentTracker/UserSession!" + "ejb.sessions.UserSessionRemote";
            return (UserSessionRemote) c.lookup(jndiName);
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

    private StudentSessionRemote lookupStudentSessionRemote(Properties props) {
        try {
            Context c = new InitialContext(props);
            String jndiName = "java:global/studentTracker/StudentSession!" + "ejb.sessions.StudentSessionRemote";
            return (StudentSessionRemote) c.lookup(jndiName);
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

    public static StudentSessionRemote studentSession()
    {
        return studentSession;
    }

    public static UserSessionRemote userSession()
    {
        return userSession;
    }

}
