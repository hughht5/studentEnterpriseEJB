/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.sessions;

import java.sql.Date;
import javax.ejb.Stateless;
import ejb.entities.Student;

/**
 *
 * @author hmh205
 */
@Stateless
public class StudentSession implements StudentSessionRemote {

    @Override
    public boolean addStudent(int candidateNum, int studentNum, String emailID, String name, Date dob) {
        Student student = new Student();
        student.setCandidateNumber(candidateNum);
        student.setStudentNumber(studentNum);
        student.setEmailID(emailID);
        student.setName(name);
        student.setDateOfBirth(dob);
        return true;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
}
