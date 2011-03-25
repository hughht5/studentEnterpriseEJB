/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client.logon;

/**
 *
 * @author mm336
 */
public class NewClass {
/*

package client.logon;

import ejb.sessions.UserSessionRemote;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class logon {

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
            Logger.getLogger(logon.class.getName()).log(Level.SEVERE, null, ex);
        }

        logIn();
    }

    private static void logIn(){
        System.out.print("fuck1");
        Sessions session = new Sessions("lol");

        ///
        System.out.print("fuck3");
        ////

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
*/
}
