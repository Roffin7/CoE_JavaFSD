package LibraryManagementSystem;

import java.util.*;

public abstract class LibrarySystem implements ILibrary {
    protected List<Book> books;
    protected List<User> users;
    protected Map<String, Queue<User>> reservationMap; // Map to manage reservation queue for each book (ISBN)

    public LibrarySystem() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.reservationMap = new HashMap<>();
    }

    public abstract void addBook(Book book);
    public abstract void addUser(User user);

    @Override
    public synchronized void borrowBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException, MaxBooksAllowedException {
        boolean bookFound = false;
        boolean userFound = false;

        for (User user : users) {
            for (Book book : books) {
                if (book.getISBN().equals(ISBN) && user.getUserID().equals(userID)) {
                    userFound = true;
                    bookFound = true;
                    user.setBorrowedBooks(book);
                    System.out.println("Book borrowed successfully: " + book.getTitle());
                    books.remove(book);
                    return;
                }
            }
        }

        if (!bookFound || !userFound) {
            throw new BookNotFoundException("Book unavailable or user not found.");
        }
    }

    @Override
    public synchronized void returnBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException {
        boolean bookReturned = false;

        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                List<Book> borrowedBooks = user.getBorrowedBooks();
                for (Book book : borrowedBooks) {
                    if (book.getISBN().equals(ISBN)) {
                        borrowedBooks.remove(book);
                        bookReturned = true;
                        System.out.println("Book returned successfully: " + book.getTitle());

                        // Check if there's a user in the reservation queue for this book
                        if (reservationMap.containsKey(ISBN) && !reservationMap.get(ISBN).isEmpty()) {
                            User nextUser = reservationMap.get(ISBN).poll();
                            nextUser.setBorrowedBooks(book); 
                            System.out.println("Notification: The book has been borrowed by " + nextUser.getUserID() + ".");
                        } else {
                            books.add(book); 
                        }
                        return;
                    }
                }
            }
        }

        if (!bookReturned) {
            throw new BookNotFoundException("Book not found in user's borrowed list.");
        }
    }


    @Override
    public synchronized void reserveBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException {
        boolean bookAvailable = false;

        
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                bookAvailable = true;
                System.out.println("Book is available. Borrowing it now...");
                try {
                    borrowBook(ISBN, userID);  
                } catch (MaxBooksAllowedException e) {
                    System.out.println("User has reached the maximum allowed borrowed books.");
                }
                return;  
            }
        }

        // If the book is not available, add the user to the reservation queue
        User user = findUserByID(userID);
        if (user == null) throw new UserNotFoundException("User not found.");

        reservationMap.putIfAbsent(ISBN, new LinkedList<>());
        reservationMap.get(ISBN).add(user);
        System.out.println("Book is currently borrowed. You have been added to the reservation list.");
    }


    
    private User findUserByID(String userID) {
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public synchronized Book searchBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        System.out.println("Book not found.");
        return null;
    }
}
