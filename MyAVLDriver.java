public class MyAVLDriver {

    /**
     * 
     * 
     * Change the following lines to meet your needs.
     * 
     * 
     */
  
    /** The number of ints we will put in our collection */
    /** Reduce this number to debug. Set it to 1_000 to pass off. */
    
    static int intSize = 1000;
  
    // Initialize the collections that will be tested
    // Chang the left-hand side to test other implementations of ICollection
    static ICollection<Integer> collection = new MyAVL<>();
    static ICollection<Pet> pets = new MyAVL<>();
  
    /**
     * 
     * 
     * You don't need to change anything below here.
     * 
     * 
     */
  
    /**
     * A custom class to demonstrate that we can work on a variety of object types
     */
    static class Pet implements Comparable<Pet> {
      String name;
  
      public Pet(String name) {
        this.name = name;
      }
  
      @Override
      public String toString() {
        return this.name;
      }
  
      @Override
      public boolean equals(Object obj) {
        if (!(obj instanceof Pet))
          return false;
        return this.name == ((Pet) obj).name;
      }
  
      @Override
      public int compareTo(Pet p) {
        return this.name.compareTo(p.name);
      }
  
    }
  
    // Helper functions for testing.
    // In a real development environment, you would use
    // a unit test library, such as JUnit
  
    static int countTests = 0;
  
    /**
     * Throws an exception if the two integers are not the same
     * 
     * @param one The first integer to compare
     * @param two The second integer to compare
     */
    public static void expectEqual(int one, int two) {
      if (one != two)
        throw new RuntimeException("Error. Mismatch in integer comparison. " + countTests);
      countTests++;
    }
  
    /**
     * Throws an exception if the value is not fasle
     * 
     * @param b The boolean value expected to be false
     */
    public static void expectFalse(boolean b) {
      if (b)
        throw new RuntimeException("Error. Boolean was true when it was expected to be false. " + countTests);
      countTests++;
    }
  
    /**
     * Throws an exception if the value is not true
     * 
     * @param b The value expected to be true
     */
    public static void expectTrue(boolean b) {
      if (!b)
        throw new RuntimeException("Error. Boolean was false when it was expected to be true. " + countTests);
      countTests++;
    }
  
    /**
     * Throws an exception if the Iterable collections does not match the array.
     * Note that because we are dealing with collections, order does not matter.
     * However, since we are not constraining ourselves to sets, there may be
     * duplicates. In order to handle this, we convert both arguments to array
     * lists, sort them, and then compare them.
     * 
     * @param petsCollection The itorator that should be equal to the array
     * @param petsArray      The array that should be equal to the iterator
     */
    private static void expectIteratorEquality(ICollection<Pet> petsCollection, Pet[] petsArray) {
      var iterator = petsCollection.iterator();
      java.util.List<Pet> values = new java.util.ArrayList<>();
  
      while (iterator.hasNext()) {
        values.add(iterator.next());
      }
  
      if (values.size() != petsArray.length)
        throw new RuntimeException("The iterator does not have the same number of values as the array.");
  
      java.util.List<Pet> values2 = java.util.Arrays.asList(petsArray);
      java.util.Collections.sort(values);
      java.util.Collections.sort(values2);
  
      for (int i = 0; i < values.size(); i++) {
        expectTrue(values.get(i).equals(values2.get(i)));
      }
  
    }
  
    // Helping objects
  
    /** A pet named Fido */
    static Pet Fido = new Pet("Fido");
    /** A pet named Fluffy */
    static Pet Fluffy = new Pet("Fluffy");
  
    /**
     * The main method of our driver.
     * 
     * @param args Command line arguments. These are ignored in this driver.
     */
    public static void main(String[] args) {
  
      // Problems with the ICollection interface are manifest by throwing
      // runtime exceptions. This try block will catch those errors and then print
      // them to the screen.
  
      boolean passedAllTests = true;
      try {
        // Test that the collection can handle integers
        testInts();
  
        // Test that the collections can handle pets
        testPets();
  
      } catch (RuntimeException e) {
        passedAllTests = false;
        // Print any exceptions that were thrown
        e.printStackTrace();
      }
      String tests = String.format("%,d", countTests);
      if (passedAllTests) {
        System.out.println("You passed all " + tests + " tests.");
      } else {
        System.out.println("You did not pass all tests. You stopped at test " + tests);
      }
  
    }
  
    /**
     * Check that our collection can handle adding/removing a large number of values
     */
    private static void testInts() {
      for (int i = 0; i < intSize; i++) {
        expectTrue(collection.add(i));
  
        // Check size and isEmpty
        expectFalse(collection.isEmpty());
        expectEqual(collection.size(), i + 1);
  
        // Check that we contain all the right values
        for (int j = 0; j <= i; j++) {
          expectTrue(collection.contains(j));
        }
  
        // Check that we don't contain anything we shouldn't
        for (int j = i + 1; j < intSize; j++) {
          expectFalse(collection.contains(j));
        }
      }
  
      // Final size and isEmpty check
      expectEqual(collection.size(), intSize);
      expectFalse(collection.isEmpty());
  
      // Now remove all the values
      for (int i = 0; i < intSize; i++) {
        if (countTests == 249){
          System.out.println();
        }
        expectTrue(collection.remove(i));
  
        // Check size and isEmpty
        if (i == (intSize - 1))
          expectTrue(collection.isEmpty());
        else
          expectFalse(collection.isEmpty());
        expectEqual(collection.size(), intSize - (i + 1));
  
        // Check that we contain all the right values
        for (int j = 0; j <= i; j++) {
          if (countTests == 249){
            System.out.println();
          }
          expectFalse(collection.contains(j));
        }
  
        // Check that we don't contain anything we shouldn't
        for (int j = i + 1; j < intSize; j++) {
          expectTrue(collection.contains(j));
        }
      }
    }
  
    /**
     * Test that our collection can handle custom classes.
     */
    private static void testPets() {
  
      // Empty base case
      expectTrue(pets.isEmpty());
      expectEqual(pets.size(), 0);
      expectFalse(pets.contains(Fluffy));
      expectFalse(pets.contains(Fido));
      expectIteratorEquality(pets, new Pet[] {});
  
      // Add one item
      expectTrue(pets.add(Fido));
      expectFalse(pets.isEmpty());
      expectEqual(pets.size(), 1);
      expectTrue(pets.contains(Fido));
      expectFalse(pets.contains(Fluffy));
      expectIteratorEquality(pets, new Pet[] { Fido });
  
      // Add a second item
      expectTrue(pets.add(Fluffy));
      expectFalse(pets.isEmpty());
      expectEqual(pets.size(), 2);
      expectTrue(pets.contains(Fido));
      expectTrue(pets.contains(Fluffy));
      expectIteratorEquality(pets, new Pet[] { Fido, Fluffy });
  
      // Add a third, duplicate item
      expectTrue(pets.add(Fluffy));
      expectFalse(pets.isEmpty());
      expectEqual(pets.size(), 3);
      expectTrue(pets.contains(Fido));
      expectTrue(pets.contains(Fluffy));
      expectIteratorEquality(pets, new Pet[] { Fido, Fluffy, Fluffy });
  
      // Remove the first item
      expectTrue(pets.remove(Fido));
      expectFalse(pets.isEmpty());
      expectEqual(pets.size(), 2);
      expectFalse(pets.contains(Fido));
      expectTrue(pets.contains(Fluffy));
      expectIteratorEquality(pets, new Pet[] { Fluffy, Fluffy });
  
      // Remove a duplicate item
      expectTrue(pets.remove(Fluffy));
      expectFalse(pets.isEmpty());
      expectEqual(pets.size(), 1);
      expectFalse(pets.contains(Fido));
      expectTrue(pets.contains(Fluffy));
      expectIteratorEquality(pets, new Pet[] { Fluffy });
  
      // Try to remove a non-existant item
      expectFalse(pets.remove(Fido));
      expectFalse(pets.isEmpty());
      expectEqual(pets.size(), 1);
      expectFalse(pets.contains(Fido));
      expectTrue(pets.contains(Fluffy));
      expectIteratorEquality(pets, new Pet[] { Fluffy });
  
      // Remove the final item
      expectTrue(pets.remove(Fluffy));
      expectTrue(pets.isEmpty());
      expectEqual(pets.size(), 0);
      expectFalse(pets.contains(Fido));
      expectFalse(pets.contains(Fluffy));
      expectIteratorEquality(pets, new Pet[] {});
  
    }
  
  }