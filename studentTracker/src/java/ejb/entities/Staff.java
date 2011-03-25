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
public class Staff implements Serializable {
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
        if (!(object instanceof Staff)) {
            return false;
        }
        Staff other = (Staff) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.entities.Staff[id=" + id + "]";
    }


     ///////////////////////////hughs code/////////////////
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


    /////////////////////////claimed///////////////

    //micmo relationships

    //Student has one Tutor. Tutor has many Tutees.
    @OneToMany(mappedBy = "tutor", fetch=FetchType.EAGER)
    private Collection<Student> listOfTutees;

    public Collection<Student> getListOfTutees()
    {
        return listOfTutees;
    }
    public void setListOfTutees(Collection<Student> listOfTutees)
    {
        this.listOfTutees = listOfTutees;
    }
    //micmo end


    // alex start

    //The staff has many lecturers. A lecturer is only part of one staff.
    @OneToMany(mappedBy = "staff", fetch=FetchType.EAGER)
    private Collection<Lecture> listOfLecturers;

    public Collection<Lecture> getListOfLecturers()
    {
        return listOfLecturers;
    }
    public void setListLecturers(Collection<Lecture> listOfLecturers)
    {
        this.listOfLecturers = listOfLecturers;
    }

    // alex end

}
