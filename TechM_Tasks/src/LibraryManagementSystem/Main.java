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
      
      try {
		libManager.borrowBook("68763486423","1");
		libManager.borrowBook("68763486529","1");
	} catch (BookNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UserNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (MaxBooksAllowedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
    System.out.println(libManager.users);  
    System.out.println(libManager.books);
    
    try {
		libManager.reserveBook("68763486529","2");
		libManager.reserveBook("68763486529","3");
	} catch (BookNotFoundException | UserNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    try {
		libManager.returnBook("68763486423","1");
		libManager.returnBook("68763486529","1");
	} catch (BookNotFoundException | UserNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    System.out.println(libManager.users);  
    System.out.println(libManager.books);
    
      
   }
}

