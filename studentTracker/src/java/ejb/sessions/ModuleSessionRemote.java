/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessions;

import ejb.entities.Assessment;
import ejb.entities.Course;
import ejb.entities.CourseModules;
import ejb.entities.Module;
import ejb.entities.Prerequisites;
import ejb.entities.Student;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author hmh205
 */
@Remote
public interface ModuleSessionRemote {

    boolean addModule(String moduleID, String name, int credits, float AVGMark, String stage, Collection<Module> prerequisites);

    boolean removeModule(Module module);

    boolean addModuleToCourse(Module _module, Course _course, boolean _isCompulsary);

    Module getModuleByID(String _ID);

    boolean removeModuleFromCourse(CourseModules _courseModule);

    List<Module> getListOfAllModules();

    boolean addAssessmentToModule(int _seq, String _type,
            Date _handout, Date _handin, int _duration, float _weighting, Module _module, String _staffEmailID);

    Collection<Student> getListOfEnrolledStudents(String _moduleID);

    Collection<Assessment> getAssessmentsForModule(String _moduleID);

    Float getAverageAssessmentMark(String _moduleID, int _assessmentSequence);

    Assessment getAssessmentForModule(String _moduleID, int _sequence);


    boolean checkIfCoordinator(String _staffEmailID, String _moduleID);
}
