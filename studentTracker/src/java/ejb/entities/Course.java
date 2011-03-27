/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author hmh205
 *
 * The Course class provides details of
 */
@Entity
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.entities.Course[id=" + id + "]";
    }


     ///////////////////////////hughs code/////////////////

    private String courseID;

    /**
     * Get the value of courseID
     *
     * @return the value of courseID
     */
    public String getCourseID() {
        return courseID;
    }

    /**
     * Set the value of courseID
     *
     * @param courseID new value of courseID
     */
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
    protected String name;

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }


    /////////////////////////claimed///////////////
    //hugh
    //Course has many course modules - course modules each have one course.
    @OneToMany(mappedBy = "course", fetch=FetchType.EAGER)
    private Collection<CourseModules> listOfCourseModules;

    public Collection<CourseModules> getListOfCourseModules()
    {
        return listOfCourseModules;
    }
    public void setListCourseModules(Collection<CourseModules> listOfCourseModules)
    {
        this.listOfCourseModules = listOfCourseModules;
    }

    //hugh end

    //micmo
    //Student has one course. A course has many students.
    @OneToMany(mappedBy = "course", fetch=FetchType.EAGER)
    private Collection<Student> listOfStudents;

    public Collection<Student> getListOfStudents()
    {
        return listOfStudents;
    }
    public void setListOfStudents(Collection<Student> listOfStudents)
    {
        this.listOfStudents = listOfStudents;
    }
    //micmo end
}
