/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author mm336
 */
@Entity
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.entities.User[id=" + id + "]";
    }



    //micmo start
    private String username;

    /**
     * Get the value of username (emailID)
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username (emailID)
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    protected String password;

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
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Set the value of isAdmin
     *
     * @param isAdmin new value of isAdmin
     */
    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
    }
    private boolean isStaff;

    /**
     * Get the value of isStaff
     *
     * @return the value of isStaff
     */
    public boolean isStaff() {
        return isStaff;
    }

    /**
     * Set the value of isStaff
     *
     * @param isStaff new value of isStaff
     */
    public void setStaff(boolean staff) {
        this.isStaff = staff;
    }



    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="STUDENT_ID")
    private Student student;

    public Student getStudent()
    {
        return student;
    }
    public void setStudent(Student _student)
    {
        this.student = _student;
    }

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="STAFF_ID")
    private Staff staff;

    public Staff getStaff()
    {
        return staff;
    }
    public void setStaff(Staff _staff)
    {
        this.staff = _staff;
    }

    //micmo end

}
