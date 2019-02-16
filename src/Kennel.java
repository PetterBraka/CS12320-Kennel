/**
 * @auther Petter Vang Brakalsvalet
 * @version 2.0 (20/02/18
 */
import java.io.*;
import java.util.*;

/**
 * To model a Kennel - a collection of dogs
 *
 * @author Chris Loftus
 * @version 1.1 (26th February 2018)
 */
public class Kennel {
    private int nextFreeLocationDogs;
    private int nextFreeLocationCats;
    private ArrayList<Dog> dogs;
    private ArrayList<Cat> cats;
    private int capacity;
    private String name;

    /*------------------------------------------------ CONSTRUCTORS -------------------------------------------------*/
    /**
     * Creates a kennel with a default size 20
     */
    public Kennel() {
        this(20);
    }

    /**
     * Create a kennel
     *
     * @param maxNoAnimals The capacity of the kennel
     */
    public Kennel(int maxNoAnimals) {
        nextFreeLocationDogs = 0; // no Dogs in collection at start
        nextFreeLocationCats = 0;
        capacity = maxNoAnimals;
        dogs = new ArrayList<Dog>(capacity); // set up default. This can
        cats = new ArrayList<Cat>(capacity);
        // actually be exceeded
        // when using ArrayList but we
        // won't allow that
        // to happen.
    }
    /*---------------------------------------------------------------------------------------------------------------*/

    /*-------------------------------------------- GETTERS AND SETTERS ----------------------------------------------*/
    /**
     * This method sets the value for the name attribute. The purpose of the
     * attribute is: The name of the kennel e.g. "DogsRUs"
     *
     * @param theName is the name of the kennel
     */
    public void setName(String theName) {
        name = theName;
    }

    /**
     * Set the size of the kennel
     *
     * @param capacity The max animals we can house
     */
    public void setCapacity(int capacity) {
        // This should really check to see if we already have dogs
        // in the kennel and reducing the capacity would lead to evictions!
        this.capacity = capacity;
    }

    /**
     * This method returns the number of dogs in a kennel
     * @return int Current number of dogs in the kennel
     */
    public int getNumOfDogs() {
        return nextFreeLocationDogs;
    }

    /**
     * This method returns the number of cat's in a kennel
     * @return int Current number of cat's in the kennel
     */
    public int getNumOfCats() {
        return nextFreeLocationCats;
    }
    /*---------------------------------------------------------------------------------------------------------------*/

    /*-------------------------------------------- ADDING AND REMOVING ----------------------------------------------*/
    /**
     * Enables a user to add a dog to the Kennel
     * but not to add too many animals
     * @param theDog A new dog to home
     */
    public void addDog(Dog theDog) {
        if (nextFreeLocationDogs + nextFreeLocationCats >= capacity) {
            System.out.println("Sorry kennel is full - cannot add team");
            return;
        }
        // we add in the position indexed by nextFreeLocation
        // This starts at zero
        dogs.add(theDog);

        // now increment index ready for next one
        nextFreeLocationDogs = nextFreeLocationDogs + 1;
    }
    /**
     * Enables a user to add a cat to the Kennel
     * but not to add too many animals
     * @param theCat A new dog to home
     */
    public void addCat(Cat theCat) {
        if (nextFreeLocationCats + nextFreeLocationDogs >= capacity) {
            System.out.println("Sorry kennel is full - cannot add team");
            return;
        }
        cats.add(theCat);
        nextFreeLocationCats = nextFreeLocationCats + 1;
    }

    /**
     * Enables a user to delete a animal from the Kennel.
     * @param animal The type of animal to removed
     * @param who The animal to remove
     */
    public void removeAnimal(String animal, String who) {
        Cat whichCat = null;
        Dog whichDog = null;
        for (int i = 0; i < getNumOfCats() + getNumOfDogs(); i++) {
            // Search for the dog by name
            for (Dog d : dogs) {
                if (who.equals(d.getName())) {
                    whichDog = d;
                }
            }
            for (Cat c : cats) {
                if (who.equals(c.getName())) {
                    whichCat = c;
                }
            }
        }
        if (whichDog != null || whichCat != null) {
            switch (animal) {
                case "dog":
                    dogs.remove(whichDog); // Requires that Dog has an equals method
                    nextFreeLocationDogs = nextFreeLocationDogs - 1;
                case "cat":
                    cats.remove(whichCat);
                    nextFreeLocationCats = nextFreeLocationCats - 1;
            }
            System.out.println("removed " + who);

        } else {
            System.err.println("cannot remove - not in kennel");
        }
    }
    /*---------------------------------------------------------------------------------------------------------------*/

