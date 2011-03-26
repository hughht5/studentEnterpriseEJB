/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client.logon;

import ejb.sessions.StudentSessionRemote;
import ejb.sessions.old_UserSessionRemote;

import ejb.entities.old_Users;
import ejb.entities.Student;

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

    old_UserSessionRemote userSession;
    StudentSessionRemote studentSession;

    String host;
    String port;

    public Sessions(String msg) {
        System.out.print(msg);
        System.out.print("fuck2");
        host = "blue98.ex.ac.uk";
        port = "20911";
System.out.print("fuck3");
        Properties props = new Properties();
        System.out.print("fuck4");
        props.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        props.put("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        props.put("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        props.put("org.omg.CORBA.ORBInitialHost", host);
        props.put("org.omg.CORBA.ORBInitialPort", port);
System.out.print("fuck5");
        userSession = lookupUserSessionRemote(props);
        System.out.print("fuck6");

        studentSession = lookupStudentSessionRemote(props);
        System.out.print("fuck7");

    }

    private old_UserSessionRemote lookupUserSessionRemote(Properties props) {
        try {
            Context c = new InitialContext(props);
            String jndiName = "java:global/studentTracker/UserSession!" + "ejb.sessions.UserSessionRemote";
            return (old_UserSessionRemote) c.lookup(jndiName);
        } catch (NamingException ne) {
            System.out.print("fuck200");
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

    public StudentSessionRemote studentSession()
    {
        return studentSession;
    }

    public old_UserSessionRemote userSession()
    {
        return userSession;
    }

}
