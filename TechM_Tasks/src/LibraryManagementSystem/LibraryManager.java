package LibraryManagementSystem;

public class LibraryManager extends LibrarySystem {

	@Override
	public void borrowBook(String ISBN, String userID)
			throws BookNotFoundException, UserNotFoundException, MaxBooksAllowedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void returnBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reserveBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Book searchBook(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}
	   // Detailed implementations for all abstract methods and interface methods
	   // Thread-safe implementations for borrowBook, returnBook methods
	   // Exception handling within all methods
	}

