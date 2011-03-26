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
public class old_Tutor implements Serializable {
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
        if (!(object instanceof old_Tutor)) {
            return false;
        }
        old_Tutor other = (old_Tutor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.entities.Tutors[id=" + id + "]";
    }


    //Micmo data relationships
    @JoinColumn(name = "STUDENT_REF", referencedColumnName = "ID")
    @ManyToOne
    private Student student;
    public Student getStudent()
    {
        return student;
    }
    public void setStudent(Student _student)
    {
        this.student = _student;
    }


    @JoinColumn(name = "STAFF_REF", referencedColumnName = "ID")
    @ManyToOne
    private Staff staff;
    public Staff getStaff()
    {
        return staff;
    }
    public void setStaff(Staff _staff)
    {
        this.staff = _staff;
    }
    //Micmo end

}
