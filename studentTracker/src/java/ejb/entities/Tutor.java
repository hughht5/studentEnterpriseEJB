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

/**
 *
 * @author hmh205
 */
@Entity
public class Tutor implements Serializable {
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
        if (!(object instanceof Tutor)) {
            return false;
        }
        Tutor other = (Tutor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.entities.Tutors[id=" + id + "]";
    }

     ///////////////////////////hughs code/////////////////
    private int tutorID;

    /**
     * Get the value of tutorID
     *
     * @return the value of tutorID
     */
    public int getTutorID() {
        return tutorID;
    }

    /**
     * Set the value of tutorID
     *
     * @param tutorID new value of tutorID
     */
    public void setTutorID(int tutorID) {
        this.tutorID = tutorID;
    }
    private String studentEmailID;

    /**
     * Get the value of studentEmailID
     *
     * @return the value of studentEmailID
     */
    public String getStudentID() {
        return studentEmailID;
    }

    /**
     * Set the value of studentEmailID
     *
     * @param studentEmailID new value of studentEmailID
     */
    public void setStudentID(String studentID) {
        this.studentEmailID = studentID;
    }

    private String staffEmailID;

    /**
     * Get the value of staffEmailID
     *
     * @return the value of staffEmailID
     */
    public String getStaffEmailID() {
        return staffEmailID;
    }

    /**
     * Set the value of staffEmailID
     *
     * @param staffEmailID new value of staffEmailID
     */
    public void setStaffEmailID(String staffEmailID) {
        this.staffEmailID = staffEmailID;
    }

    /////////////////////////claimed///////////////

}
