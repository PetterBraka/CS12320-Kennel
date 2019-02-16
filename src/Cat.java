/**
 * @auther Petter Vang Brakalsvalet
 * @version 2.0 (20/02/18
 */

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Cat extends Animal {

    /*------------------------------------------------ CONSTRUCTORS -------------------------------------------------*/
    /**
     * Default constructor
     */
    public Cat() {
        this("unknown", false, "unknown", 1);
    }

    /**
     * Constructor for the cat
     *
     * @param name       The dog's name
     * @param withOthers If the dog can be around other animals
     * @param favFood    The kind of food it eats
     * @param foodPerDay Number of feeds per day
     */
    public Cat(String name, boolean withOthers, String favFood, int foodPerDay) {
        this.originalOwners = new ArrayList<Owner>();
        this.foodPerDay = foodPerDay;
        this.withOthers = withOthers;
        this.favFood = favFood;
        this.name = name;
    }

    /**
     * Constructor for the cat
     *
     * @param name       The cat's name
     * @param withOthers If the cat can be around other animals
     * @param favFood    The kind of food it eats
     * @param foodPerDay Number of feeds per day
     * @param owners     Info about owners
     */
    public Cat(String name, boolean withOthers, String favFood, int foodPerDay, ArrayList<Owner> owners) {
        this.originalOwners = owners;
        this.withOthers = withOthers;
        this.foodPerDay = foodPerDay;
        this.favFood = favFood;
        this.name = name;
    }
    /*---------------------------------------------------------------------------------------------------------------*/

    /*------------------------------------------------ SAVE AND LOAD ------------------------------------------------*/
    /**
     * Will save the info about cat
     *
     * @param pw is the name of the print writer
     */
    public void save(PrintWriter pw) {
        pw.println("Cat");
        super.save(pw);
        pw.println(false);
        pw.println("0");
        pw.println(withOthers);
    }

    /**
     * Saves the cat information
     *
     * @param infile The file to save to
     * @throws IOException If some IO error occurs
     */
    public void load(Scanner infile) throws IOException {

        super.load(infile);
    }
    /*---------------------------------------------------------------------------------------------------------------*/


    /*--------------------------------------------------- OVERRIDDEN -------------------------------------------------*/
    /**
     * Note that this only compares equality based on a
     * cat's name.
     *
     * @param obj the other dog to compare against.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cat other = (Cat) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    /**
     * @return String showing all the info about the dog
     */
    public String printCat() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cat name: ");
        sb.append(name);
        sb.append("\nGoes with others: ");
        sb.append(withOthers);
        sb.append("\nOriginal Owner: ");
        sb.append(originalOwners);
        sb.append("\nFavfood: ");
        sb.append(favFood);
        sb.append("\nFoodPerDay: ");
        sb.append(foodPerDay);
        sb.append("\n");

        return sb.toString();
    }
    /*---------------------------------------------------------------------------------------------------------------*/
}
