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
 * The prerequisites class details previous module completion needed by students
 * to progress to desired course module.
 */
@Entity
public class Prerequisites implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Auto generated code
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Auto generated code
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Auto generated code
     *
     * @return int hash code
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
        if (!(object instanceof Prerequisites)) {
            return false;
        }
        Prerequisites other = (Prerequisites) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Auto generated code
     *
     * @return String
     */
    @Override
    public String toString() {
        return "ejb.entities.Prerequisites[id=" + id + "]";
    }

    /**
     * Unique identifier for prerequisite ID
     */
    private int prerequisiteID;

    /**
     * Get the value of prerequisiteID
     *
     * @return the value of prerequisiteID
     */
    public int getPrerequisiteID() {
        return prerequisiteID;
    }

    /**
     * Set the value of prerequisiteID
     *
     * @param prerequisiteID new value of prerequisiteID
     */
    public void setPrerequisiteID(int prerequisiteID) {
        this.prerequisiteID = prerequisiteID;
    }

    /**
     * Unique identifier String for module ID
     */
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

    /**
     * unique identifier String for prerequisite module ID
     */
    private String prerequisiteModuleID;

    /**
     * Get the value of prerequisiteModuleID
     *
     * @return the value of prerequisiteModuleID
     */
    public String getPrerequisiteModuleID() {
        return prerequisiteModuleID;
    }

    /**
     * Set the value of prerequisiteModuleID
     *
     * @param prerequisiteModuleID new value of prerequisiteModuleID
     */
    public void setPrerequisiteModuleID(String prerequisiteModuleID) {
        this.prerequisiteModuleID = prerequisiteModuleID;
    }


    /**
     * Modules have many prereqs (other modules). A prereq may belong to many modules
     */
    @JoinColumn(name = "MODULE_REF", referencedColumnName = "ID")
    @ManyToOne
    private Module prereq;

    /**
     * Get method to return pre-requisite module
     *
     * @return Module
     */
    public Module getPreReq()
    {
        return prereq;
    }

    /**
     * Set method to set the prerequisite key
     *
     * @param prereq
     */
    public void setPreReq(Module prereq)
    {
        this.prereq = prereq;
    }

}
