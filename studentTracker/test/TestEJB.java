/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import ejb.entities.Staff;
import ejb.sessions.StudentSessionRemote;
import ejb.entities.Users;
import java.sql.Date;
import ejb.entities.Student;
import ejb.sessions.StaffSessionRemote;
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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mm336
 */
public class TestEJB {

    @PersistenceContext
    private EntityManager manager;

    private UserSessionRemote userSession;
    private StudentSessionRemote studentSession;
    private StaffSessionRemote staffSession;

    private String host;
    private String port;

    public TestEJB() {
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

        Properties props = new Properties();
        props.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        props.put("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        props.put("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        props.put("org.omg.CORBA.ORBInitialHost", host);
        props.put("org.omg.CORBA.ORBInitialPort", port);

        userSession = lookupUserSessionRemote(props);
        studentSession = lookupStudentSessionRemote(props);
        staffSession = lookupStaffSessionRemote(props);
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

    private StaffSessionRemote lookupStaffSessionRemote(Properties props) {
        try {
            Context c = new InitialContext(props);
            String jndiName = "java:global/studentTracker/StaffSession!" + "ejb.sessions.StaffSessionRemote";
            return (StaffSessionRemote) c.lookup(jndiName);
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
    public void testAddStudent()
    {
        try {
            java.sql.Date dob = java.sql.Date.valueOf("1980-06-28"); //yyyy-mm-dd
            studentSession.addStudent(10, 20, "abc101", "Micmo1", dob);
            studentSession.addStudent(10, 20, "abc102", "Micmo2", dob);
            studentSession.addStudent(10, 20, "abc103", "Micmo3", dob);
            studentSession.addStudent(10, 20, "abc104", "Micmo4", dob);
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.assertTrue("testAddStudent() ERROR: "+e, false);
        }
    }

    @Test
    public void getStudentByID()
    {
        String emailID = "abc103";

        Student student = studentSession.getStudentByEmailID(emailID);

        if(student!=null)
            Assert.assertTrue(true);
        else
            Assert.assertTrue(false);

        System.out.println("Student with EmailID "+emailID+" = "+student.getName());
    }

//    @Test
    public void testAddStudentUserAccount(){
        Boolean result = false;

        Student student = studentSession.getStudentByEmailID("abc103"); //Micmo3
        result = userSession.addStudentUser(student, "password");

        System.out.println("testAddStudentUser: Added student "+student.getName()+" ("+student.getEmailID()+")");
        Assert.assertTrue(result);
    }

    @Test
    public void testAddStaff()
    {
        try {
            staffSession.addStaff("tut01", "Mr Tutor 1", "01");
            staffSession.addStaff("tut02", "Mr Tutor 2", "02");
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.assertTrue("testAddStudent() ERROR: "+e, false);
        }
    }

    @Test
    public void getStaffByID()
    {
        String emailID = "tut01";

        Staff staff = staffSession.getStaffByEmailID(emailID);

        if(staff!=null)
            Assert.assertTrue(true);
        else
            Assert.assertTrue(false);

        System.out.println("Staff with EmailID "+emailID+" = "+staff.getName());
    }

//    @Test
    public void testAddStaffUserAccount(){
        Boolean result = false;

        Staff staff = staffSession.getStaffByEmailID("tut02"); //Mr Tutor 2
        result = userSession.addStaffUser(staff, "password", false);

        System.out.println("testAddStaffUser: Added staff "+staff.getName()+" ("+staff.getEmailID()+")");
        Assert.assertTrue(result);
    }

    @Test
    public void testAddTutorToStudent(){
        Student student = studentSession.getStudentByEmailID("abc102"); //Micmo2
        Staff tutor = staffSession.getStaffByEmailID("tut02"); //MrTutor2

        System.out.println("testAddTutorToStudent()");
        System.out.println("student: "+student.getEmailID());
        System.out.println("tutor: "+tutor.getEmailID());



        studentSession.addTutor(student, tutor);
    }
}
