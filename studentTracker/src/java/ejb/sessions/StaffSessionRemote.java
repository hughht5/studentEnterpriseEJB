/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessions;

import ejb.entities.Module;
import ejb.entities.Staff;
import javax.ejb.Remote;

/**
 *
 * @author hmh205
 */
@Remote
public interface StaffSessionRemote {

    boolean addStaff(String _emailID, String _name, String _phone, String _room, String _password, boolean _isAdmin);

    boolean removeStaff(Staff _staff);

    Staff getStaffByEmailID(String _emailID);

    boolean checkStaffLogin(String _username, String _password);

    boolean addStaffToModule(Staff _staff, Module _module);

}
