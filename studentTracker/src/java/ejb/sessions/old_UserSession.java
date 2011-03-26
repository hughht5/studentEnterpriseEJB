/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.sessions;

import ejb.entities.Staff;
import ejb.entities.Student;
import ejb.entities.old_Users;
import ejb.sessions.StaffSession;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mm336
 */
@Stateless(mappedName="UserSession")
public class old_UserSession implements old_UserSessionRemote {
    @PersistenceContext
    EntityManager manager;

    
    @Override
    public Boolean checkLogin(String _username, String _password) {
        List<old_Users> users;
        try{
            String query = "SELECT user FROM Users as user";

            users = manager.createQuery(query).getResultList();

            for(old_Users u : users)
            {
               if(u.getUsername().equals(_username) && u.getPassword().equals(_password))
                   return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("++checkLogin ERROR: "+e);
            return false;
        }
    }

    @Override
    public Boolean checkIfStaff(String _username) {
        List<old_Users> users;
        try{
            String query = "SELECT user FROM Users as user";

            users = manager.createQuery(query).getResultList();

            for(old_Users u : users)
            {
               if(u.getUsername().equals(_username))
                   if(u.isStaff())
                       return true;
                   else
                       return false;
            }
            return false;
        } catch (Exception e) {
            System.out.println("++checkIfStaff ERROR: "+e);
            return false;
        }
    }

    @Override
    public Boolean addStudentUser(Student _student, String _password) {
        /*old_Users user = new old_Users();

        user.setUsername(_student.getEmailID());
        user.setPassword(_password);

        user.setIsAdmin(false);
        user.setIsStaff(false);

        user.setStudent(_student);
        user.setStaff(null);

        manager.persist(_student);
        //manager.flush();
        manager.persist(user);*/
        
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

        old_Users user = new old_Users();

        try
        {
            user.setUsername(_staff.getEmailID());
            user.setPassword(_password);

            user.setIsAdmin(_isAdmin);
            user.setIsStaff(true);

            user.setStudent(null);
            

            //possible try catch for already existing entities
            //manager.persist(_staff);
            //manager.flush();
            manager.persist(user);

            user.setStaff(_staff);
            manager.merge(_staff);

            return true;
        }catch(Exception ex)
        {
            System.out.println(ex);
            return false;
        }
    }

    

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
}
