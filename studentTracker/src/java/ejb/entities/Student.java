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
 */
@Entity
public class Student implements Serializable {

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
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.entities.Student[id=" + id + "]";
    }
    ///////////////////////////hughs code/////////////////
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

    /////////////////////////claimed///////////////

    //Micmo relationships 

    //Student has one Tutor. Tutor has many Tutees.
    @JoinColumn(name = "STAFF_REF", referencedColumnName = "ID")
    @ManyToOne
    private Staff tutor;
    public Staff getTutor()
    {
        return tutor;
    }
    public void setTutor(Staff tutor)
    {
        this.tutor = tutor;
    }

    //Student has many submissions. A Submission only has one student
    @OneToMany(mappedBy = "student", fetch=FetchType.EAGER)
    private Collection<Submission> listOfSubmissions;

    public Collection<Submission> getListOfSubmissions()
    {
        return listOfSubmissions;
    }
    public void setListOfSubmissions(Collection<Submission> listOfSubmissions)
    {
        this.listOfSubmissions = listOfSubmissions;
    }

    //Student has one course. A course has many students.
    @JoinColumn(name = "COURSE_REF", referencedColumnName = "ID")
    @ManyToOne
    private Course course;
    public Course getCourse()
    {
        return course;
    }
    public void setCourse(Course course)
    {
        this.course = course;
    }
    //micmo end

    //hugh and alex
    //Student is enrolled to many modules. An enrolled module only has one student
    @OneToMany(mappedBy = "student", fetch=FetchType.EAGER)
    private Collection<EnrolledModules> listOfEnrolledModules;

    public Collection<EnrolledModules> getListOfEnrolledModules()
    {
        return listOfEnrolledModules;
    }
    public void setListEnrolledModules(Collection<EnrolledModules> listOfEnrolledModules)
    {
        this.listOfEnrolledModules = listOfEnrolledModules;
    }
    
    ///hugh and alex end
}
