/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessions;

import ejb.entities.Lecture;
import ejb.entities.Module;
import ejb.entities.Staff;
import ejb.entities.Submission;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * This class implements the business logic for methods described in the
 * StaffSessionRemote interface.
 *
 */
@Stateless
public class StaffSession implements StaffSessionRemote {

    @PersistenceContext
    EntityManager manager;

    /**
     * Add a member of staff to the system
     * @param _emailID
     * @param _name
     * @param _phone
     * @param _room
     * @param _password
     * @param _isAdmin
     * @return true for success, false otherwise
     */
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

    /**
     * Remove a member of staff from the system
     * @param _staff
     * @return true for success, false otherwise
     */
    @Override
    public boolean removeStaff(Staff _staff) {
        try {
            manager.remove(manager.find(Staff.class, _staff.getId()));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Get a Staff object by its email ID
     * @param _emailID
     * @return the staff object if found, null otherwise
     */
    @Override
    public Staff getStaffByEmailID(String _emailID) {
        List<Staff> staff;
        try {
            String query = "SELECT staff FROM Staff as staff";

            staff = manager.createQuery(query).getResultList();

            for (Staff s : staff) {
                if (s.getEmailID().equals(_emailID)) {
                    return s;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("getStaffByEmailID ERROR: " + e);
            return null;
        }
    }

    /**
     * Ensure that the member of staff's username and password are correct
     * @param _username
     * @param _password
     * @return true if correct, false otherwise
     */
    @Override
    public boolean checkStaffLogin(String _username, String _password) {
        List<Staff> staff;
        try {
            String query = "SELECT staff FROM Staff as staff";

            staff = manager.createQuery(query).getResultList();

            for (Staff s : staff) {
                if (s.getEmailID().equals(_username) && s.getPassword().equals(_password)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("checkStaffLogin ERROR: " + e);
            return false;
        }
    }



    /**
     * Method adds a member of staff as a lecture for a module
     * @param _staff
     * @param _module
     * @return true for success, false otherwise
     */
    @Override
    public boolean addStaffToModule(Staff _staff, Module _module, boolean _isCoordinator) {
        try {
            Lecture lecture = new Lecture();
            lecture.setStaff(manager.find(Staff.class, _staff.getId()));
            lecture.setModule(manager.find(Module.class, _module.getId()));
            lecture.setIsCoordinator(_isCoordinator);
            manager.persist(lecture);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Boolean markSubmission(Submission _submission, float _mark, String _feedback) {
        try {
            _submission.setMark(_mark);
            _submission.setFeedback(_feedback);
            manager.merge(_submission);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")


}
