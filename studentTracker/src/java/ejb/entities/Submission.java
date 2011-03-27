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
 * @author hmh205
 */
@Entity
public class Submission implements Serializable {

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
        if (!(object instanceof Submission)) {
            return false;
        }
        Submission other = (Submission) object;
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
        return "ejb.entities.submission[id=" + id + "]";
    }
    /**
     * Unique identifier for submission ID
     */
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
    private boolean isLate;

    /**
     * Get the value of isLate
     *
     * @return the value of isLate
     */
    public boolean getIsLate() {
        return isLate;
    }

    /**
     * Set the value of isLate
     *
     * @param isLate new value of isLate
     */
    public void setIsLate(boolean isLate) {
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

    private String feedback;

    /**
     * Get the value of feedback
     *
     * @return the value of feedback
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * Set the value of feedback
     *
     * @param feedback new value of feedback
     */
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    /**
     * Student has many submissions. A Submission has one student
     */
    @JoinColumn(name = "STUDENTSUBMISSION_REF", referencedColumnName = "ID")
    @ManyToOne
    private Student student;

    /**
     * Get method to get a student's that has submitted
     *
     * @return Student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Set method to set student's that have submitted an assignment
     *
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;
    }
    /**
     * Assessments have many submissions. A submission has one assessment
     */
    @JoinColumn(name = "ASSESSMENTSUBMISSION_REF", referencedColumnName = "ID")
    @ManyToOne
    private Assessment assessment;

    /**
     * Get method to get a students assessment
     *
     * @return Assessment
     */
    public Assessment getAssessment() {
        return assessment;
    }

    /**
     * Set method to set the assessment for a module
     *
     * @param assessment
     */
    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }
}
