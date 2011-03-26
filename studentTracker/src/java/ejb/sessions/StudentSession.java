/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessions;

import java.sql.Date;
import javax.ejb.Stateless;
import ejb.entities.Student;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hmh205
 */
@Stateless
public class StudentSession implements StudentSessionRemote {

    @PersistenceContext
    EntityManager manager;

    @Override
    public Boolean addStudent(int candidateNum, int studentNum, String emailID, String name, Date dob) {

        try {
            Student student = new Student();
            student.setCandidateNumber(candidateNum);
            student.setStudentNumber(studentNum);
            student.setEmailID(emailID);
            student.setName(name);
            student.setDateOfBirth(dob);
            manager.persist(student);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    @Override
    public boolean removeStudent(Student _student) {
        try{
            manager.remove(manager.find(Student.class, _student.getId()));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")


}
