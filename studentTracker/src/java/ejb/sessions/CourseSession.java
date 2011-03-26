/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.sessions;

import ejb.entities.Course;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hmh205
 */
@Stateless
public class CourseSession implements CourseSessionRemote {

    @PersistenceContext
    EntityManager manager;

    @Override
    public boolean addCourse(String _courseID, String _name) {
        try {
            Course course = new Course();
            course.setName(_name);
            manager.persist(course);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean removeCourse(Course _course) {
        try{
            manager.remove(manager.find(Course.class, _course.getId()));
        } catch (Exception e) {
            return false;
        }
        return true;
    }



    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
}
