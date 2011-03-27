/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessions;

import ejb.entities.Course;
import ejb.entities.CourseModules;
import ejb.entities.Module;
import ejb.entities.Prerequisites;
import java.util.Collection;
import java.util.List;
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
    public boolean addModule(String moduleID, String name, int credits, float avgMark, String stage, Collection<Module> prerequisites) {
        try {
            Module module = new Module();
            module.setModuleID(moduleID);
            module.setStage(stage);
            module.setName(name);
            module.setCredits(credits);
            module.setAvgMark(avgMark);
            module.setListOfPrereqs(prerequisites);
            manager.persist(module);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean removeModule(Module module) {
        try {
            manager.remove(manager.find(Module.class, module.getId()));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean addModuleToCourse(Module _module, Course _course) {
        try {
            CourseModules courseModule = new CourseModules();
            courseModule.setCourse(manager.find(Course.class, _course.getId()));
            courseModule.setModule(manager.find(Module.class, _module.getId()));
            manager.persist(courseModule);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean removeModuleFromCourse(CourseModules _courseModule) {
        try {
            manager.remove(manager.find(CourseModules.class, _courseModule.getId()));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
