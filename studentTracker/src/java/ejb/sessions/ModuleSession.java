/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessions;

import ejb.entities.Assessment;
import ejb.entities.Course;
import ejb.entities.CourseModules;
import ejb.entities.EnrolledModules;
import ejb.entities.Lecture;
import ejb.entities.Module;
import ejb.entities.Staff;
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
 * This class implements the business logic for methods described in the
 * ModuleSessionRemote interface.
 */
@Stateless
public class ModuleSession implements ModuleSessionRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager manager;

    /**
     * Method to add a new module to the system
     * @param moduleID
     * @param name
     * @param credits
     * @param stage
     * @param prerequisites - a Collection of modules require to take this module
     * @return true for success, false otherwise
     */
    @Override
    public boolean addModule(String moduleID, String name, int credits, String stage, Collection<Module> prerequisites) {
        try {
            Module module = new Module();
            module.setModuleID(moduleID);
            module.setStage(stage);
            module.setName(name);
            module.setCredits(credits);
            module.setAvgMark(0F);
            module.setListOfPrereqs(prerequisites);
            manager.persist(module);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Method to remove a module from the system
     * @param module
     * @return true for success, false otherwise
     */
    @Override
    public boolean removeModule(Module module) {
        try {
            manager.remove(manager.find(Module.class, module.getId()));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Method to add a module to a course
     * @param _module
     * @param _course
     * @param _isCompulsary - true if module is compulsory on specified course
     * @return true for success, false otherwise
     */
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

    /**
     * Method to get a module
     * @param _ID - the module's ID
     * @return The specified module, or null if not found
     */
    @Override
    public Module getModuleByID(String _ID) {
        List<Module> modules;
        try {
            String query = "SELECT module FROM Module as module";

            modules = manager.createQuery(query).getResultList();

            for (Module m : modules) {
                if (m.getModuleID().equals(_ID)) {
                    return m;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("getCourseByID ERROR: " + e);
            return null;
        }
    }

    /**
     * Method to remove a module from a course
     * @param _courseModule
     * @return true for success, false otherwise
     */
    @Override
    public boolean removeModuleFromCourse(CourseModules _courseModule) {
        try {
            manager.remove(manager.find(CourseModules.class, _courseModule.getId()));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Method to get a list of all modules in the system
     * @return a List of Module objects, or null if no module are in the system
     */
    @Override
    public List<Module> getListOfAllModules() {
        List<Module> modules;
        try {
            String query = "SELECT module FROM Module as module";

            modules = manager.createQuery(query).getResultList();
            return modules;
        } catch (Exception e) {
            System.out.println("getListOfAllModules ERROR: " + e);
            return null;
        }
    }

    /**
     * Check if a member of staff is the module coordinator of a particular module
     * @param _staffEmailID
     * @param _moduleID
     * @return true if coordinator, false otherwise
     */
    @Override
    public boolean checkIfCoordinator(String _staffEmailID, String _moduleID) {
        List<Lecture> lectures;
        try {
            String query = "SELECT staff FROM Lecture as staff";

            lectures = manager.createQuery(query).getResultList();

            for (Lecture l : lectures) {
                if (l.getStaff().getEmailID().equals(_staffEmailID)
                        && l.getModule().getModuleID().equals(_moduleID)) {
                    return l.isIsCoordinator();
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("checkIfCoordinator ERROR: " + e);
            return false;
        }
    }

    /**
     * Check if a member of staff is a lecturer for a particular module
     * @param _staffEmailID
     * @param _moduleID
     * @return true if lecturer, false otherwise
     */
    @Override
    public boolean checkIfLecturer(String _staffEmailID, String _moduleID) {
        List<Lecture> lectures;
        try {
            String query = "SELECT staff FROM Lecture as staff";

            lectures = manager.createQuery(query).getResultList();

            for (Lecture l : lectures) {
                if (l.getStaff().getEmailID().equals(_staffEmailID)
                        && l.getModule().getModuleID().equals(_moduleID)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("checkIfLecturer ERROR: " + e);
            return false;
        }
    }

    /**
     * Method to add an assessment to a module. Only module coordinators can do this.
     * @param _seq - an integer representing the order of the assignments. 1 for first, 2 for second...
     * @param _type - Practical, Class test, Presentation, Examination
     * @param _handout - handout date
     * @param _handin - handin date
     * @param _duration - estimated time for completion
     * @param _weighting - weighting of the assessment
     * @param _module - module that the assessment is being added to
     * @param _staffEmailID - the email id of the staff member adding the assessment. Must be a coordinator
     * @return true for success, false otherwise
     */
    @Override
    public boolean addAssessmentToModule(int _seq, String _type,
            Date _handout, Date _handin, int _duration, float _weighting, Module _module, String _staffEmailID) {
        try {

            if (checkIfCoordinator(_staffEmailID, _module.getModuleID())) {
                Assessment ass = new Assessment();

                if (_handin.before(_handout)) {
                    throw new Exception("Handin cannot be before the handout date");
                }

                if (_duration < 0) {
                    throw new Exception("Duration time must be > 0");
                }

                if (_weighting < 0 || _weighting > 1) {
                    throw new Exception("Weighting must be between 0.00 and 1.00");
                }

                ass.setSequence(_seq);
                ass.setType(_type);
                ass.setHandout(_handout);
                ass.setHandin(_handin);
                ass.setDuration(_duration);
                ass.setAvgMark(0);
                ass.setWeighting(_weighting);
                ass.setModule(_module);

                manager.persist(ass);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * Method to get a Collection of all the students enrolled on a module.
     * @param _moduleID - ID of the module
     * @param _staffEmailID - ID of the Staff. Must be a lecturer for specified module
     * @return A Collection of Student objects, or null if no students in the system
     */
    @Override
    public Collection<Student> getListOfEnrolledStudents(String _moduleID, String _staffEmailID) {
        Collection<EnrolledModules> modules;
        Collection<Student> enrolledStudents = new ArrayList();
        try {

            if (checkIfLecturer(_staffEmailID, _moduleID)) {
                String query = "SELECT modules FROM EnrolledModules as modules";

                modules = manager.createQuery(query).getResultList();

                for (EnrolledModules m : modules) {
                    if (m.getCourseModule().getModuleID().equals(_moduleID)) {
                        enrolledStudents.add(m.getStudent());
                    }
                }

                return enrolledStudents;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("getListOfEnrolledStudents ERROR: " + e);
            return null;
        }
    }

    /**
     * Method to get all the assessments on a specified Module
     * @param _moduleID
     * @return A Collection of Module objects, or null if no modules are in the system
     */
    @Override
    public Collection<Assessment> getAssessmentsForModule(String _moduleID) {
        Collection<Assessment> allAssessments;
        Collection<Assessment> assessments = new ArrayList();
        try {
            String query = "SELECT assessment FROM Assessment as assessment";

            allAssessments = manager.createQuery(query).getResultList();

            for (Assessment a : allAssessments) {
                if (a.getModule().getModuleID().equals(_moduleID)) {
                    assessments.add(a);
                }
            }

            return assessments;
        } catch (Exception e) {
            System.out.println("getListOfAllModules ERROR: " + e);
            return null;
        }
    }

    /**
     * Method to get an assessment in a specified sequence on a specified Module
     * @param _moduleID
     * @param _sequence
     * @return The Assessment object, or null if not found
     */
    @Override
    public Assessment getAssessmentForModule(String _moduleID, int _sequence) {
        Collection<Assessment> assessments;
        try {
            String query = "SELECT assessment FROM Assessment as assessment";

            assessments = manager.createQuery(query).getResultList();

            for (Assessment a : assessments) {
                if (a.getModule().getModuleID().equals(_moduleID)
                        && a.getSequence() == _sequence) {
                    return a;
                }
            }

            return null;
        } catch (Exception e) {
            System.out.println("getAssessmentForModule ERROR: " + e);
            return null;
        }
    }

    /**
     * Method to get the average mark for an assessment
     * @param _moduleID
     * @param _assessmentSequence
     * @return The average mark, or 0 if not found
     */
    @Override
    public Float getAverageAssessmentMark(String _moduleID, int _assessmentSequence) {
        Collection<Submission> submissions;
        try {
            String query = "SELECT submission FROM Submission as submission";

            submissions = manager.createQuery(query).getResultList();
            int totalMarks = 0;
            int numOfSubmissions = 0;
            float averageMark = 0;

            for (Submission s : submissions) {

                if (s.getAssessment().getModule().getModuleID().equals(_moduleID)
                        && (s.getAssessment().getSequence() == _assessmentSequence)) {
                    totalMarks += s.getMark();
                    numOfSubmissions++;
                }

            }

            if (numOfSubmissions > 0) {
                averageMark = totalMarks / numOfSubmissions;
            } else {
                averageMark = 0;
            }

            Assessment assessment = getAssessmentForModule(_moduleID, _assessmentSequence);

            assessment.setAvgMark(averageMark);
            manager.merge(assessment);

            return averageMark;
        } catch (Exception e) {
            System.out.println("getAverageAssessmentMark ERROR: " + e);
            return 0F;
        }
    }

    /**
     * Method to get the average mark for a Module
     * @param _moduleID
     * @return The average mark, or 0 if not found
     */
    @Override
    public float getAverageModuleMark(String _moduleID) {
        Collection<Submission> submissions;
        try {
            String query = "SELECT submission FROM Submission as submission";

            submissions = manager.createQuery(query).getResultList();
            int totalMarks = 0;
            int numOfSubmissions = 0;
            float averageMark = 0;

            for (Submission s : submissions) {
                if (s.getAssessment().getModule().getModuleID().equals(_moduleID)) {
                    totalMarks += s.getMark();
                    numOfSubmissions++;
                }
            }

            if (numOfSubmissions > 0) {
                averageMark = totalMarks / numOfSubmissions;
            } else {
                averageMark = 0;
            }
            return averageMark;
        } catch (Exception e) {
            System.out.println("getAverageModuleMark ERROR: " + e);
            return 0F;
        }
    }

    /**
     * Method to get a Student's marks for a Module
     * @param _moduleID
     * @param _studentEmailID
     * @return The student's mark for that module, 0 if not found
     */
    @Override
    public float getModuleMark(String _moduleID, String _studentEmailID) {
        Collection<Submission> submissions;
        try {
            String query = "SELECT submission FROM Submission as submission";

            submissions = manager.createQuery(query).getResultList();
            int totalMarks = 0;
            int numOfSubmissions = 0;
            float averageMark = 0;

            for (Submission s : submissions) {
                if (s.getAssessment().getModule().getModuleID().equals(_moduleID)
                        && s.getStudent().getEmailID().equals(_studentEmailID)) {
                    totalMarks += s.getMark();
                    numOfSubmissions++;
                }
            }

            if (numOfSubmissions > 0) {
                averageMark = totalMarks / numOfSubmissions;
            } else {
                averageMark = 0;
            }
            return averageMark;
        } catch (Exception e) {
            System.out.println("getModuleMark ERROR: " + e);
            return 0F;
        }
    }

    /**
     * Method to get a Collection of all submissions for an assessment
     * @param _moduleID
     * @param _sequence
     * @return A Collection of submissions, or null if no submissions are found for that assessment
     */
    @Override
    public Collection<Submission> getSubmissions(String _moduleID, int _sequence) {
        Collection<Submission> allSubmissions;
        Collection<Submission> submissions = new ArrayList();
        try {
            String query = "SELECT submission FROM Submission as submission";

            allSubmissions = manager.createQuery(query).getResultList();

            for (Submission s : allSubmissions) {
                if (s.getAssessment().getModule().getModuleID().equals(_moduleID)
                        && s.getAssessment().getSequence() == _sequence) {
                    submissions.add(s);
                }
            }

            return submissions;
        } catch (Exception e) {
            System.out.println("getSubmissions ERROR: " + e);
            return null;
        }
    }
}
