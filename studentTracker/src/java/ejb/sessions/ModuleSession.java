/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessions;

import ejb.entities.Assessment;
import ejb.entities.Course;
import ejb.entities.CourseModules;
import ejb.entities.EnrolledModules;
import ejb.entities.Module;
import ejb.entities.Prerequisites;
import ejb.entities.Student;
import ejb.entities.Submission;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hmh205
 */
@Stateless
public class ModuleSession implements ModuleSessionRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager manager;

    @Override
    public boolean addModule(String moduleID, String name, int credits, float avgMark, String stage, Collection<Module> prerequisites) {
        try {
            Module module = new Module();
            module.setModuleID(moduleID);
            module.setStage(stage);
            module.setName(name);
            module.setCredits(credits);
            module.setAvgMark(avgMark);
            module.setListOfPrereqs(prerequisites);
            manager.persist(module);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean removeModule(Module module) {
        try {
            manager.remove(manager.find(Module.class, module.getId()));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean addModuleToCourse(Module _module, Course _course, boolean _isCompulsary) {
        try {
            CourseModules courseModule = new CourseModules();
            courseModule.setCourse(manager.find(Course.class, _course.getId()));
            courseModule.setModule(manager.find(Module.class, _module.getId()));
            courseModule.setIsCompulsary(_isCompulsary);
            manager.persist(courseModule);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Module getModuleByID(String _ID) {
         List<Module> modules;
        try{
            String query = "SELECT module FROM Module as module";

            modules = manager.createQuery(query).getResultList();

            for(Module m : modules)
            {
               if(m.getModuleID().equals(_ID))
                   return m;
            }
            return null;
        } catch (Exception e) {
            System.out.println("getCourseByID ERROR: "+e);
            return null;
        }
    }

    @Override
    public boolean removeModuleFromCourse(CourseModules _courseModule) {
        try {
            manager.remove(manager.find(CourseModules.class, _courseModule.getId()));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Module> getListOfAllModules() {
         List<Module> modules;
        try{
            String query = "SELECT module FROM Module as module";

            modules = manager.createQuery(query).getResultList();
            return modules;
        } catch (Exception e) {
            System.out.println("getListOfAllModules ERROR: "+e);
            return null;
        }
    }


    @Override
    public boolean addAssessmentToModule(int _seq, String _type,
            Date _handout, Date _handin, int _duration, float _weighting, Module _module) {
        try {
            Assessment ass = new Assessment();

            if(_handin.before(_handout))
                throw new Exception("Handin cannot be before the handout date");

            if(_duration < 0)
                throw new Exception("Duration time must be > 0");

            if(_weighting < 0 || _weighting > 1)
                throw new Exception("Weighting must be between 0.00 and 1.00");
            
            ass.setSequence(_seq);
            ass.setType(_type);
            ass.setHandout(_handout);
            ass.setHandin(_handin);
            ass.setDuration(_duration);
            ass.setAvgMark(0);
            ass.setWeighting(_weighting);
            ass.setModule(_module);
            
            manager.persist(ass);
        } catch (Exception e) {
            return false;
        }
        return true;
    }



    public Collection<Student> getListOfEnrolledStudents(String _moduleID) {
        Collection<EnrolledModules> modules;
        Collection<Student> enrolledStudents = new ArrayList();
        try{
            String query = "SELECT modules FROM EnrolledModules as modules";

            modules = manager.createQuery(query).getResultList();

            for(EnrolledModules m : modules)
            {
                if(m.getCourseModule().getModuleID().equals(_moduleID))
                        enrolledStudents.add(m.getStudent());
            }

            return enrolledStudents;
        } catch (Exception e) {
            System.out.println("getListOfEnrolledStudents ERROR: "+e);
            return null;
        }
    }

    public Collection<Assessment> getAssessmentsForModule(String _moduleID) {
         Collection<Assessment> allAssessments;
         Collection<Assessment> assessments = new ArrayList();
        try{
            String query = "SELECT assessment FROM Assessment as assessment";

            allAssessments = manager.createQuery(query).getResultList();

            for(Assessment a : allAssessments)
            {
                if(a.getModule().getModuleID().equals(_moduleID))
                    assessments.add(a);
            }

            return assessments;
        } catch (Exception e) {
            System.out.println("getListOfAllModules ERROR: "+e);
            return null;
        }
    }

    public Assessment getAssessmentForModule(String _moduleID, int _sequence)
    {
        Collection<Assessment> assessments;
        try{
            String query = "SELECT assessment FROM Assessment as assessment";

            assessments = manager.createQuery(query).getResultList();

            for(Assessment a : assessments)
            {
                if(a.getModule().getModuleID().equals(_moduleID))
                    return a;
            }

            return null;
        } catch (Exception e) {
            System.out.println("getAssessmentForModule ERROR: "+e);
            return null;
        }
    }

    public Float getAverageAssessmentMark(String _moduleID, int _assessmentSequence) {
        Collection<Submission> submissions;
        try{
            String query = "SELECT submission FROM Submission as submission";

            submissions = manager.createQuery(query).getResultList();
            int totalMarks = 0;
            int numOfSubmissions = 0;
            float averageMark = 0;

            for(Submission s : submissions)
            {
                if(s.getAssessment().getModule().getModuleID().equals(_moduleID)
                        && (s.getAssessment().getSequence() == _assessmentSequence))
                {
                    totalMarks += s.getMark();
                    numOfSubmissions ++;
                }
            }

            if(numOfSubmissions>0)
                averageMark = totalMarks / numOfSubmissions;
            else
                averageMark = 0;

            Assessment assessment = getAssessmentForModule(_moduleID, _assessmentSequence);

            assessment.setAvgMark(averageMark);
            manager.merge(assessment);

            return averageMark;
        } catch (Exception e) {
            System.out.println("getAverageAssessmentMark ERROR: "+e);
            return 0F;
        }
    }


    

}
