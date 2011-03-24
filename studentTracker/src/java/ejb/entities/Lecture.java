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
public class Lecture implements Serializable {
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
        if (!(object instanceof Lecture)) {
            return false;
        }
        Lecture other = (Lecture) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.entities.Lecture[id=" + id + "]";
    }


     ///////////////////////////hughs code/////////////////

    private int lectureID;

    /**
     * Get the value of lectureID
     *
     * @return the value of lectureID
     */
    public int getLectureID() {
        return lectureID;
    }

    /**
     * Set the value of lectureID
     *
     * @param lectureID new value of lectureID
     */
    public void setLectureID(int lectureID) {
        this.lectureID = lectureID;
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
    private String moduleID;

    /**
     * Get the value of moduleID
     *
     * @return the value of moduleID
     */
    public String getModuleID() {
        return moduleID;
    }

    /**
     * Set the value of moduleID
     *
     * @param moduleID new value of moduleID
     */
    public void setModuleID(String moduleID) {
        this.moduleID = moduleID;
    }

    private boolean isCoordinator;

    /**
     * Get the value of isCoordinator
     *
     * @return the value of isCoordinator
     */
    public boolean isIsCoordinator() {
        return isCoordinator;
    }

    /**
     * Set the value of isCoordinator
     *
     * @param isCoordinator new value of isCoordinator
     */
    public void setIsCoordinator(boolean isCoordinator) {
        this.isCoordinator = isCoordinator;
    }

    /////////////////////////claimed///////////////
}
