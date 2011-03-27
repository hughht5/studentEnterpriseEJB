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
import java.sql.Date;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author hmh205
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
}
