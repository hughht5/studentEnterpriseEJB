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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author hmh205
 */
@Entity
public class EnrolledModules implements Serializable {
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
        if (!(object instanceof EnrolledModules)) {
            return false;
        }
        EnrolledModules other = (EnrolledModules) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.entities.EnrolledModules[id=" + id + "]";
    }


    ///////////////////////////hughs code/////////////////


    private int enrollmentID;

    /**
     * Get the value of enrollmentID
     *
     * @return the value of enrollmentID
     */
    public int getEnrollmentID() {
        return enrollmentID;
    }

    /**
     * Set the value of enrollmentID
     *
     * @param enrollmentID new value of enrollmentID
     */
    public void setEnrollmentID(int enrollmentID) {
        this.enrollmentID = enrollmentID;
    }
    private String studentID;

    /**
     * Get the value of studentID
     *
     * @return the value of studentID
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Set the value of studentID
     *
     * @param studentID new value of studentID
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    private int coureseModuleID;

    /**
     * Get the value of coureseModuleID
     *
     * @return the value of coureseModuleID
     */
    public int getCoureseModuleID() {
        return coureseModuleID;
    }

    /**
     * Set the value of coureseModuleID
     *
     * @param coureseModuleID new value of coureseModuleID
     */
    public void setCoureseModuleID(int coureseModuleID) {
        this.coureseModuleID = coureseModuleID;
    }


    /////////////////////////claimed///////////////

    //Student has many submissions. A Submission has one student
    @JoinColumn(name = "STUDENT_ENROLLED_MODULES_REF", referencedColumnName = "ID")
    @ManyToOne
    private Student student;
    public Student getStudent()
    {
        return student;
    }
    public void setStudent(Student student)
    {
        this.student = student;
    }

}
