/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import ejb.sessions.StudentSessionRemote;
import ejb.entities.Users;
import java.sql.Date;
import ejb.entities.Student;
import junit.framework.Assert;
import ejb.sessions.UserSessionRemote;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.net.*;

/**
 *
 * @author mm336
 */
public class TestEJB {

    UserSessionRemote userSession;
    StudentSessionRemote studentSession;

    String host;
    String port;

    public TestEJB() {

        //Get the hostname (bluexxx.ex.ac.uk)
        try {
            InetAddress addr = InetAddress.getLocalHost();
            byte[] ipAddr = addr.getAddress();
            String hostname = addr.getHostName();
            System.out.println("hostname="+hostname);

            int hostIndex = hostname.indexOf(".");

            host = hostname.substring(0, hostIndex);
            System.out.println("hostname="+hostname);

        } catch (UnknownHostException e) {
        }

        //Set the IIOP Listener port for the Glassfish server
        //Do this by:
            //In Netbeans click: Services -> Servers -> Glassfish, rightclick 'Admin Console'
            //On the management console: ORB -> IIOP Listeners -> 'orb-listener-1'
            //Change port to 20911
        port = "20911";

        Properties props = new Properties();
        props.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        props.put("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        props.put("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        props.put("org.omg.CORBA.ORBInitialHost", host+".ex.ac.uk");
        props.put("org.omg.CORBA.ORBInitialPort", port);

        userSession = lookupUserSessionRemote(props);
        studentSession = lookupStudentSessionRemote(props);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
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

    @Test
    public void testCheckLogin() {
        if(userSession.checkLogin("user", "pass"))
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertFalse(false);
        }
    }

    @Test
    public void testAddStudentUser()
    {
        boolean result = false;
        java.sql.Date dob = java.sql.Date.valueOf("1980-06-28"); //yyyy-mm-dd

        //studentSession.addStudent(10, 20, "xzy101", "Joe Bloggs", dob);

        Student student = new Student();
        student.setCandidateNumber(1);
        student.setStudentNumber(2);
        student.setEmailID("xyz101");
        student.setName("Joe Bloggs");
        student.setDateOfBirth(dob);

        result = userSession.addStudentUser(student, "password");

        System.out.println("testAddStudentUser: Added student "+student.getName()+" ("+student.getEmailID()+")");
        Assert.assertTrue(result);

    } 
}
