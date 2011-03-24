/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.sessions;

import javax.ejb.Remote;

/**
 *
 * @author mm336
 */
@Remote
public interface UserSessionRemote {

    Boolean checkLogin(String username, String password);

    Boolean checkIfStaff(String username);
    
}
