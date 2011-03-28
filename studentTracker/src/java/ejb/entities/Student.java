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
 * The Student class details all students enrolled on the studentTrackerEJB,
 * provides methods to add students, and details list of enrolled modules
 */
@Entity
public class Student implements Serializable {

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
     * @return int hash code
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
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
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
        return "ejb.entities.Student[id=" + id + "]";
    }
    /**
     * Unique string identifier name
     */
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
    /**
     * Import DATE to allow date of birth for student
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBirth;

    /**
     * Get the value of dateOfBirth
     *
     * @return the value of dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Set the value of dateOfBirth
     *
     * @param dateOfBirth new value of dateOfBirth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    /**
     * Unique identifier candidate number
     */
    private int candidateNumber;

    /**
     * Get the value of candidateNumber
     *
     * @return the value of candidateNumber
     */
    public int getCandidateNumber() {
        return candidateNumber;
    }

    /**
     * Set the value of candidateNumber
     *
     * @param candidateNumber new value of candidateNumber
     */
    public void setCandidateNumber(int candidateNumber) {
        this.candidateNumber = candidateNumber;
    }
    /**
     * Unique identifier student number
     */
    private int studentNumber;

    /**
     * Get the value of studentNumber
     *
     * @return the value of studentNumber
     */
    public int getStudentNumber() {
        return studentNumber;
    }

    /**
     * Set the value of studentNumber
     *
     * @param studentNumber new value of studentNumber
     */
    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }
    /**
     * Unique identifier email address
     */
    private String emailID;

    /**
     * Get the value of emailID
     *
     * @return the value of emailID
     */
    public String getEmailID() {
        return emailID;
    }

    /**
     * Set the value of emailID
     *
     * @param emailID new value of emailID
     */
    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }
    private String password;

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Student has one Tutor. Tutor has many Tutees.
     */
    @JoinColumn(name = "TUTOR_REF", referencedColumnName = "ID")
    @ManyToOne
    private Staff tutor;

    /**
     * Get method to return student's tutor
     *
     * @return Staff
     */
    public Staff getTutor() {
        return tutor;
    }

    /**
     * Set method to set student's tutor
     *
     * @param tutor
     */
    public void setTutor(Staff tutor) {
        this.tutor = tutor;
    }
    /**
     * Student has many submissions. A Submission only has one student
     */
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private Collection<Submission> listOfSubmissions;

    /**
     * Get method to return list of student's submissions
     *
     * @return Collection<Submission>
     *
     */
    public Collection<Submission> getListOfSubmissions() {
        return listOfSubmissions;
    }

    /**
     * Set method to set the student's list of submissions
     *
     * @param listOfSubmissions
     */
    public void setListOfSubmissions(Collection<Submission> listOfSubmissions) {
        this.listOfSubmissions = listOfSubmissions;
    }
    /**
     * Student has one course. A course has many students.
     */
    @JoinColumn(name = "COURSE_REF", referencedColumnName = "ID")
    @ManyToOne
    private Course course;

    /**
     * Get method to get the students course
     *
     * @return Course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Set method to set the students course
     *
     * @param course
     */
    public void setCourse(Course course) {
        this.course = course;
    }
    /**
     * Student is enrolled to many modules. An enrolled module only has one student
     */
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private Collection<EnrolledModules> listOfEnrolledModules;

    /**
     * Get method to get list of student's enrolled modules
     *
     * @return Collection<EnrolledModules>
     */
    public Collection<EnrolledModules> getListOfEnrolledModules() {
        return listOfEnrolledModules;
    }

    /**
     * Set method to set the student's list of enrolled modules
     * 
     * @param listOfEnrolledModules
     */
    public void setListEnrolledModules(Collection<EnrolledModules> listOfEnrolledModules) {
        this.listOfEnrolledModules = listOfEnrolledModules;
    }
    ///hugh and alex end
}
