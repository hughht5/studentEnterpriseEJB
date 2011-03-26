/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessions;

import ejb.entities.Staff;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hmh205
 */
@Stateless
public class StaffSession implements StaffSessionRemote {

    @PersistenceContext
    EntityManager manager;

    @Override
    public boolean addStaff(String _emailID, String _name, String _phone) {
        try {
            Staff staff = new Staff();
            staff.setEmailID(_emailID);
            staff.setName(_name);
            staff.setPhone(_phone);
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
    public Staff findStaff(String _emailID) {
        return null;
    }





    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
