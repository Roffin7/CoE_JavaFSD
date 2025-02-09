package LibraryManagementSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class LibrarySystem implements ILibrary {
	   protected List<Book> books;
	   protected List<User> users;

	   public abstract void addBook(Book book);
	   public abstract void addUser(User user);
	   
	   
	
	public LibrarySystem() {
		this.books = new ArrayList<>();
		this.users = new ArrayList<>();
	}
	
	
	public void borrowBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException, MaxBooksAllowedException
	   {
		boolean flag = true;
		boolean uflag = false;
		
		if (books != null && users != null) 
		{
			for(User user:users) 
			{
				for(Book book:books) 
				{
					if ((book.getISBN().equals(ISBN) && user.getUserID().equals(userID))) 
					{
						flag = false;
						uflag = true;
						user.setBorrowedBooks(book);
						System.out.println("Borrowed Books added:" + book);
						books.remove(book);
						System.out.println("Book removed from books");
						break;
					}
				}
				if (uflag) 
				{
					break;
				}
			}
			
			if (flag) 
			{
				throw new BookNotFoundException("Book unavailable may be user too");
			}
		}
		else 
		{
			System.out.println("Invalid Books and User");
		}
		   
	   }
	   
	   public void returnBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException
	   {
		   boolean flag = true;
		   boolean uflag = false;
		   for(User user:users) 
		   {
			   if (user.getUserID().equals(userID)) 
			   {
				   List<Book> borrowedBooks = user.getBorrowedBooks();
				   
				   for(Book book:borrowedBooks) 
				   {
					   if (book.getISBN().equals(ISBN)) 
					   {
						   flag = false;
						   uflag = true;
						   books.add(book);
						   System.out.println("Book returned");
						   borrowedBooks.remove(book);
						   break;
					   }
				   }
			   }
			   if (uflag) 
			   {
				   break;
			   }
		   }
		   
		   if(flag) 
		   {
			   throw new BookNotFoundException("Invalid Book May Be User Too");
		   }
		   
	   }
	   
	   public void reserveBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException
	   {
		   
	   }
	   
	   public Book searchBook(String title) 
	   {
		   
		   for(Book book:books) 
		   {
			   if (book.getTitle().equals(title)) 
			   {
				   return book;
			   }
		   }
		   
		   System.out.println("Book Not Found");
		   return null;
	   }
	   
	   

	}

