/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessions;

import ejb.entities.Assessment;
import ejb.entities.Course;
import ejb.entities.CourseModules;
import ejb.entities.Module;
import ejb.entities.Student;
import ejb.entities.Submission;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;

/**
 * This is the remote interface for the Module Session.
 *
 * The Module session contains methods related to modules and assessments
 * and methods related to marking and retrieving marks.
 *
 */
@Remote
public interface ModuleSessionRemote {

    boolean addModule(String moduleID, String name, int credits,
            String stage, Collection<Module> prerequisites);

    boolean removeModule(Module module);

    Module getModuleByID(String _ID);

    List<Module> getListOfAllModules();

    boolean addModuleToCourse(Module _module, Course _course, boolean _isCompulsary);

    boolean removeModuleFromCourse(CourseModules _courseModule);

    Collection<Student> getListOfEnrolledStudents(String _moduleID, String _staffEmailID);

    boolean addAssessmentToModule(int _seq, String _type,
            Date _handout, Date _handin, int _duration, float _weighting,
            Module _module, String _staffEmailID);

    Assessment getAssessmentForModule(String _moduleID, int _sequence);

    Collection<Assessment> getAssessmentsForModule(String _moduleID);

    Float getAverageAssessmentMark(String _moduleID, int _assessmentSequence);

    float getAverageModuleMark(String _moduleID);

    float getModuleMark(String _moduleID, String _studentEmailID);

    Collection<Submission> getSubmissions(String _moduleID, int _sequence);

    boolean checkIfCoordinator(String _staffEmailID, String _moduleID);

    boolean checkIfLecturer(String _staffEmailID, String _moduleID);
}
