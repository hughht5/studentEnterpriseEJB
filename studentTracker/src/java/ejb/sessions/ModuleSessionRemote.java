/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.sessions;

import ejb.entities.Module;
import javax.ejb.Remote;

/**
 *
 * @author hmh205
 */
@Remote
public interface ModuleSessionRemote {

    boolean addModule(String moduleID, String name, int credits, float AVGMark);

    boolean removeModule(Module module);
    
}
