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
 * The EnrolledModules details the students enrolled on courses
 */
@Entity
public class EnrolledModules implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Auto generated method
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Auto generated method
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Auto generated method
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
     * Auto generated method
     *
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnrolledModules)) {
            return false;
        }
        EnrolledModules other = (EnrolledModules) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Auto generated method
     *
     * @return String
     */
    @Override
    public String toString() {
        return "ejb.entities.EnrolledModules[id=" + id + "]";
    }
    /**
     * Student has many enrolled modules. An enrolled module has one student
     */
    @JoinColumn(name = "STUDENT_ENROLLED_MODULES_REF", referencedColumnName = "ID")
    @ManyToOne
    private Student student;

    /**
     * Get the student
     *
     * @return Student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Set the value of the student
     *
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;
    }
    /**
     * A courseModule has many enrollments. Each enrollment has one course module
     */
    @JoinColumn(name = "MODULE_REF", referencedColumnName = "ID")
    @ManyToOne
    private Module courseModule;

    /**
     * Get the course module
     *
     * @return Module
     */
    public Module getCourseModule() {
        return courseModule;
    }

    /**
     * Set the Course Module
     * 
     * @param courseModule
     */
    public void setCourseModule(Module courseModule) {
        this.courseModule = courseModule;
    }
}
