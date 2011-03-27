/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessions;

import ejb.entities.Course;
import ejb.entities.Student;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * The Course session allows clients to add and remove Courses from the system
 * and to access details of a Course and a list of all available Courses. Users
 * can also view a list of all users on a particular course.
 *
 * These methods are directly related to courses allowing users to access all
 * course information in one  place.
 *
 */
@Stateless
public class CourseSession implements CourseSessionRemote {

    @PersistenceContext
    EntityManager manager;

    /**
     * Method to add a new Course
     * @param _courseID
     * @param _name
     * @return true for success, false otherwise
     */
    @Override
    public boolean addCourse(String _courseID, String _name) {
        try {
            Course course = new Course();
            course.setCourseID(_courseID);
            course.setName(_name);
            manager.persist(course);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Remove a course from the system
     * @param _course
     * @return true for success, false otherwise
     */
    @Override
    public boolean removeCourse(Course _course) {
        try {
            manager.remove(manager.find(Course.class, _course.getId()));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Get a Course object by it's ID
     * @param _ID
     * @return the course if found, null otherwise
     */
    @Override
    public Course getCourseByID(String _ID) {
        List<Course> courses;
        try {
            String query = "SELECT course FROM Course as course";

            courses = manager.createQuery(query).getResultList();

            for (Course c : courses) {
                if (c.getCourseID().equals(_ID)) {
                    return c;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("getCourseByID ERROR: " + e);
            return null;
        }
    }

    /**
     * Method to get a list of all Courses in the system
     * @return a list of Courses
     */
    @Override
    public List<Course> getListOfCourses() {
        List<Course> courses;
        try {
            String query = "SELECT course FROM Course as course";
            courses = manager.createQuery(query).getResultList();
            return courses;
        } catch (Exception e) {
            System.out.println("getListOfCourses ERROR: " + e);
            return null;
        }
    }

    /**
     * Method to get a list of all Students on a particular course
     * @param _courseID
     * @return The list of students. This list can be empty.
     */
    @Override
    public Collection<Student> getListOfStudentsOnCourse(String _courseID) {
        Collection<Student> students;
        Collection<Student> studentsOnCourse = new ArrayList();
        try {
            String query = "SELECT student FROM Student as student";

            students = manager.createQuery(query).getResultList();

            for (Student s : students) {
                if (s.getCourse() != null) {
                    if (s.getCourse().getCourseID().equals(_courseID)) {
                        studentsOnCourse.add(s);
                    }
                }
            }

            return studentsOnCourse;
        } catch (Exception e) {
            System.out.println("getListOfStudentsOnCourse ERROR: " + e);
            return null;
        }

    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
