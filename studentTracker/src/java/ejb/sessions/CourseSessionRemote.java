/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.sessions;

import ejb.entities.Course;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author hmh205
 */
@Remote
public interface CourseSessionRemote {

    boolean addCourse(String _courseID, String _name);

    boolean removeCourse(Course _course);

    Course getCourseByID(String _ID);

    List<Course> getListOfCourses();
}
