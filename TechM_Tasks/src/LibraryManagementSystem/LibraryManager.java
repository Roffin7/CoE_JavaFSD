package LibraryManagementSystem;

public class LibraryManager extends LibrarySystem {
	
	LibraryManager()
	{
		super();
	}

	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		if (book != null) 
		{
			books.add(book);
			System.out.println("Book added" + book);
		}
		else 
		{
			System.out.println("Invalid book");
		}
		
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		if (user != null) 
		{
			users.add(user);
			System.out.println("User added: " + user);
		}
		else 
		{
			System.out.println("Invalid user");
		}
		
	}

	}

