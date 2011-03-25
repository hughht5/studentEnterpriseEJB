/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.sessions;

import ejb.entities.Staff;
import ejb.entities.Student;
import javax.ejb.Remote;

/**
 *
 * @author mm336
 */
@Remote
public interface UserSessionRemote {

    Boolean checkLogin(String username, String password);

    Boolean checkIfStaff(String username);

    Boolean addStudentUser(Student _student, String _password);

    Boolean addStaffUser(Staff _staff, String _password);

    Boolean addStaffUser(Staff _staff, String _password, boolean _isAdmin);

    Student getStudentUser(String _username);

    Staff getStaffUser(String _username);
    
}
