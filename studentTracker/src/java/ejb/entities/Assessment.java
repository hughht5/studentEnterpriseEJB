/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author hmh205
 *
 * The Assessment class contains details of assessments for modules that
 * students are enrolled in.
 *
 */
@Entity
public class Assessment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Get the ID of assessment
     *
     * @return long of ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the assessment
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the hash code.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Method asserts that assessment is available??
     *
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assessment)) {
            return false;
        }
        Assessment other = (Assessment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Create a string representation of the assessment object.
     *
     * @return String assessment object
     */
    @Override
    public String toString() {
        return "ejb.entities.Assesment[id=" + id + "]";
    }

     ///////////////////////////hughs code/////////////////

    private int assesmentID;

    /**
     * Get the value of assesmentID
     *
     * @return the value of assesmentID
     */
    public int getAssesmentID() {
        return assesmentID;
    }

    /**
     * Set the value of assesmentID
     *
     * @param assesmentID new value of assesmentID
     */
    public void setAssesmentID(int assesmentID) {
        this.assesmentID = assesmentID;
    }
    private int sequence;

    /**
     * Get the value of sequence
     *
     * @return the value of sequence
     */
    public int getSequence() {
        return sequence;
    }

    /**
     * Set the value of sequence
     *
     * @param sequence new value of sequence
     */
    public void setSequence(int sequence) {
        this.sequence = sequence;
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
    private String type;

    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setType(String type) {
        this.type = type;
    }
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date handout;

    /**
     * Get the value of handout
     *
     * @return the value of handout
     */
    public Date getHandout() {
        return handout;
    }

    /**
     * Set the value of handout
     *
     * @param handout new value of handout
     */
    public void setHandout(Date handout) {
        this.handout = handout;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date handin;

    /**
     * Get the value of handin
     *
     * @return the value of handin
     */
    public Date getHandin() {
        return handin;
    }

    /**
     * Set the value of handin
     *
     * @param handin new value of handin
     */
    public void setHandin(Date handin) {
        this.handin = handin;
    }

    private int duration;

    /**
     * Get the value of duration
     *
     * @return the value of duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Set the value of duration
     *
     * @param duration new value of duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
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
    private float weighting;

    /**
     * Get the value of weighting
     *
     * @return the value of weighting
     */
    public float getWeighting() {
        return weighting;
    }

    /**
     * Set the value of weighting
     *
     * @param weighting new value of weighting
     */
    public void setWeighting(float weighting) {
        this.weighting = weighting;
    }


    /////////////////////////claimed///////////////


    //micmo relationships
    //Assessments have many submissions. A submission has one assessment
    @OneToMany(mappedBy = "assessment", fetch=FetchType.EAGER)
    private Collection<Submission> listOfSubmissions;

    /**
     * Get the list of submissions
     *
     * @return Collection<Submission>
     */
    public Collection<Submission> getListOfSubmissions()
    {
        return listOfSubmissions;
    }

    /**
     * Set the list of submissions
     *
     * @param listOfSubmissions
     */
    public void setListOfSubmissions(Collection<Submission> listOfSubmissions)
    {
        this.listOfSubmissions = listOfSubmissions;
    }

    //Modules have many Assessments. An assessment has only one module
    @JoinColumn(name = "MODULE_REF", referencedColumnName = "ID")
    @ManyToOne
    private Module module;

    /**
     * Get the course module
     *
     * @return Module
     */
    public Module getModule()
    {
        return module;
    }

    /**
     * Set the course module
     * 
     * @param module
     */
    public void setModule(Module module)
    {
        this.module = module;
    }
    //micmo end
}
