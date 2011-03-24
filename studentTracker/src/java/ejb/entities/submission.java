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
public class submission implements Serializable {
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
        if (!(object instanceof submission)) {
            return false;
        }
        submission other = (submission) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.entities.submission[id=" + id + "]";
    }


    ///////////////////////////hughs code/////////////////

    private int submissionID;

    /**
     * Get the value of submissionID
     *
     * @return the value of submissionID
     */
    public int getSubmissionID() {
        return submissionID;
    }

    /**
     * Set the value of submissionID
     *
     * @param submissionID new value of submissionID
     */
    public void setSubmissionID(int submissionID) {
        this.submissionID = submissionID;
    }
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
    private float mark;

    /**
     * Get the value of mark
     *
     * @return the value of mark
     */
    public float getMark() {
        return mark;
    }

    /**
     * Set the value of mark
     *
     * @param mark new value of mark
     */
    public void setMark(float mark) {
        this.mark = mark;
    }
    private String isLate;

    /**
     * Get the value of isLate
     *
     * @return the value of isLate
     */
    public String getIsLate() {
        return isLate;
    }

    /**
     * Set the value of isLate
     *
     * @param isLate new value of isLate
     */
    public void setIsLate(String isLate) {
        this.isLate = isLate;
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


    /////////////////////////claimed///////////////

}
