/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.sessions;

import java.sql.Date;
import javax.ejb.Remote;

/**
 *
 * @author hmh205
 */
@Remote
public interface StudentSessionRemote {

    string addStudent(int candidateNum, int studentNum, String emailID, String name, Date dob);

    boolean getStudent(int studentNumber);

    list<List> listStudentModules(int studentNumber);
    
}
 