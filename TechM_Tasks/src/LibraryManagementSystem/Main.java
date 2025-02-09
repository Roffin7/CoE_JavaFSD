package LibraryManagementSystem;

import java.util.Arrays;

public class Main {
   public static void main(String[] args) {
      LibraryManager libManager = new LibraryManager();
      
      // Initialize library system, add users and books
      // Demonstrate borrowing, returning, reserving books
      // Demonstrate multithreading by simulating multiple users
      // Save and retrieve library data using Java I/O
      Book b1 = new Book("Fire","Jason","68763486423");
      Book b2 = new Book("Water","Ron","68763486529");
      Book b3 = new Book("Earth","Haku","68763486111");
      
      libManager.addBook(b1);
      libManager.addBook(b2);
      libManager.addBook(b3);
      
      libManager.addUser(new User("Mizuki","1"));
      libManager.addUser(new User("Rin","2"));
      libManager.addUser(new User("Tielle","3"));
      
      System.out.println(libManager.books);
      
      LibraryUserTask t1 = new LibraryUserTask(libManager, "1", "68763486423", "borrow");
      LibraryUserTask t2 = new LibraryUserTask(libManager, "2", "68763486529", "borrow");
      LibraryUserTask t3 = new LibraryUserTask(libManager, "3", "68763486529", "reserve");
      LibraryUserTask t4 = new LibraryUserTask(libManager, "2", "68763486423", "reserve");
      LibraryUserTask t5 = new LibraryUserTask(libManager, "1", "68763486423", "return");
      LibraryUserTask t6 = new LibraryUserTask(libManager, "2", "68763486529", "return");
      
      t1.start();
      t2.start();
      t3.start();
      t4.start();
      t5.start();
      t6.start();
      
      try {
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		t6.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
      System.out.println("\nFinal state of users:");
      System.out.println(libManager.users);

      System.out.println("\nFinal state of books:");
      System.out.println(libManager.books);
      
   
    
      
   }
}

