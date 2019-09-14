/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code_challenge;

import Controllers.RunApplication;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Mtchathuranga
 */
public class Code_Challenge {

    /**
     * @param args the command line arguments
     */
    Scanner scanner = new Scanner(System.in);

    public void showMainMenu() {
        //View Main Menu
        System.out.println("__________________________________________________________________________________________________");
        System.out.print("\n\n Type 'Quit' to exit , Type value,Field and Press 'Enter' to continue....\n\n");
        System.out.println("\t Select search options :");
        System.out.println("\t  * Press 1 to search");
        System.out.println("\t  * Press 2 to view a list of searchable fields");
        System.out.println("\t  * Type 'Quit' to exit");
        System.out.println("__________________________________________________________________________________________________");
    }

    public void getUserInputs() {
        String mainInput;
        showMainMenu();
        RunApplication runApp = new RunApplication();

        try {
            loop:
            while (true) {
                mainInput = scanner.nextLine();

                switch (mainInput) {
                    case "1":                       
                        runApp.GetFirstOptionInputs();
                        break;
                    case "2":                       
                        runApp.ViewSecondOptionList();
                        showMainMenu();
                        break;
                    case "Quit":
                        System.out.println("Main Quit");
                        System.exit(0);
                        break loop;
                    default:
                        System.out.println("* This is not an option! Please try again !\n");
                        break;
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("InputMismatch: Please try again with 1,2");
        }

    }

    public static void main(String[] args) {
        // TODO code application logic here

        Code_Challenge cc = new Code_Challenge();
        cc.getUserInputs();

    }

}
