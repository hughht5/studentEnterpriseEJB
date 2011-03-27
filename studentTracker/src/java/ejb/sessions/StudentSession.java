/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessions;

import ejb.entities.Assessment;
import ejb.entities.Course;
import ejb.entities.EnrolledModules;
import ejb.entities.Module;
import ejb.entities.Staff;
import java.sql.Date;
import javax.ejb.Stateless;
import ejb.entities.Student;
import ejb.entities.Submission;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This class implements the business logic for methods described in the
 * StudentSessionRemote interface.
 */
@Stateless
public class StudentSession implements StudentSessionRemote {

    @PersistenceContext
    EntityManager manager;

    /**
     * Method to add a new student to the system
     * @param candidateNum
     * @param studentNum
     * @param emailID
     * @param name
     * @param dob
     * @param password
     * @return true for success, false otherwise
     */
    @Override
    public Boolean addStudent(int candidateNum, int studentNum, String emailID, String name, Date dob, String password) {

        try {
            Student student = new Student();
            student.setCandidateNumber(candidateNum);
            student.setStudentNumber(studentNum);
            student.setEmailID(emailID);
            student.setName(name);
            student.setDateOfBirth(dob);
            student.setPassword(password);

            manager.persist(student);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Method to remove a student from the system
     * @param _student
     * @return true for success, false otherwise
     */
    @Override
    public boolean removeStudent(Student _student) {
        try {
            manager.remove(manager.find(Student.class, _student.getEmailID()));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Get a student by their emailID
     * @param _emailID
     * @return the Student object if found, false otherwise
     */
    @Override
    public Student getStudentByEmailID(String _emailID) {
        List<Student> students;
        try {
            String query = "SELECT student FROM Student as student";

            students = manager.createQuery(query).getResultList();

            for (Student s : students) {
                if (s.getEmailID().equals(_emailID)) {
                    return s;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("++getStudentByEmailID ERROR: " + e);
            return null;
        }
    }

    /**
     * Add a member of staff as a student's tutor
     * @param _student
     * @param _tutor
     * @return true for success, false otherwise
     */
    @Override
    public Boolean addTutor(Student _student, Staff _tutor) {
        try {
            _student.setTutor(_tutor);
            manager.merge(_student);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Ensure that the student's username and password are correct
     * @param _username
     * @param _password
     * @return true if correct, false otherwise
     */
    @Override
    public boolean checkStudentLogin(String _username, String _password) {
        List<Student> student;
        try {
            String query = "SELECT student FROM Student as student";

            student = manager.createQuery(query).getResultList();

            for (Student s : student) {
                if (s.getEmailID().equals(_username) && s.getPassword().equals(_password)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("checkStudentLogin ERROR: " + e);
            return false;
        }
    }

    /**
     * Enroll a Student on a Module
     * @param _modules
     * @param _student
     * @return true for success, false otherwise
     */
    @Override
    public boolean enrollStudentOnModule(Collection<Module> _modules, Student _student) {
        try {
            for (Module m : _modules) {
                EnrolledModules enrollement = new EnrolledModules();
                enrollement.setStudent(manager.find(Student.class, _student.getId()));
                enrollement.setCourseModule(manager.find(Module.class, m.getId()));
                manager.persist(enrollement);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Enroll a Student on a Course
     * @param _student
     * @param _course
     * @return true for success, false otherwise
     */
    @Override
    public boolean enrollStudentOnCourse(Student _student, Course _course) {
        try {
            _student.setCourse(_course);
            manager.merge(_student);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Submit an Assessment for a particular Student
     * @param _student
     * @param _assesment
     * @return true for success, false otherwise
     */
    @Override
    public boolean submitAssessment(Student _student, Assessment _assessment) {
        try {
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();
            java.sql.Date date = new java.sql.Date(currentDate.getTime());

            Submission submission = new Submission();
            submission.setStudent(_student);
            submission.setAssessment(_assessment);
            submission.setMark(0);
            submission.setFeedback("");
            
            if(date.after(_assessment.getHandin()))
                submission.setIsLate(true);
            else
                submission.setIsLate(false);
            
            manager.persist(submission);
        } catch (Exception e) {
            System.out.println("submitAssessment error : "+e);
            return false;
        }
        return true;
    }

    
    @Override
    public Collection<Module> getModulesEnrolledOn(String _emailID) {
        Student student = getStudentByEmailID(_emailID);
        Collection<Student> students;

        Collection<EnrolledModules> enrolledModules = null;
        Collection<Module> modules = new ArrayList();
        try{
            String query = "SELECT modules FROM EnrolledModules as modules";

            enrolledModules = manager.createQuery(query).getResultList();

            for(EnrolledModules m : enrolledModules)
            {
                if(m.getStudent().equals(student))
                    modules.add(m.getCourseModule());
            }

            return modules;

        } catch (Exception e) {
            System.out.println("getModulesEnrolledOn ERROR: "+e);
            return null;
        }
    }

    public Submission getSpecificSubmission(String _moduleID, int _sequence, String _studentEmailID) {
        Collection<Submission> allSubmissions;
        try{
            String query = "SELECT submission FROM Submission as submission";

            allSubmissions = manager.createQuery(query).getResultList();

            for(Submission s : allSubmissions)
            {
                if(s.getAssessment().getModule().getModuleID().equals(_moduleID)
                        && s.getAssessment().getSequence()==_sequence)
                    return s;
            }

            return null;
        } catch (Exception e) {
            System.out.println("getSpecificSubmission ERROR: "+e);
            return null;
        }
    }

    public float getSubmissionMark(Submission _submission) {
        float mark = _submission.getMark();
        if(_submission.getIsLate())
        {
            if(_submission.getAssessment().getModule().getStage().equals("M"))
            {
                if(mark>=50)
                    mark=50;
            }else{
                if(mark>=40)
                    mark=40;
            }
        }
        return mark;
    }

    

    

}
