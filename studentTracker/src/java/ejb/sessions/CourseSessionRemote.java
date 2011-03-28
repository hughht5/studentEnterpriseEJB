/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessions;

import ejb.entities.Course;
import ejb.entities.Student;
import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * This is the remote interface for the Course session.
 * 
 * The Course session allows clients to add and remove Courses from the system
 * and to access details of a Course and a list of all available Courses. Users
 * can also view a list of all users on a particular course.
 *
 * These methods are directly related to courses allowing users to access all
 * course information in one  place.
 * 
 */
@Remote
public interface CourseSessionRemote {

    boolean addCourse(String _courseID, String _name);

    boolean removeCourse(Course _course);

    Course getCourseByID(String _ID);

    List<Course> getListOfCourses();

    Collection<Student> getListOfStudentsOnCourse(String _courseID);
}
