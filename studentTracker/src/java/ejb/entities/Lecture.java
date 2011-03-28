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
 *
 * The Lecture class is a linking class between staff and module,
 * detailing which members of staff lecture for each module.
 */
@Entity
public class Lecture implements Serializable {

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
        if (!(object instanceof Lecture)) {
            return false;
        }
        Lecture other = (Lecture) object;
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
        return "ejb.entities.Lecture[id=" + id + "]";
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
    /**
     * A Staff has many lectures. A lecture has one staff
     */
    @JoinColumn(name = "LECTURER_REF", referencedColumnName = "ID")
    @ManyToOne
    private Staff staff;

    /**
     * Get method to get staff object
     *
     * @return Staff
     */
    public Staff getStaff() {
        return staff;
    }

    /**
     * Set method to set staff
     *
     * @param staff
     */
    public void setStaff(Staff staff) {
        this.staff = staff;
    }
    /**
     * A module has many lecturers. A lecturer has one module.
     */
    @JoinColumn(name = "MODULE_REF", referencedColumnName = "ID")
    @ManyToOne
    private Module module;

    /**
     * Get module method to get modules for lectures
     *
     *
     * @return Module
     */
    public Module getModule() {
        return module;
    }

    /**
     * Set course module
     *
     * @param module
     */
    public void setModule(Module module) {
        this.module = module;
    }
}
