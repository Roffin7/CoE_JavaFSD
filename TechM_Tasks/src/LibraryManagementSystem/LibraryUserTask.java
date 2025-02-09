package LibraryManagementSystem;

public class LibraryUserTask extends Thread {
    private LibraryManager libraryManager;
    private String userID;
    private String ISBN;
    private String taskType;  

    public LibraryUserTask(LibraryManager libraryManager, String userID, String ISBN, String taskType) {
        this.libraryManager = libraryManager;
        this.userID = userID;
        this.ISBN = ISBN;
        this.taskType = taskType;
    }

    @Override
    public void run() {
        try {
            switch (taskType.toLowerCase()) {
                case "borrow":
                    libraryManager.borrowBook(ISBN, userID);
                    break;
                case "return":
                    libraryManager.returnBook(ISBN, userID);
                    break;
                case "reserve":
                    libraryManager.reserveBook(ISBN, userID);
                    break;
                default:
                    System.out.println("Invalid task type.");
            }
        } catch (Exception e) {
            System.out.println("Exception in " + taskType + " operation: " + e.getMessage());
        }
    }
}
