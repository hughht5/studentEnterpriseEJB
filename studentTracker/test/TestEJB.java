/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

// SessionBeans:
/*
 *
 * Student (add, delete, getById, login, setTutor, enrollOnModule)
 * Staff (add, delete, getById, login, addLecture, getListOfTutees)
 * Assessment (addAss, deleteAss, getAssById, addSub, deleteSub, getSubById,
        getAssAvgMark, getSubMarkByStudent)
 * Module (add, delete, getById, addAss, getModuleAvgMark, getModuleMarkByStudent, getStudentsOnModule)
 * Course (add, delete, getById, addModule)
 *
 */

import ejb.entities.Course;
import ejb.entities.Module;
import ejb.entities.Staff;
import ejb.sessions.StudentSessionRemote;
import ejb.entities.old_Users;
import java.sql.Date;
import ejb.entities.Student;
import ejb.sessions.AssessmentSessionRemote;
import ejb.sessions.CourseSessionRemote;
import ejb.sessions.ModuleSessionRemote;
import ejb.sessions.StaffSessionRemote;
import junit.framework.Assert;
import ejb.sessions.old_UserSessionRemote;
import java.lang.reflect.Array;
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
    private StudentSessionRemote studentSession;
    private StaffSessionRemote staffSession;
    private AssessmentSessionRemote assessmentSession;
    private CourseSessionRemote courseSession;
    private ModuleSessionRemote moduleSession;

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

        studentSession = lookupStudentSessionRemote(props);
        staffSession = lookupStaffSessionRemote(props);
        assessmentSession = lookupAssessmentSessionRemote(props);
        courseSession = lookupCourseSessionRemote(props);
        moduleSession = lookupModuleSessionRemote(props);
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

    private AssessmentSessionRemote lookupAssessmentSessionRemote(Properties props) {
        try {
            Context c = new InitialContext(props);
            String jndiName = "java:global/studentTracker/AssessmentSession!" + "ejb.sessions.AssessmentSessionRemote";
            return (AssessmentSessionRemote) c.lookup(jndiName);
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

    private CourseSessionRemote lookupCourseSessionRemote(Properties props) {
        try {
            Context c = new InitialContext(props);
            String jndiName = "java:global/studentTracker/CourseSession!" + "ejb.sessions.CourseSessionRemote";
            return (CourseSessionRemote) c.lookup(jndiName);
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

    private ModuleSessionRemote lookupModuleSessionRemote(Properties props) {
        try {
            Context c = new InitialContext(props);
            String jndiName = "java:global/studentTracker/ModuleSession!" + "ejb.sessions.ModuleSessionRemote";
            return (ModuleSessionRemote) c.lookup(jndiName);
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

    @Test
    public void testConnection()
    {
        Assert.assertTrue(studentSession!=null && staffSession!=null
                && assessmentSession!=null);
    }

    @Test
    public void STUDENT_AddStudents()
    {
        try {
            java.sql.Date dob = java.sql.Date.valueOf("1980-06-28"); //yyyy-mm-dd
            studentSession.addStudent(10, 20, "abc101", "Micmo1", dob, "password");
            studentSession.addStudent(10, 20, "abc102", "Micmo2", dob, "password");
            studentSession.addStudent(10, 20, "abc103", "Micmo3", dob, "password");
            studentSession.addStudent(10, 20, "abc104", "Micmo4", dob, "password");
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.assertTrue("testAddStudent() ERROR: "+e, false);
        }
    }

    @Test
    public void STUDENT_GetStudentByID()
    {
        String emailID = "abc103";

        Student student = studentSession.getStudentByEmailID(emailID);

        if(student!=null)
            Assert.assertTrue(true);
        else
            Assert.assertTrue("Could not find student with emailID "+emailID, false);
    }

    @Test
    public void STAFF_AddStaff()
    {
        try {
            staffSession.addStaff("tut01", "Mr Tutor 1", "01", "room1", "password", true);
            staffSession.addStaff("tut02", "Mr Tutor 2", "02", "room2", "password", false);
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.assertTrue("testAddStudent() ERROR: "+e, false);
        }
    }

    @Test
    public void STAFF_GetStaffByID()
    {
        String emailID = "tut01";

        Staff staff = staffSession.getStaffByEmailID(emailID);

        Assert.assertNotNull("Could not find staff with emailID "+emailID, staff);
    }

    @Test
    public void LOGIN_CheckStaffLogin() {
        if(staffSession.checkStaffLogin("tut02", "password"))
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertFalse(false);
        }
    }

    @Test
    public void LOGIN_CheckStudentLogin()
    {
        if(studentSession.checkStudentLogin("abc101", "password"))
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertFalse(false);
        }
    }

    @Test
    public void STUDENT_AddTutorToStudent(){
        Student student = studentSession.getStudentByEmailID("abc102"); //Micmo2
        Staff tutor = staffSession.getStaffByEmailID("tut02"); //MrTutor2

        studentSession.addTutor(student, tutor);
    }

    @Test
    public void STUDENT_GetTutorForStudent()
    {
        String studentEmail = "abc102";
        
        Student student = studentSession.getStudentByEmailID(studentEmail); //Micmo2
        Staff tutor = student.getTutor(); //MrTutor2

        //Check not null
        Assert.assertNotNull("Could not find tutor for student emailID "+studentEmail, tutor);

        //Make sure it's the right tutor
        Assert.assertEquals(tutor.getEmailID(), "tut02");

    }

    @Test
    public void COURSE_AddCourses()
    {
        courseSession.addCourse("CS", "COMPUTER SCIENCE");
        courseSession.addCourse("MAS", "MATHEMATICS");
        courseSession.addCourse("ENG", "ENGINEERING");
    }

    @Test
    public void COURSE_GetCourseByID()
    {
        Assert.assertEquals(courseSession.getCourseByID("ENG").getName(), "ENGINEERING");
    }

    @Test
    public void MODULE_AddModules()
    {
        //Main Modules
        moduleSession.addModule("ECM3401", "CS Module 1", 15, 0, "3", null);
        moduleSession.addModule("ECM3102", "ENG Module 2", 15, 0, "3", null);
        moduleSession.addModule("ECM3103", "ENG Module 3", 15, 0, "3", null);
        moduleSession.addModule("ECM3404", "CS Module 4", 15, 0, "3", null);
        moduleSession.addModule("ECM3405", "CS Module 5", 15, 0, "3", null);
        moduleSession.addModule("ECM3306", "MAS Module 6", 15, 0, "3", null);
        moduleSession.addModule("ECM3307", "MAS Module 7", 15, 0, "3", null);
        moduleSession.addModule("ECM3308", "MAS Module 8", 15, 0, "3", null);
        moduleSession.addModule("ECM3409", "CS Module 9", 15, 0, "3", null);
        moduleSession.addModule("ECM3410", "CS Module 10", 15, 0, "3", null);
    }

    @Test
    public void COURSEMODULES_AddModulesToCourse()
    {
        //Get a list of all of the modules
        int numOfModules = moduleSession.getListOfAllModules().size();
        Module modules[] = moduleSession.getListOfAllModules().toArray(new Module[numOfModules]);

        //Get a list of all of the courses
        int numOfCourses = courseSession.getListOfCourses().size();
        Course courses[] = courseSession.getListOfCourses().toArray(new Course[numOfCourses]);     

        //Just add the CompSci Modules
        //addModuleToCourse(module, course, isCompulsary);
        moduleSession.addModuleToCourse(modules[0], courses[0], true); //CS, ECM3401, Compulsary
        moduleSession.addModuleToCourse(modules[3], courses[0], true); //CS, ECM3404, Compulsary
        moduleSession.addModuleToCourse(modules[4], courses[0], true); //CS, ECM3405, Compulsary
        moduleSession.addModuleToCourse(modules[8], courses[0], true); //CS, ECM3409, Compulsary
        moduleSession.addModuleToCourse(modules[7], courses[0], true); //MAS, ECM3306, Compulsary
        moduleSession.addModuleToCourse(modules[1], courses[0], true); //ENG, ECM3102, Compulsary
        moduleSession.addModuleToCourse(modules[6], courses[0], false); //MAS, ECM3307, Optional
        moduleSession.addModuleToCourse(modules[2], courses[0], false); //ENG, ECM3103, Optional
        moduleSession.addModuleToCourse(modules[9], courses[0], false); //CS, ECM3410, Optional
    }

    @Test
    public void STUDENT_EnrollStudent()
    {
        String studentEmail = "abc101";
        Student student = studentSession.getStudentByEmailID(studentEmail);

        Course course = courseSession.getCourseByID("CS");

        //Enroll the student on the course
        studentSession.enrollStudentOnCourse(student, course);

        //Enroll the student on some modules
        List<Module> modules = null;
        modules.add(moduleSession.getModuleByID("ECM3401"));

        studentSession.enrollStudentOnModule(modules, student);
    }
}
