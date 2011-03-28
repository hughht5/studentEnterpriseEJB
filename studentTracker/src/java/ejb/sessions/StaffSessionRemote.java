/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessions;

import ejb.entities.Module;
import ejb.entities.Staff;
import ejb.entities.Submission;
import javax.ejb.Remote;

/**
 *
 * This is the remote interface for the Staff Session.
 *
 * The Staff session contains methods to add/remove members of staff, get a
 * Staff object, login a member of staff, and add a member of staff as a
 * lecturer to a module.
 *
 * It encapsulates all methods that are directly related to a member of staff
 *
 */
@Remote
public interface StaffSessionRemote {

    boolean addStaff(String _emailID, String _name, String _phone, String _room, String _password, boolean _isAdmin);

    boolean removeStaff(Staff _staff);

    Staff getStaffByEmailID(String _emailID);

    boolean checkStaffLogin(String _username, String _password);

    boolean addStaffToModule(Staff _staff, Module _module, boolean _isCoordinator);

    Boolean markSubmission(Submission _submission, float _mark, String _feedback);
}
