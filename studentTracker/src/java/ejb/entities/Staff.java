/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * The Staff class details the methods to find lecturers lectures
 * and corresponding tutees for said lectures.  It also
 * contains all personal and contact details.
 */
@Entity
public class Staff implements Serializable {

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
        if (!(object instanceof Staff)) {
            return false;
        }
        Staff other = (Staff) object;
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
        return "ejb.entities.Staff[id=" + id + "]";
    }
    /**
     * Unique identifier for email ID
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
    private String room;

    /**
     * Get the value of room
     *
     * @return the value of room
     */
    public String getRoom() {
        return room;
    }

    /**
     * Set the value of room
     *
     * @param room new value of room
     */
    public void setRoom(String room) {
        this.room = room;
    }
    protected String phone;

    /**
     * Get the value of phone
     *
     * @return the value of phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set the value of phone
     *
     * @param phone new value of phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**
     * unique identifier String password
     */
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
    private boolean isAdmin;

    /**
     * Get the value of isAdmin
     *
     * @return the value of isAdmin
     */
    public boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * Set the value of isAdmin
     *
     * @param isAdmin new value of isAdmin
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    /**
     * Student has one Tutor. Tutor has many Tutees.
     *
     */
    @OneToMany(mappedBy = "tutor", fetch = FetchType.EAGER)
    private Collection<Student> listOfTutees;

    /**
     * Get method to return list of tutees for the member of staff
     *
     * @return Collection<Student>
     */
    public Collection<Student> getListOfTutees() {
        return listOfTutees;
    }

    /**
     * Set method to set list of tutees for the member of staff
     *
     * @param listOfTutees
     */
    public void setListOfTutees(Collection<Student> listOfTutees) {
        this.listOfTutees = listOfTutees;
    }
    /**
     * The staff has many lecturers. A lecturer is only part of one staff.
     */
    @OneToMany(mappedBy = "staff", fetch = FetchType.EAGER)
    private Collection<Lecture> listOfLecturers;

    /**
     * Get method to return list of lectures
     *
     * @return Collection<Lecture>
     */
    public Collection<Lecture> getListOfLecturers() {
        return listOfLecturers;
    }

    /**
     * Set method to set the list of lecturers
     *
     * @param listOfLecturers
     */
    public void setListLecturers(Collection<Lecture> listOfLecturers) {
        this.listOfLecturers = listOfLecturers;
    }
}
