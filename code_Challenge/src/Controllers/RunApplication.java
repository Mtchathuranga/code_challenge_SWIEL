/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Config.AppParams.organizationFilePath;
import static Config.AppParams.ticketsFilePath;
import static Config.AppParams.usersFilePath;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Mtchathuranga
 */
public class RunApplication {

    Scanner scanner = new Scanner(System.in);
    ReadFiles rd = new ReadFiles();
    String searchTerm;
    String searchValue;

    JSONArray objArryUSERS;
    JSONArray objArryTickets;
    JSONArray objArryOraganizations;

    public void getFiles() {
        //Read All JSON Files and assign values into JSON Arrays
        objArryUSERS = rd.ReadJSON_FILES(usersFilePath);
        objArryTickets = rd.ReadJSON_FILES(ticketsFilePath);
        objArryOraganizations = rd.ReadJSON_FILES(organizationFilePath);
    }

    public void GetFirstOptionInputs() {
        getFiles();//Read All JSON Files and assign values into JSON Arrays
        if (objArryUSERS == null || objArryTickets==null ||objArryOraganizations==null ) {
            System.out.println("JSON FILES ARE EMPTY. Please check..");

            System.out.println("\n__________________________________________________________________________________________________");
            System.out.print("\n\n Type 'Quit' to exit , Type value,Field and Press 'Enter' to continue....\n\n");
            System.out.println("\t Select search options :");
            System.out.println("\t  * Press 1 to search");
            System.out.println("\t  * Press 2 to view a list of searchable fields");
            System.out.println("\t  * Type 'Quit' to exit");
            System.out.println("__________________________________________________________________________________________________");

        } else {
            System.out.println("select 1) - Users ,  2) - Tickets , 3) - Oraganization, 4) - BACK");
            String Input;

            loop:
            while (true) {
                Input = scanner.nextLine();
                switch (Input) {
                    case "1":
                        getSecondInputs(1);
                        System.out.println("-------------------------------------------------------------------------------------------------");
                        System.out.println("select 1) - Users ,  2) - Tickets , 3) - Oraganization, 4) - BACK");
                        break;
                    case "2":
                        getSecondInputs(2);
                        System.out.println("-------------------------------------------------------------------------------------------------");
                        System.out.println("select 1) - Users ,  2) - Tickets , 3) - Oraganization, 4) - BACK");
                        break;
                    case "3":
                        getSecondInputs(3);
                        System.out.println("-------------------------------------------------------------------------------------------------");
                        System.out.println("select 1) - Users ,  2) - Tickets , 3) - Oraganization, 4) - BACK");
                        break;
                    case "4":
                        System.out.println("__________________________________________________________________________________________________");

                        System.out.print("Type 'Quit' to exit at any time, Press 'Enter' to continue..\n\n");
                        System.out.println("\t Select search options :");
                        System.out.println("\t  * Press 1 to search");
                        System.out.println("\t  * Press 2 to view a list of searchable fields");
                        System.out.println("\t  * Type 'Quit' to exit");
                        System.out.println("__________________________________________________________________________________________________");

                        break loop;
                    case "Quit":
                        System.out.println("Quit");
                        System.exit(0);
                    default:
                        System.out.println("* This is not an option! Please try again !\n");
                        break;
                }
            }
        }

    }

    public void getSecondInputs(Integer type) {
        //Type 1 - user , 2-Tickets , 3 - Oraganization
        Boolean isAvailable = false;
        System.out.println("-> Enter Search Term");
        searchTerm = scanner.nextLine();
        System.out.println("-> Enter Search Value");
        searchValue = scanner.nextLine();

        if (type == 1) {
            System.out.println("\n Searching Users for " + searchTerm + " With a value of " + searchValue);
            System.out.println("\n");
            isAvailable = false;
            isAvailable = rd.CheckKey(objArryUSERS, searchTerm);
            if (isAvailable) {
                filterUsers();
            }
        } else if (type == 2) {
            System.out.println("\n Searching Tickets for " + searchTerm + " With a value of " + searchValue);
            System.out.println("\n");
            isAvailable = false;
            isAvailable = rd.CheckKey(objArryTickets, searchTerm);
            if (isAvailable) {
                filterTickets();
            }
        } else {
            System.out.println("\n Searching Organizations for " + searchTerm + " With a value of " + searchValue);
            System.out.println("\n");
            isAvailable = false;
            isAvailable = rd.CheckKey(objArryOraganizations, searchTerm);
            if (isAvailable) {
                filterOraganizations();
            }

        }
    }

    public void filterUsers() {
        //Filter values and Display
        rd.searchUsersInList(objArryUSERS, objArryTickets, objArryOraganizations, searchTerm, searchValue);
    }

    public void filterTickets() {
        //Filter values and Display
        rd.searchTicketsInList(objArryUSERS, objArryTickets, objArryOraganizations, searchTerm, searchValue);
    }

    public void filterOraganizations() {
        //Filter values and Display
        rd.searchOraganizationsInList(objArryUSERS, objArryTickets, objArryOraganizations, searchTerm, searchValue);
    }

    public void ViewSecondOptionList() {
        //Show All Keys FROM JSON Files
        JSONObject keysOfFiles = null;
        keysOfFiles = rd.showKeys(usersFilePath);
        System.out.println("==================================================================================================");
        System.out.println("\t--------------------------------Search Users With----------------------------------");
        if (keysOfFiles != null) {
            keysOfFiles.keySet().forEach(key -> System.out.println(key));
        }

        keysOfFiles = rd.showKeys(ticketsFilePath);
        System.out.println("==================================================================================================");
        System.out.println("\t--------------------------------Search Tickets With--------------------------------");
        if (keysOfFiles != null) {
            keysOfFiles.keySet().forEach(key -> System.out.println(key));
        }

        keysOfFiles = rd.showKeys(organizationFilePath);
        System.out.println("==================================================================================================");
        System.out.println("\t-----------------------------Search Oraganizations With----------------------------");
        if (keysOfFiles != null) {
            keysOfFiles.keySet().forEach(key -> System.out.println(key));
        }

    }

}
