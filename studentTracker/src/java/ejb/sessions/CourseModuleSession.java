/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessions;

import ejb.entities.Course;
import ejb.entities.CourseModules;
import ejb.entities.Module;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hmh205
 */
@Stateless
public class CourseModuleSession implements CourseModuleSessionRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager manager;

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
        try{
            manager.remove(manager.find(CourseModules.class, _courseModule.getId()));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    

}
