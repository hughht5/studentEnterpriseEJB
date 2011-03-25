/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



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

/**
 *
 * @author mm336
 */
public class TestEJB {

    UserSessionRemote userSession;

    String host;
    String port;

    public TestEJB() {
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
            System.out.println("Initialising context");
            Context c = new InitialContext(props);
            System.out.println("Context initialised");
            String jndiName = "java:global/studentTracker/UserSession!" + "ejb.sessions.UserSessionRemote";
            return (UserSessionRemote) c.lookup(jndiName);
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

    @Test
    public void testAssignBookCopyToLibrary() {
        if(userSession.checkLogin("user", "pass"))
        {
            Assert.assertTrue(true);
        }
        else
        {
            System.out.println("false");
        }
    }
}
