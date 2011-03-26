/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessions;

import ejb.entities.Module;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hmh205
 */
@Stateless
public class ModuleSession implements ModuleSessionRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager manager;

    @Override
    public boolean addModule(String moduleID, String name, int credits, float avgMark) {
         try {
            Module module = new Module();
            module.setModuleID(moduleID);
            module.setName(name);
            module.setCredits(credits);
            module.setAvgMark(avgMark);
            manager.persist(module);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean removeModule(Module module) {
        try{
            manager.remove(manager.find(Module.class, module.getId()));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    

}
