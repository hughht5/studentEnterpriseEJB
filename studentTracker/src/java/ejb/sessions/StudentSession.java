/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessions;

import ejb.entities.Staff;
import java.sql.Date;
import javax.ejb.Stateless;
import ejb.entities.Student;
import java.util.List;
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
            manager.remove(manager.find(Student.class, _student.getEmailID()));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Student getStudentByEmailID(String _emailID) {
        List<Student> students;
        try{
            String query = "SELECT student FROM Student as student";

            students = manager.createQuery(query).getResultList();

            for(Student s : students)
            {
               if(s.getEmailID().equals(_emailID))
                   return s;
            }
            return null;
        } catch (Exception e) {
            System.out.println("++getStudentByEmailID ERROR: "+e);
            return null;
        }
    }

    public Boolean addTutor(Student _student, Staff _tutor) {
        System.out.println("++addTutor("+_student.getEmailID()+", "+_tutor.getEmailID()+")");
        _student.setTutor(_tutor);
        manager.merge(_student);
        System.out.println("++tutor that has been added: "+_student.getTutor().getEmailID());
        return true;

    }

    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")


}
