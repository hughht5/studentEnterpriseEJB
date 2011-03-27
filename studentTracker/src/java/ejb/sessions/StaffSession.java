/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessions;

import ejb.entities.Lecture;
import ejb.entities.Module;
import ejb.entities.Staff;
import ejb.entities.Student;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * 
 */
@Stateless
public class StaffSession implements StaffSessionRemote {

    @PersistenceContext
    EntityManager manager;

    @Override
    public boolean addStaff(String _emailID, String _name, String _phone, String _room, String _password, boolean _isAdmin) {
        try {
            Staff staff = new Staff();
            staff.setEmailID(_emailID);
            staff.setName(_name);
            staff.setPhone(_phone);
            staff.setRoom(_room);
            staff.setPassword(_password);
            staff.setIsAdmin(_isAdmin);
            
            manager.persist(staff);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    @Override
    public boolean removeStaff(Staff _staff) {
        try{
            manager.remove(manager.find(Staff.class, _staff.getId()));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Staff getStaffByEmailID(String _emailID) {
         List<Staff> staff;
        try{
            String query = "SELECT staff FROM Staff as staff";

            staff = manager.createQuery(query).getResultList();

            for(Staff s : staff)
            {
               if(s.getEmailID().equals(_emailID))
                   return s;
            }
            return null;
        } catch (Exception e) {
            System.out.println("getStaffByEmailID ERROR: "+e);
            return null;
        }
    }

    @Override
    public boolean checkStaffLogin(String _username, String _password)
    {
        List<Staff> staff;
        try{
            String query = "SELECT staff FROM Staff as staff";

            staff = manager.createQuery(query).getResultList();

            for(Staff s : staff)
            {
               if(s.getEmailID().equals(_username) && s.getPassword().equals(_password))
                   return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("checkStaffLogin ERROR: "+e);
            return false;
        }
    }

    @Override
    public boolean addStaffToModule(Staff _staff, Module _module) {
        try {
            Lecture lecture = new Lecture();
            lecture.setStaff(manager.find(Staff.class, _staff.getId()));
            lecture.setModule(manager.find(Module.class, _module.getId()));
            manager.persist(lecture);
        } catch (Exception e) {
            return false;
        }
        return true;
    }



    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
