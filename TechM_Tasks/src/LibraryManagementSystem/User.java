package LibraryManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class User {
	   private String name;
	   private String userID;
	   private List<Book> borrowedBooks;
	   
	public User(String name, String userID) {
		this.name = name;
		this.userID = userID;
		this.borrowedBooks = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public List<Book> getBorrowedBooks() {
		return borrowedBooks;
	}

	public void setBorrowedBooks(Book borrowedBooks) {
		this.borrowedBooks.add(borrowedBooks);
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", userID=" + userID + ", borrowedBooks=" + borrowedBooks + "]";
	}
	
	
	
	   
	}
