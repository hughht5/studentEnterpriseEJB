/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessions;

import ejb.entities.Assessment;
import ejb.entities.Course;
import ejb.entities.Module;
import ejb.entities.Staff;
import ejb.entities.Student;
import ejb.entities.Submission;
import java.sql.Date;
import java.util.Collection;
import javax.ejb.Remote;

/**
 * This is the remote interface for the student Session.
 *
 * The Student session contains methods to add/remove students, get a
 * Student object, login a student, add a member of staff as the student's
 * tutor, enroll a student on a course/module, and submit assessments
 *
 * It encapsulates all methods that are directly related to a student
 *
 */
@Remote
public interface StudentSessionRemote {

    Boolean addStudent(int candidateNum, int studentNum, String emailID, String name, Date dob, String password);

    boolean removeStudent(Student _student);

    Student getStudentByEmailID(String _emailID);

    Boolean addTutor(Student _student, Staff _tutor);

    boolean checkStudentLogin(String _username, String _password);

    boolean enrollStudentOnModule(Collection<Module> _modules, Student _student);

    boolean enrollStudentOnCourse(Student _student, Course _course);

    boolean submitAssessment(Student _student, Assessment _assesment);

    Collection<Module> getModulesEnrolledOn(String _emailID);

    Submission getSpecificSubmission(String _moduleID, int _sequence, String _studentEmailID);

    float getSubmissionMark(Submission _submission);
}
