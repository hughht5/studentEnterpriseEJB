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
import javax.ejb.Remote;

/**
 *
 * @author hmh205
 */
@Remote
public interface ModuleSessionRemote {

    boolean addModule(String moduleID, String name, int credits, float AVGMark, String stage, Collection<Module> prerequisites);

    boolean removeModule(Module module);

    public boolean addModuleToCourse(Module _module, Course _course);

    public boolean removeModuleFromCourse(CourseModules _courseModule);
}
