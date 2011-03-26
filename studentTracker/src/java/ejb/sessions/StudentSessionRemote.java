/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.sessions;

import ejb.entities.Student;
import java.sql.Date;
import javax.ejb.Remote;

/**
 *
 * @author hmh205
 */
@Remote
public interface StudentSessionRemote {

    Boolean addStudent(int candidateNum, int studentNum, String emailID, String name, Date dob);

    boolean removeStudent(Student _student);

    Student getStudentByEmailID(String _emailID);

}
