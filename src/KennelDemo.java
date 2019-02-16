/**
 * This class runs a Kennel
 *
 * @author Lynda Thomas and Chris Loftus
 * @version 1.2 (23rd February 2018)
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class KennelDemo {

    private String filename; // holds the name of the file
    private Kennel kennel; // holds the kennel
    private Scanner scan; // so we can read from keyboard

    private KennelDemo() {
        scan = new Scanner(System.in);
        System.out.print("Please enter the filename of kennel information: ");
        filename = scan.nextLine();

        kennel = new Kennel();
    }

    /*-------------------------------------------------- MAIN MENU --------------------------------------------------*/
    private void runMenu() {
        String response;
        do {
            printMenu();
            System.out.println("What would you like to do:");
            scan = new Scanner(System.in);
            response = scan.nextLine().toUpperCase();
            switch (response) {
                case "1":
                    admitAnimal();
                    break;
                case "2":
                    removeAnimal();
                    break;
                case "3":
                    changeKennelName();
                    break;
                case "4":
                    searchForAnimal();
                    break;
                case "5":
                    setKennelCapacity();
                    break;
                case "6":
                    printAll();
                    break;
                case "Q":
                    break;
                default:
                    System.out.println("Try again");
            }
        } while (!(response.equals("Q")));
    }

    private void printMenu() {
        System.out.println("1 -  add a new animal");
        System.out.println("2 -  remove an animal");
        System.out.println("3 -  changing the Kennel name");
        System.out.println("4 -  search for a animal");
        System.out.println("5 -  set kennel capacity");
        System.out.println("6 -  display all inmates");
        System.out.println("q -  Quit");
    }

    private void admitAnimal() {
        boolean lb = false;
        boolean wo = false;
        System.out.println("Do you want to admit dog or cat");
        String animalType = scan.nextLine().toLowerCase();
        if (animalType.equals("dog") || animalType.equals("cat")) {
            System.out.println("What is the name of the animal? ");
            String name = scan.nextLine().toLowerCase();
            System.out.println("How many runs a day do he/she needs? (as a number)");
            int runs = scan.nextInt();
            scan.nextLine();
            if (animalType == "dog") {
                System.out.println("Does he/she like bones? (Y/N)");
                String likeBones = scan.nextLine().toUpperCase();
                if (likeBones.equals("Y")) {
                    lb = true;
                }
            }
            else if (animalType == "cat"){
                System.out.println("Does he/she goes along with other? (Y/N)");
                String withOthers = scan.nextLine();
                if (withOthers.equals("Y")) {
                    wo = true;
                }
            }
            System.out.println("What is his/her favourite food?");
            String fav;
            fav = scan.nextLine().toLowerCase();
            System.out.println("How many times is he/she fed a day? (as a number)");
            int numTimes;
            numTimes = scan.nextInt(); // This can be improved (InputMismatchException?)
            scan.nextLine(); // Clear the end of line characters because I didn't use a delimiter
            switch (animalType) {
                case "dog":
                    Dog newDog = new Dog(name, runs, lb, fav, numTimes);
                    ArrayList<Owner> ownersDog = getOwners();
                    for (Owner o : ownersDog) {
                        newDog.addOriginalOwner(o);
                    }
                    kennel.addDog(newDog);
                    break;
                case "cat":
                    Cat newCat = new Cat(name, wo, fav, numTimes);
                    ArrayList<Owner> ownersCat = getOwners();
                    for (Owner o : ownersCat) {
                        newCat.addOriginalOwner(o);
                    }
                    kennel.addCat(newCat);
                    break;
                default:
                    System.err.println("Invalid entry");
            }
        } else System.err.println("Can't add that animal");
    }//Option 1

    private void removeAnimal() {
        System.out.println("what animal do you want to remove? cat or dog");
        String animal = scan.nextLine().toLowerCase();
        System.out.println("what is the name of the animal?");
        String animalToBeRemoved = scan.nextLine();
        kennel.removeAnimal(animal, animalToBeRemoved);
    }//Option 2

    private void changeKennelName() {
        System.out.println("What is the new name of the kennel? ");
        String name = scan.nextLine();
        kennel.setName(name);
    }//Option 3

    private void searchForAnimal() {
        System.out.println("what is the name of the animal?");
        String aniamlName = scan.nextLine().toLowerCase();
        System.out.println(kennel.searchForAnimal(aniamlName));
    }//Option 4

    private void setKennelCapacity() {
        System.out.print("Enter max number of animals: ");
        int max = scan.nextInt();
        scan.nextLine();
        kennel.setCapacity(max);
    }//Option 5

    /**
     * printAll() method runs from the main and prints status
     */
    private void printAll() {
        ArrayList<Object> sortedList  = kennel.sortAnimals();
        for (Object o : sortedList){
            System.out.println(o.toString());
        }
    }//Option 6
    /*---------------------------------------------------------------------------------------------------------------*/

    private ArrayList<Owner> getOwners() {
        ArrayList<Owner> owners = new ArrayList<>();
        String answer;
        do {
            System.out
                    .println("Enter on separate lines: owner-name owner-phone");
            String ownName = scan.nextLine();
            String ownPhone = scan.nextLine();
            Owner own = new Owner(ownName, ownPhone);
            owners.add(own);
            System.out.println("Another owner (Y/N)?");
            answer = scan.nextLine().toUpperCase();
        } while (!answer.equals("N"));
        return owners;
    }

    /*------------------------------------------------ SAVE AND LOAD ------------------------------------------------*/
    /**
     * save() method runs from the main and writes back to file
     */
    private void save() {
        try {
            kennel.save(filename);
        } catch (IOException e) {
            System.err.println("Problem when trying to write to file: " + filename);
        }
    }

    private void initialise() {
        System.out.println("Using file " + filename);

        try {
            kennel.load(filename);
        } catch (FileNotFoundException e) {
            System.err.println("The file: " + filename + " does not exist. Assuming first use and an empty file." +
                    " If this is not the first use then have you accidentally deleted the file?");
        } catch (IOException e) {
            System.err.println("An unexpected error occurred when trying to open the file " + filename);
            System.err.println(e.getMessage());
        }
    }
    /*---------------------------------------------------------------------------------------------------------------*/

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String args[]) {
        System.out.println("************HELLO*************");
        KennelDemo demo = new KennelDemo();
        demo.initialise();
        demo.runMenu();
        System.out.println("********Saving to file********");
        // MAKE A BACKUP COPY OF dogsrus.txt JUST IN CASE YOU CORRUPT IT
        demo.save();
        System.out.println(" **********GOODBYE***********");
    }
}
