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

import ejb.entities.Assessment;
import ejb.entities.Course;
import ejb.entities.Module;
import ejb.entities.Staff;
import ejb.sessions.StudentSessionRemote;
import ejb.entities.Student;
import ejb.sessions.CourseSessionRemote;
import ejb.sessions.ModuleSessionRemote;
import ejb.sessions.StaffSessionRemote;
import junit.framework.Assert;
import java.util.List;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.*;
import java.util.ArrayList;

/**
 *
 * @author mm336
 */
public class TestEJB {
    private StudentSessionRemote studentSession;
    private StaffSessionRemote staffSession;
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
        Assert.assertTrue(studentSession!=null && staffSession!=null &&
                courseSession!=null && moduleSession!=null);
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

        Assert.assertNotNull("Could not find student with emailID "+emailID, student);
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
        Assert.assertTrue(staffSession.checkStaffLogin("tut02", "password"));     
    }

    @Test
    public void LOGIN_CheckStudentLogin()
    {
        Assert.assertTrue(studentSession.checkStudentLogin("abc101", "password"));
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
        Assert.assertEquals("tut02", tutor.getEmailID());

    }

    @Test
    public void COURSE_AddCourses()
    {
        try {
            courseSession.addCourse("CS", "COMPUTER SCIENCE");
            courseSession.addCourse("MAS", "MATHEMATICS");
            courseSession.addCourse("ENG", "ENGINEERING");
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.assertTrue("Failed to add courses", false);
        }
    }

    @Test
    public void COURSE_GetCourseByID()
    {
        Assert.assertEquals("ENGINEERING", courseSession.getCourseByID("ENG").getName());
    }

    @Test
    public void MODULE_AddModules()
    {
        //Main Modules
       try {
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
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.assertTrue("Failed to add modules", false);
        }
    }

    @Test
    public void COURSEMODULES_AddModulesToCourse()
    {
        //Get a list of all of the modules
        int numOfModules = moduleSession.getListOfAllModules().size();
        Module modules[] = moduleSession.getListOfAllModules().toArray(new Module[numOfModules]);

        //Make sure we have some modules to add
        Assert.assertNotNull("No Modules to add", modules);

        //Get a list of all of the courses
        int numOfCourses = courseSession.getListOfCourses().size();
        Course courses[] = courseSession.getListOfCourses().toArray(new Course[numOfCourses]);

        //Make sure we have some courses to add modules to
        Assert.assertNotNull("No courses to add Modules to", courses);

        //Just add the CompSci Modules
        //addModuleToCourse(module, course, isCompulsary);
        try {
            moduleSession.addModuleToCourse(modules[0], courses[0], true); //CS, ECM3401, Compulsary
            moduleSession.addModuleToCourse(modules[3], courses[0], true); //CS, ECM3404, Compulsary
            moduleSession.addModuleToCourse(modules[4], courses[0], true); //CS, ECM3405, Compulsary
            moduleSession.addModuleToCourse(modules[8], courses[0], true); //CS, ECM3409, Compulsary
            moduleSession.addModuleToCourse(modules[7], courses[0], true); //MAS, ECM3306, Compulsary
            moduleSession.addModuleToCourse(modules[1], courses[0], true); //ENG, ECM3102, Compulsary
            moduleSession.addModuleToCourse(modules[6], courses[0], false); //MAS, ECM3307, Optional
            moduleSession.addModuleToCourse(modules[2], courses[0], false); //ENG, ECM3103, Optional
            moduleSession.addModuleToCourse(modules[9], courses[0], false); //CS, ECM3410, Optional
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.assertTrue("Failed to add modules to the course", false);
        }
    }

    @Test
    public void STUDENT_EnrollStudent()
    {
        String studentEmail = "abc102";
        Student student = studentSession.getStudentByEmailID(studentEmail);

        //Make sure we have a student to add
        Assert.assertNotNull("No Student Found", student);

        Course course = courseSession.getCourseByID("CS");

        //Make sure we have a course to add
        Assert.assertNotNull("No Course Found", course);

        //Enroll the student on the course
        studentSession.enrollStudentOnCourse(student, course);

        //Enroll the student on some modules
        List<Module> modules = new ArrayList();
        modules.add(moduleSession.getModuleByID("ECM3401"));

        //Make sure we are adding some modules
        Assert.assertNotNull("No modules to be added", modules);

        studentSession.enrollStudentOnModule(modules, student);
    }

    @Test
    public void STUDENT_GetEnrolledModulesForStudent()
    {
        //Student has only enrolled on one module
        Assert.assertEquals(1, studentSession.getModulesEnrolledOn("abc102").size());
    }

    @Test
    public void COURSE_GetStudentsOnCourse()
    {
        //Only one student has been added to the CS course
        Assert.assertEquals(1, courseSession.getListOfStudentsOnCourse("CS").size());
    }

    @Test
    public void MODULE_GetStudentsOnModule()
    {
        //Only one student has beena dded to the ECM3401 module
        Assert.assertEquals(1, moduleSession.getListOfEnrolledStudents("ECM3401").size());
    }

    @Test
    public void ASSESSMENT_AddAssessment()
    {
        java.sql.Date handOut = java.sql.Date.valueOf("2011-03-20"); //yyyy-mm-dd
        java.sql.Date handIn = java.sql.Date.valueOf("2011-03-28"); //yyyy-mm-dd
        Module module = moduleSession.getModuleByID("ECM3401");

        try {
            moduleSession.addAssessmentToModule(1, "Practical", handOut, handIn, 10, 0.3f, module);
            moduleSession.addAssessmentToModule(2, "Examination", handOut, handIn, 2, 0.7f, module);
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.assertTrue("Could not add assessments", false);
        }
    }

    @Test
    public void ASSESSMENT_GetAssessmentsForModule()
    {
        Assert.assertEquals(2, moduleSession.getAssessmentsForModule("ECM3401").size());
    }

    @Test
    public void SUBMISSION_AddSubmission()
    {
        String studentEmail = "abc102";
        Student student = studentSession.getStudentByEmailID(studentEmail);

        //Make sure we have a student to add
        Assert.assertNotNull("No Student Found", student);

        Assessment ass = moduleSession.getAssessmentForModule("ECM3401", 1);
        Assessment ass2 = moduleSession.getAssessmentForModule("ECM3401", 2);

        //Make sure we have an assessment to submit to
        Assert.assertNotNull("No Assessment Found", ass);
        Assert.assertNotNull("No Assessment Found", ass2);

        studentSession.submitAssessment(student, ass);
        studentSession.submitAssessment(student, ass2);
    }

    @Test
    public void STAFF_MarkSubmission()
    {
        
    }

    @Test
    public void ASSESSMENT_GetAverageMarksForAssessment()
    {
        Assert.assertEquals(0F, moduleSession.getAverageAssessmentMark("ECM3401", 1));
    }
}
