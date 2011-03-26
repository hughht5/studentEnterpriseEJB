/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.sessions;

import ejb.entities.Course;
import ejb.entities.Module;
import javax.ejb.Remote;

/**
 *
 * @author hmh205
 */
@Remote
public interface CourseModuleSessionRemote {

    public boolean addModuleToCourse(Module _module, Course _course);
    
}