    /*---------------------------------------------- SEARCH AND SORT ------------------------------------------------*/
    /**
     * Will sort the animals in the kennel
     * @return an array of animals sorted alphabetically
     */
    public ArrayList<Object> sortAnimals() {
        ArrayList<Object> sortedList = new ArrayList<>();
        ArrayList<String> tempList = new ArrayList(capacity);

        int numDog = 0;
        int numCat = 0;
        for (Dog d : dogs) {
            Dog sortDog = dogs.get(numDog);
            tempList.add(sortDog.getName());
            numDog++;
        }
        for (Cat c : cats) {
            Cat sortCat = cats.get(numCat);
            tempList.add(sortCat.getName());
            numCat++;
        }
        Collections.sort(tempList);

        int length = 0;
        while (length < numDog + numCat) {
            sortedList.add(searchForAnimal(tempList.get(length)));
            length++;
        }
        return sortedList;
    }

    /**
     * Returns an array of the inmates in the kennels
     *
     * @return An array of the correct size
     */
    public String obtainAllInmates() {
        return null;
    }

    /**
     * Will find an animal in the kennel
     * @param who The animal searched for
     * @return An String with the animals info
     */
    public String searchForAnimal(String who) {
        // ENTER CODE HERE (POSSIBLY CHANGE SOME, YOU MAY CHANGE THE SIGNATURE TO DEAL
        // WITH ALL KINDS OF ANIMALS: CATS AND DOGS)
        String animal = null;
        Cat whichCat = null;
        Dog whichDog = null;
        for (int i = 0; i < getNumOfCats() + getNumOfDogs(); i++) {
            // Search for the dog by name
            for (Dog d : dogs) {
                if (who.equals(d.getName())) {
                    animal = "dog";
                    whichDog = d;
                }
            }
            for (Cat c : cats) {
                if (who.equals(c.getName())) {
                    animal = "cat";
                    whichCat = c;
                }
            }
            if ((whichDog != null) || (whichCat != null)) {
                switch (animal) {
                    case "dog":
                        return whichDog.printDog();
                    case "cat":
                        return whichCat.printCat();
                }

            } else {
                System.err.println("can't find animal - not in kennel");
            }
        }
        return null;
    }
    /*---------------------------------------------------------------------------------------------------------------*/

    /*------------------------------------------------ SAVE AND LOAD ------------------------------------------------*/
    /**
     * Reads in Kennel information from the file
     *
     * @param infileName The file to read from
     * @throws FileNotFoundException    if file doesn't exist
     * @throws IOException              if some other IO error occurs
     * @throws IllegalArgumentException if infileName is null or empty
     */
    public void load(String infileName) throws IOException {
        // Using try-with-resource. We will cover this in workshop 15, but
        // what it does is to automatically close the file after the try / catch ends.
        // This means we don't have to worry about closing the file.
        try (FileReader fr = new FileReader(infileName);
             Scanner infile = new Scanner(fr)) {

            // Use the delimiter pattern so that we don't have to clear end of line
            // characters after doing a nextInt or nextBoolean
            infile.useDelimiter("\r?\n|\r");

            name = infile.nextLine();
            capacity = infile.nextInt();

            System.out.println("Loading the animals in kennel: " + name);

            int numDog = infile.nextInt();
            int numCat = infile.nextInt();
            infile.nextLine();
            while (0 < numDog + numCat) {
                infile.nextLine();
                String animal = infile.nextLine().toLowerCase();
                if (animal.equals("dog")) {
                    Dog newDog = new Dog();
                    newDog.load(infile);
                    addDog(newDog);
                    numDog--;
                } else if (animal.equals("cat")) {
                    Cat newCat = new Cat();
                    newCat.load(infile);
                    addCat(newCat);
                    numCat--;
                }
            }
        }
    }

    /**
     * Saves the kennel information
     *
     * @param filename The file to save to
     * @throws IOException If some IO error occurs
     */
    public void save(String filename) throws IOException {
        // Again using try-with-resource so that I don't need to close the file explicitly
        try (FileWriter fw = new FileWriter(filename);
             PrintWriter outfile = new PrintWriter(fw)) {

            outfile.println(name);
            outfile.println(capacity);
            outfile.println(this.getNumOfDogs());
            outfile.println(this.getNumOfCats());
            outfile.println(" ");
            for (Dog d : dogs) {
                d.save(outfile);
                outfile.println(" ");
            }
            for (Cat c : cats) {
                c.save(outfile);
                outfile.println(" ");
            }
        }
    }
    /*---------------------------------------------------------------------------------------------------------------*/

    /*------------------------------------------------- OVERRIDDEN --------------------------------------------------*/
    /**
     * @return String showing all the information in the kennel
     */
    public String toString() {
        String results = "Data in Kennel " + name + " is: \n\n";
        for (Dog d : dogs) {
            results = results + d.printDog() + "\n";
        }
        for (Cat c : cats) {
            results = results + c.printCat() + "\n";
        }
        return results;
    }
    /*---------------------------------------------------------------------------------------------------------------*/
}