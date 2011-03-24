/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mm336
 */
@Stateless
public class UserSession implements UserSessionRemote {
    @PersistenceContext
    EntityManager manager;

    
    @Override
    public Boolean checkLogin(String _username, String _password) {
        if((_username == null ? "user" == null : _username.equals("user"))
                && (_password == null ? "pass" == null : _password.equals("pass")))
        {
            return true;
        }
        else
            return null;
    }

    @Override
    public Boolean checkIfStaff(String _username) {
        boolean isStaff = false;
        if(isStaff)
            return true;
        else
            return false;
    }

    /*public Student getStudentUser(String _username) {
        //Get the student from the database
        Student student = Student.getStudent(_username);
        
        return student;
    }*/

    /*public Staff getStaffUser(String _username) {
        //Get the staff from the database
        Staff staff = Staff.getStaff(_username);

        return staff;
    }*/

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
}
