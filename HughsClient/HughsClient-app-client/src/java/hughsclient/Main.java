/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hughsclient;

import ejb.sessions.StudentSessionRemote;
import javax.ejb.EJB;

/**
 *
 * @author hmh205
 */
public class Main {
    @EJB
    private static StudentSessionRemote studentSession;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new StudentLoginForm();
        // TODO code application logic here
    }

}
