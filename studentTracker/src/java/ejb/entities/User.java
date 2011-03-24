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
 * @author mm336
 */
@Entity
public class User implements Serializable {
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
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

    private boolean admin;

    /**
     * Get the value of admin
     *
     * @return the value of admin
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Set the value of admin
     *
     * @param admin new value of admin
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    private boolean staff;

    /**
     * Get the value of staff
     *
     * @return the value of staff
     */
    public boolean isStaff() {
        return staff;
    }

    /**
     * Set the value of staff
     *
     * @param staff new value of staff
     */
    public void setStaff(boolean staff) {
        this.staff = staff;
    }

    //micmo end

}
