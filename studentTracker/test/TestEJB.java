import ejb.entities.Assessment;
import ejb.entities.Course;
import ejb.entities.Module;
import ejb.entities.Staff;
import ejb.sessions.StudentSessionRemote;
import ejb.entities.Student;
import ejb.entities.Submission;
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
 * EJB JUNIT Tests
 */
public class TestEJB {
    private StudentSessionRemote studentSession;
    private StaffSessionRemote staffSession;
    private CourseSessionRemote courseSession;
    private ModuleSessionRemote moduleSession;

    private String host;
    private String port;

    /**
     * TestEJB()
     * Sets up the initial connect properties for ORB & the GlassFish server
     * It automatically gets the host name of the machine and sets the port
     * of the GlassFish server.
     *
     * It then initialises the four session beans.
     */
    public TestEJB() {
        //Get the hostname of the machine (e.g. bluexxx.ex.ac.uk)
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
            //Restart the GlassFish Server
        port = "20911";

        //Set the properties for the InitialContext
        Properties props = new Properties();
        props.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        props.put("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        props.put("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        props.put("org.omg.CORBA.ORBInitialHost", host);
        props.put("org.omg.CORBA.ORBInitialPort", port);

        //Initialise the Session Beans
        studentSession = lookupStudentSessionRemote(props);
        staffSession = lookupStaffSessionRemote(props);
        courseSession = lookupCourseSessionRemote(props);
        moduleSession = lookupModuleSessionRemote(props);
    }

    /**
     * JUNIT Set Up Class Method
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    /**
     * JUNIT Tear Down Class Method
     * @throws Exception
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * JUNIT Setup Method
     */
    @Before
    public void setUp() {
    }

    /**
     * JUNIT Tear Down method
     */
    @After
    public void tearDown() {
    }

    /**
     * lookupStudentSessionRemote()
     *
     * Sets up the connection to the Session Bean and returns the bean context
     *
     * @param props The Context properties
     * @return The session bean context
     */
    private StudentSessionRemote lookupStudentSessionRemote(Properties props) {
        try {
            Context c = new InitialContext(props);
            String jndiName = "java:global/studentTracker/StudentSession!" + "ejb.sessions.StudentSessionRemote";
            return (StudentSessionRemote) c.lookup(jndiName);
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

    /**
     * lookupStaffSessionRemote()
     *
     * Sets up the connection to the Session Bean and returns the bean context
     *
     * @param props The Context properties
     * @return The session bean context
     */
    private StaffSessionRemote lookupStaffSessionRemote(Properties props) {
        try {
            Context c = new InitialContext(props);
            String jndiName = "java:global/studentTracker/StaffSession!" + "ejb.sessions.StaffSessionRemote";
            return (StaffSessionRemote) c.lookup(jndiName);
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

    /**
     * lookupCourseSessionRemote()
     *
     * Sets up the connection to the Session Bean and returns the bean context
     *
     * @param props The Context properties
     * @return The session bean context
     */
    private CourseSessionRemote lookupCourseSessionRemote(Properties props) {
        try {
            Context c = new InitialContext(props);
            String jndiName = "java:global/studentTracker/CourseSession!" + "ejb.sessions.CourseSessionRemote";
            return (CourseSessionRemote) c.lookup(jndiName);
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

    /**
     * lookupModuleSessionRemote()
     *
     * Sets up the connection to the Session Bean and returns the bean context
     *
     * @param props The Context properties
     * @return The session bean context
     */
    private ModuleSessionRemote lookupModuleSessionRemote(Properties props) {
        try {
            Context c = new InitialContext(props);
            String jndiName = "java:global/studentTracker/ModuleSession!" + "ejb.sessions.ModuleSessionRemote";
            return (ModuleSessionRemote) c.lookup(jndiName);
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

    /**
     * Test to make sure the four session beans are not null
     */
    @Test
    public void testConnection()
    {
        Assert.assertTrue(studentSession!=null && staffSession!=null &&
                courseSession!=null && moduleSession!=null);
    }

    /**
     * Test to make sure we can add Students to the system.
     */
    @Test
    public void STUDENT_AddStudents()
    {
        try {
            //Set a dob for the students (just using one for simplicity)
            java.sql.Date dob = java.sql.Date.valueOf("1980-06-28"); //yyyy-mm-dd

            //addStudent(candidateNum, studentNum, emailID, fullname, dob, password)
            studentSession.addStudent(10, 20, "abc101", "Micmo1", dob, "password");
            studentSession.addStudent(10, 20, "abc102", "Micmo2", dob, "password");
            studentSession.addStudent(10, 20, "abc103", "Micmo3", dob, "password");
            studentSession.addStudent(10, 20, "abc104", "Micmo4", dob, "password");

            //If no errors assert true
            Assert.assertTrue(true);
        } catch (Exception e) {
            //Otherwise return an error
            Assert.assertTrue("testAddStudent() ERROR: "+e, false);
        }
    }

    /**
     * Test to check to make sure that the Students have been added successfully by retrieving
     * a student for the system
     */
    @Test
    public void STUDENT_GetStudentByID()
    {
        //Set up the email id of a student we added (Micmo3)
        String emailID = "abc103";

        //Get the student
        Student student = studentSession.getStudentByEmailID(emailID);

        //If the student could not be found, it would return null, so make sure
        //the student object is not null
        Assert.assertNotNull("Could not find student with emailID "+emailID, student);
    }

    /**
     * Test to add staff to the system.
     */
    @Test
    public void STAFF_AddStaff()
    {
        try {
            //Create two staff members
            //addStaff(emailID, fullname, phoneNumber, room, password, isAdmin)

            //This staff member is an admin
            staffSession.addStaff("tut01", "Mr Tutor 1", "01", "room1", "password", true);
            //This staff member is not an admin
            staffSession.addStaff("tut02", "Mr Tutor 2", "02", "room2", "password", false);
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.assertTrue("testAddStudent() ERROR: "+e, false);
        }
    }

    /**
     * Test to make sure that the staff were successfully added to the system by
     * retrieving a staff member that was just created
     */
    @Test
    public void STAFF_GetStaffByID()
    {
        //Staff member "Mr Tutor 1"
        String emailID = "tut01";

        //Get the staff member
        Staff staff = staffSession.getStaffByEmailID(emailID);

        //If the staff member is found it will return a staff object, otherwise null
        Assert.assertNotNull("Could not find staff with emailID "+emailID, staff);
    }

    /**
     * Test to check the login functionality of the Staff Client Login. This test
     * inputs the username and password of a registered (and authenticated)
     * staff member.
     */
    @Test
    public void LOGIN_CheckStaffLogin() {
        //Asserts true if the username & password are correct
        Assert.assertTrue(staffSession.checkStaffLogin("tut02", "password"));     
    }

    /**
     * Test to check the login functionality of the Student Client Login. This test
     * inputs the username and password of a registered (and authenticated)
     * student.
     */
    @Test
    public void LOGIN_CheckStudentLogin()
    {
        //Asserts true if the username & password are correct
        Assert.assertTrue(studentSession.checkStudentLogin("abc101", "password"));
    }

    /**
     * Test to make sure a Tutor (staff member) can be added to a student
     */
    @Test
    public void STUDENT_AddTutorToStudent(){
        try {
            //Get the student and tutor (staff) objects
            Student student = studentSession.getStudentByEmailID("abc102"); //Micmo2
            Staff tutor = staffSession.getStaffByEmailID("tut02"); //MrTutor2

            //Assign the tutor to the student
            studentSession.addTutor(student, tutor);
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.assertTrue("Could not add the tutor to the student", false);
        }
    }

    /**
     * Test to make sure the tutor was successfully added to the student and that
     * the tutor's information can be returned
     */
    @Test
    public void STUDENT_GetTutorForStudent()
    {
        String studentEmail = "abc102";

        //Get the student object (Student: "Micmo2")
        Student student = studentSession.getStudentByEmailID(studentEmail);
        //Get the tutor(staff) object (Staff: "Mr Tutor 2")
        Staff tutor = student.getTutor();

        //Check not null
        Assert.assertNotNull("Could not find tutor for student emailID "+studentEmail, tutor);

        //Make sure it's the right tutor
        Assert.assertEquals("tut02", tutor.getEmailID());

    }

    /**
     * Test to make sure you can add courses to the system.
     */
    @Test
    public void COURSE_AddCourses()
    {
        try {
            //Add three courses to the system
            courseSession.addCourse("CS", "COMPUTER SCIENCE");
            courseSession.addCourse("MAS", "MATHEMATICS");
            courseSession.addCourse("ENG", "ENGINEERING");
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.assertTrue("Failed to add courses", false);
        }
    }

    /**
     * Test to make sure you can return a specific course object (searching by
     * courseID)
     */
    @Test
    public void COURSE_GetCourseByID()
    {
        //Should return the 'ENGINEERING' course object
        Assert.assertEquals("ENGINEERING", courseSession.getCourseByID("ENG").getName());
    }

    /**+
     *
     * Test to make sure you can add modules to the system (not related to courses
     * at this stage)
     */
    @Test
    public void MODULE_AddModules()
    {
       //Add a selection of modules to the system
       try {
           //addModule(moduleID, moduleName, credits, averageMark, stage, prereqs)
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

    /**
     * Test to make sure you can get a specific module by moduleID (aka moduleCode)
     */
    @Test
    public void MODULE_GetModuleByID()
    {
        //Get the module "ECM3409"
        Module module = moduleSession.getModuleByID("ECM3409");

        //Should return the module with name "CS Module 9"
        Assert.assertEquals("CS Module 9", module.getName());
    }

    /**
     * Test to make sure we can list all of the modules within the system (regardless
     * of course)
     */
    @Test
    public void MODULE_ListAllModules()
    {
        //We added 10 modules before, so there should be 10 in the system
        Assert.assertEquals(10, moduleSession.getListOfAllModules().size());
    }

    /**
     * Test to add modules to a specific course. This includes setting the optional
     * /compulsary flag of the module for that course.
     */
    @Test
    public void MODULE_AddModulesToCourse()
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

    /**
     * Test to make sure we can enroll a student on a course and subsequent modules
     */
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

        //Enroll the student on some modules (in this case just the one)
        List<Module> modules = new ArrayList();
        modules.add(moduleSession.getModuleByID("ECM3401"));

        //Make sure we are adding some modules
        Assert.assertNotNull("No modules to be added", modules);

        studentSession.enrollStudentOnModule(modules, student);
    }

    /**
     * Make sure we can get a list of enrolled modules for a specific student
     */
    @Test
    public void STUDENT_GetEnrolledModulesForStudent()
    {
        //Student has only enrolled on one module
        Assert.assertEquals(1, studentSession.getModulesEnrolledOn("abc102").size());
    }

    /**
     * Make sure we can get a list of enrolled students for a specific course
     */
    @Test
    public void COURSE_GetStudentsOnCourse()
    {
        //Only one student has been added to the CS course
        Assert.assertEquals(1, courseSession.getListOfStudentsOnCourse("CS").size());
    }

    /**
     * Test to see if we can return a list of all of the courses
     */
    @Test
    public void COURSE_ListAllCourses()
    {
        //Only 3 courses have been added
        Assert.assertEquals(3, courseSession.getListOfCourses().size());
    }

    /**
     * Test to make sure we can add a lecturer (staff member) to a module,
     * with the option of setting them as the coordinator
     */
    @Test
    public void LECTURE_AddStaffToModule()
    {
        try {
            //Get the staff and module entities
            Module module = moduleSession.getModuleByID("ECM3401");
            Staff staff = staffSession.getStaffByEmailID("tut02");

            //Add the staff member to the module (and make them the coordinator)
            staffSession.addStaffToModule(staff, module, true);
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.assertTrue("Failed to add the staff member to the module", false);
        }
    }

    /**
     * Test to check to see if a staff member is a lecturer on a module
     */
    @Test
    public void STAFF_CheckIfLecturer()
    {
        //Staff member "tut02" (Mr Tutor 2) is a lecturer on ECM3401
        Assert.assertTrue(moduleSession.checkIfLecturer("tut02", "ECM3401"));
    }
    
    /**
     * Test to check to see if a staff member is a coordinator on a module
     */
    @Test
    public void STAFF_CheckIfCoordinator()
    {
        //Staff member "tut02" (Mr Tutor 2) is the module coordinator for ECM3401
        Assert.assertTrue(moduleSession.checkIfCoordinator("tut02", "ECM3401"));
    }

    /**
     * Test to make sure we can return a list of all students on a module. Only 
     * the module coordinator can do this.
     */
    @Test
    public void MODULE_GetStudentsOnModule()
    {
        //Only one student has beena dded to the ECM3401 module and "tut02" is the
        //module coordinator for ECM3401
        Assert.assertEquals(1, moduleSession.getListOfEnrolledStudents("ECM3401", "tut02").size());
    }
    
    /**
     * Test to make sure we can add an assessment to the system. This method adds
     * two assessments to the module ECM3401.
     */
    @Test
    public void ASSESSMENT_AddAssessment()
    {
        //Set up the date objects
        java.sql.Date handOut = java.sql.Date.valueOf("2011-03-20"); //yyyy-mm-dd
        java.sql.Date handIn = java.sql.Date.valueOf("2011-05-28"); //yyyy-mm-dd
        java.sql.Date lateHandIn = java.sql.Date.valueOf("2011-03-21"); //yyyy-mm-dd

        //Get the module to add the assessment to
        Module module = moduleSession.getModuleByID("ECM3401");

        try {
            //Add the assessments. 
            //addAssessmentToModule(sequence, handOutDate, handInDate, duration, 
                //weighting, module, staffID (only coords can add))
            moduleSession.addAssessmentToModule(1, "Practical", handOut, handIn, 10, 0.3f, module, "tut02");
            
            //This assessment has a handIn date before today, so any submissions will be marked as 'late'
            moduleSession.addAssessmentToModule(2, "Examination", handOut, lateHandIn, 2, 0.7f, module, "tut02");
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.assertTrue("Could not add assessments", false);
        }
    }

    /**
     * MAke sure we can Get a list of assessments for a module
     */
    @Test
    public void ASSESSMENT_GetAssessmentsForModule()
    {
        //Only two assessments have been added for ECM3401
        Assert.assertEquals(2, moduleSession.getAssessmentsForModule("ECM3401").size());
    }

    /**
     * Test to make sure a student can add a submission to an assessment
     */
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

        //Add submission to the assessments
        studentSession.submitAssessment(student, ass); //Normal
        studentSession.submitAssessment(student, ass2); //This submission will be marked as late
    }

    /**
     * Test to make sure a staff member can mark a submission for a student
     */
    @Test
    public void STAFF_MarkSubmission()
    {
        //Get the two submissions for student "abc102" on module "ECM3401"
        Submission submission = studentSession.getSpecificSubmission("ECM3401", 1, "abc102");
        Submission submission2 = studentSession.getSpecificSubmission("ECM3401", 2, "abc102"); //The late submission

        //Make sure the submissions were found
        Assert.assertNotNull("No submission found", submission);
        Assert.assertNotNull("No submission2 found", submission2);

        //Mark the submissions
        //markSubmission(submission, mark, feedback)
        staffSession.markSubmission(submission, 72, "well done");
        staffSession.markSubmission(submission2, 90, "shame it was late"); //This was submitted late so will be capped at 40%
    }

    /**
     * Test to make sure we can get an average mark for an assessment on a module
     */
    @Test
    public void ASSESSMENT_GetAverageMarkForAssessment()
    {
        //Only one assessment marked for ECM3401 assessment sequence 1 (i.e. CA1) so will return 72.0
        Assert.assertEquals(72F, moduleSession.getAverageAssessmentMark("ECM3401", 1));
    }

    /**
     * Test to make sure we can get the mark for a specific submission
     */
    @Test
    public void SUBMISSION_GetSubmissionMark()
    {
        //Get the submission object for student "abc102" on module "ECM3401" (CA1)
        Submission submission = studentSession.getSpecificSubmission("ECM3401", 1, "abc102");

        //Make sure the mark was 72.0
        Assert.assertEquals(72F, studentSession.getSubmissionMark(submission));
    }

    /**
     * Test to make sure we can get the mark for a specific submission, and that
     * if the submission was flagged as being 'late', the mark returned is capped
     */
    @Test
    public void SUBMISSION_GetLateSubmissionMark()
    {
        //Get the submission
        Submission submission = studentSession.getSpecificSubmission("ECM3401", 2, "abc102");

        //As the submission was late, the result should be capped at 40% (as it's for stage 3)
        Assert.assertEquals(40F, studentSession.getSubmissionMark(submission));
    }
    
    /**
     * Test to make sure we can get the average module mark.
     */
    @Test
    public void MODULE_GetAverageMarkForModule()
    {
        //Get the average module mark for the ECM3401 module.
        float moduleMark = moduleSession.getAverageModuleMark("ECM3401");

        //Two submissions (72 and 90) ==> (72+90)/2 = 81
        Assert.assertEquals(81F, moduleMark);
    }

}
