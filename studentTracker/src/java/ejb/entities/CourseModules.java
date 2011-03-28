/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The CourseModules class contains the details for the modules
 */
@Entity
public class CourseModules implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Auto generated code get ID
     *
     * @return long
     */
    public Long getId() {
        return id;
    }

    /**
     * Auto generated code set ID
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Auto generated code get hash code
     *
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Auto generated code
     *
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CourseModules)) {
            return false;
        }
        CourseModules other = (CourseModules) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Auto generated code get ID
     *
     * @return String
     */
    @Override
    public String toString() {
        return "ejb.entities.CourseModules[id=" + id + "]";
    }

    
    private boolean isCompulsary;

    /**
     * Get the value of isCompulsary
     *
     * @return the value of isCompulsary
     */
    public boolean isIsCompulsary() {
        return isCompulsary;
    }

    /**
     * Set the value of isCompulsary
     *
     * @param isCompulsary new value of isCompulsary
     */
    public void setIsCompulsary(boolean isCompulsary) {
        this.isCompulsary = isCompulsary;
    }
    /**
     *
     * Student has many submissions. A Submission has one student
     */
    @JoinColumn(name = "COURSE_REF", referencedColumnName = "ID")
    @ManyToOne
    private Course course;

    /**
     * Get course method
     *
     * @return Course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Set the course
     *
     * @param course
     */
    public void setCourse(Course course) {
        this.course = course;
    }
    /**
     * Student has many submissions. A Submission has one student
     */
    @JoinColumn(name = "MODULE_REF", referencedColumnName = "ID")
    @ManyToOne
    private Module module;

    /**
     * Get course module method
     *
     * @return Module
     */
    public Module getModule() {
        return module;
    }

    /**
     * Set method to set the course module
     *
     * @param module
     */
    public void setModule(Module module) {
        this.module = module;
    }
}
