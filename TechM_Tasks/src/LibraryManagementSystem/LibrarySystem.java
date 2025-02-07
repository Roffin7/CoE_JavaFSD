package LibraryManagementSystem;

import java.util.List;

public abstract class LibrarySystem implements ILibrary {
	   protected List<Book> books;
	   protected List<User> users;

	   public abstract void addBook(Book book);
	   public abstract void addUser(User user);

	}

