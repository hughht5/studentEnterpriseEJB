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
public class Module implements Serializable {
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
        if (!(object instanceof Module)) {
            return false;
        }
        Module other = (Module) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.entities.Module[id=" + id + "]";
    }
 ///////////////////////////hughs code/////////////////
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
    private String name;

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
    private int credits;

    /**
     * Get the value of credits
     *
     * @return the value of credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Set the value of credits
     *
     * @param credits new value of credits
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }
    private float avgMark;

    /**
     * Get the value of avgMark
     *
     * @return the value of avgMark
     */
    public float getAvgMark() {
        return avgMark;
    }

    /**
     * Set the value of avgMark
     *
     * @param avgMark new value of avgMark
     */
    public void setAvgMark(float avgMark) {
        this.avgMark = avgMark;
    }

    /////////////////////////claimed///////////////
}
