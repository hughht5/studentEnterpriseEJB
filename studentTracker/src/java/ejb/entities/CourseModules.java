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
 */
@Entity
public class CourseModules implements Serializable {
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
        if (!(object instanceof CourseModules)) {
            return false;
        }
        CourseModules other = (CourseModules) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.entities.CourseModules[id=" + id + "]";
    }

     ///////////////////////////hughs code/////////////////

    private int courseModuleID;

    /**
     * Get the value of courseModuleID
     *
     * @return the value of courseModuleID
     */
    public int getCourseModuleID() {
        return courseModuleID;
    }

    /**
     * Set the value of courseModuleID
     *
     * @param courseModuleID new value of courseModuleID
     */
    public void setCourseModuleID(int courseModuleID) {
        this.courseModuleID = courseModuleID;
    }
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

    /////////////////////////claimed///////////////

    //hugh
    //CourseModules have many enrollments. An enrolled module only has one course module
    @OneToMany(mappedBy = "courseModule", fetch=FetchType.EAGER)
    private Collection<EnrolledModules> listOfEnrolledModules;

    public Collection<EnrolledModules> getListOfEnrolledModules()
    {
        return listOfEnrolledModules;
    }
    public void setListEnrolledModules(Collection<EnrolledModules> listOfEnrolledModules)
    {
        this.listOfEnrolledModules = listOfEnrolledModules;
    }

    ///hugh end

}
