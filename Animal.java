/**
 * @auther Petter Vang Brakalsvalet
 * @version 2.0 (20/02/18
 */

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Animal {
    ArrayList<Owner> originalOwners;
    boolean likesBones;
    boolean withOthers;
    int foodPerDay;
    String favFood;
    int runsADay;
    String name;

    /*--------------------------------------------------- GETTERS ---------------------------------------------------*/
    /**
     * Will get the name of an animal
     *
     * @return a string name
     */
    public String getName() {
        return name;
    }
    /*---------------------------------------------------------------------------------------------------------------*/

    /*---------------------------------------------- SAVE AND LOAD --------------------------------------------------*/
    /**
     * Will save the info about animals
     *
     * @param pw is the name of the print writer
     */
    void save(PrintWriter pw) {
        pw.println(name);
        pw.println(originalOwners.size());
        for (Owner o : originalOwners) {
            pw.println(o.getName());
            pw.println(o.getPhone());
        }
        pw.println(foodPerDay);
        pw.println(favFood);
    }

    /**
     * Saves the animals information
     *
     * @param infile The file to save to
     * @throws IOException If some IO error occurs
     */
    void load(Scanner infile) throws IOException {

            name = infile.nextLine();
            int numOwners = infile.nextInt();
            for (int oCount = 0; oCount < numOwners; oCount++) {
                infile.nextLine();
                String ownersName = infile.nextLine();
                String ownersPhone = infile.nextLine();
                Owner owner = new Owner(ownersName, ownersPhone);
                originalOwners.add(owner);
            }
            foodPerDay = infile.nextInt();
            infile.nextLine();
            favFood = infile.nextLine();
            likesBones = infile.nextBoolean();
            runsADay = infile.nextInt();
            infile.nextLine();
            withOthers = infile.nextBoolean();
            infile.nextLine();
    }
    /*---------------------------------------------------------------------------------------------------------------*/

    /**
     * To add an original owner
     *
     * @param o An original owner
     */
    public void addOriginalOwner(Owner o) {
        originalOwners.add(o);
    }
}
