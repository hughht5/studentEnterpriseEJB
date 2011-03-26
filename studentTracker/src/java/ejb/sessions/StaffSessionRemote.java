/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessions;

import ejb.entities.Staff;
import javax.ejb.Remote;

/**
 *
 * @author hmh205
 */
@Remote
public interface StaffSessionRemote {

    boolean addStaff(String _emailID, String _name, String _phone);

    boolean removeStaff(Staff _staff);

    Staff getStaffByEmailID(String _emailID);
}
