/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.sessions;

import ejb.entities.Staff;
import ejb.entities.Student;
import ejb.entities.Users;
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
            return false;
    }

    @Override
    public Boolean checkIfStaff(String _username) {
        boolean isStaff = false;

        //if(Staff.getStaff(_username).isAdmin)
        if(isStaff)
            return true;
        else
            return false;
    }

    @Override
    public Boolean addStudentUser(Student _student, String _password) {
        Users user = new Users();

        user.setUsername(_student.getEmailID());
        user.setPassword(_password);

        user.setIsAdmin(false);
        user.setIsStaff(false);

        user.setStudent(_student);
        user.setStaff(null);

        manager.persist(user);
        
        return true;
    }

    @Override
    public Boolean addStaffUser(Staff _staff, String _password)
    {
        addStaffUser(_staff, _password, false);

        return true;
    }

    @Override
    public Boolean addStaffUser(Staff _staff, String _password, boolean _isAdmin) {

        Users user = new Users();

        try
        {
            user.setUsername(_staff.getEmailID());
            user.setPassword(_password);

            user.setIsAdmin(_isAdmin);
            user.setIsStaff(true);

            user.setStudent(null);
            user.setStaff(_staff);

            //possible try catch for already existing entities
            manager.persist(user);

            return true;
        }catch(Exception ex)
        {
            System.out.println(ex);
            return false;
        }
    }

    public Staff getStaffUser(String _username)
    {
        return new Staff();
    }

    public Student getStudentUser(String _username) {
        //Get the student from the database
        //Student student = StudentSession.(_username);
        //return student;

        return new Student();
    }

    /*public Staff getStaffUser(String _username) {
        //Get the staff from the database
        Staff staff = StaffSession.getStaff(_username);

        return staff;
    }*/

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
}
