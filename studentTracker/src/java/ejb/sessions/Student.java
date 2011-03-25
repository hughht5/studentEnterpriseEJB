/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.sessions;

import java.sql.Date;
import javax.ejb.Stateless;

/**
 *
 * @author hmh205
 */
@Stateless
public class Student implements StudentRemote {

    public boolean addStudent(int candidateNum, int studentNum, String emailID, String name, Date dob) {
        return true;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
}
