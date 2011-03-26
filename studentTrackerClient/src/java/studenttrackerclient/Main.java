/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package studenttrackerclient;

import javax.ejb.EJB;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/** 
 *
 * @author mm336
 */
public class Main {

    //@EJB
    //UserSessionRemote userSession;

    private static String username = null;
    private static String password = null;

    private static void getUserPass() {
        try {
            if(username != null && password != null)
            {
                InputStreamReader converter = new InputStreamReader(System.in);
                BufferedReader in = new BufferedReader(converter);

                System.out.println("Please enter your email ID: ");
                username = in.readLine();

                System.out.println("Please enter your password: ");
                password = in.readLine();
            }
        } catch (IOException ex) {
            System.out.println("Error with username/password: "+ex);
        }

        logIn();
    }

    private static void logIn(){
        Sessions session = new Sessions();

        if(session.userSession().checkLogin(username, password))
        {
            if(session.userSession().checkIfStaff(username))
            {
                //Get the staff user object
                //Staff staff = session.staffSession.getStaffMember(username);
                //System.out.println("Welcome " + staff.name + ". Opening the Staff Client now...");

                System.out.println("STAFF CLIENT OPENING...");
            }
            else
            {
                //Get the student
                //Student student = session.studentSession.getStudent(username);
                //System.out.println("Welcome " + student.name + ". Opening the Student Client now...");

                System.out.println("STUDENT CLIENT OPENING...");
            }
        }
        else
        {
            System.out.println("Incorrect Login Details. Please try again");
            getUserPass();
        }
    }


    public static void main(String[] args) {
        if (args.length == 2) {
            username = args[0];
            password = args[1];

            logIn();
        } else {
            getUserPass();
        }


    }
}
