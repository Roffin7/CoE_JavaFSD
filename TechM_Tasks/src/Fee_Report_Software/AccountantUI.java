package Fee_Report_Software;

import java.util.Scanner;

public class AccountantUI {
    private AccountantService accountantService = new AccountantService();
    private Scanner sc = new Scanner(System.in);
    private boolean loggedInAccountant = false;
    private int id;

    public void start() {
        boolean exit = true;
        while (exit) {
            System.out.println("\n----- Accountant Login Panel -----");
            System.out.print("Do you want to exit (y/n): ");
            char c = sc.next().charAt(0);
            sc.nextLine(); 
            if (c == 'y' || c == 'Y') {
                exit = false;
            } else {
                System.out.print("Enter Accountant Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Password: ");
                String password = sc.nextLine();

                id = accountantService.login(name, password);

                if (id != 0) {
                    System.out.println("\nLogin successful! Welcome, " + name);
                    displayMenu();
                } else {
                    System.out.println("\nInvalid name or password. Please try again.");
                }
            }
        }
    }

    private void displayMenu() {
        boolean exit = true;
        while (exit) {
            System.out.println("\n----- Accountant Menu -----");
            System.out.println("1.Add Student");
            System.out.println("2.View All Students");
            System.out.println("3.Edit Student");
            System.out.println("4.Delete Student");
            System.out.println("5.View Fee Pending Students");
            System.out.println("6.Logout");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudent();
                    break;
                case 3:
                    editStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    feePendingStudents();
                    break;
                case 6:
                    System.out.println("Logging out...");
                    exit = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addStudent() {
        System.out.println("\n--- Add Student ---");
	    System.out.print("Enter Student Name: ");
	    String name = sc.nextLine();
	    System.out.print("Enter Student Email: ");
	    String email = sc.nextLine();
	    System.out.print("Enter Student course: ");
	    String course = sc.nextLine();
	    System.out.print("Enter Student fee: ");
	    double fee = sc.nextDouble();sc.nextLine();
	    System.out.print("Enter Student paid: ");
	    double paid = sc.nextDouble();sc.nextLine();
	    double due = fee - paid;
	    System.out.print("Enter Student Address: ");
	    String address = sc.nextLine();
	    System.out.print("Enter Student Phone: ");
	    String phone = sc.nextLine();
	    
	    Student student = new Student();
	    
	    student.setName(name);
	    student.setEmail(email);
	    student.setCourse(course);
	    student.setFee(fee);
	    student.setPaid(paid);
	    student.setDue(due);
	    student.setAddress(address);
	    student.setPhone(phone);
	    
	    if(accountantService.addStudent(student,id)) 
	    {
	    	System.out.println("\nStudent added successfully");
	    }
	    else 
	    {
	    	System.out.println("\nFailed to add Student");
	    }
    }

    private void viewAllStudent() {
    	accountantService.viewAllStudent();
    }

    private void editStudent() {
        System.out.println("\n--- Edit Student ---");
        System.out.print("\nEnter Student id: ");
		int id = sc.nextInt();sc.nextLine();
		System.out.println();
		
        System.out.println("\n--- Add Student ---");
	    System.out.print("Enter New Student Name: ");
	    String new_name = sc.nextLine();
	    System.out.print("Enter New Student Email: ");
	    String new_email = sc.nextLine();
	    System.out.print("Enter New Student course: ");
	    String new_course = sc.nextLine();
	    System.out.print("Enter New_Student fee: ");
	    double new_fee = sc.nextDouble();sc.nextLine();
	    System.out.print("Enter New_Student paid: ");
	    double new_paid = sc.nextDouble();sc.nextLine();
	    double new_due = new_fee - new_paid;
	    System.out.print("Enter Student Address: ");
	    String new_address = sc.nextLine();
	    System.out.print("Enter Student Phone: ");
	    String new_phone = sc.nextLine();
	    
	    accountantService.editStudent(id,new_name,new_email,new_course,new_fee,new_paid,new_due,new_address,new_phone);
		
    }

    private void deleteStudent() {
        System.out.println("\n--- Delete Student ---");
        System.out.print("\nEnter Accountant ID to Delete: ");
        int id = sc.nextInt();sc.nextLine();
        
        accountantService.deleteStudent(id);
    }

    private void feePendingStudents() {
        System.out.println("\n--- View Fee Pending Students ---");
        accountantService.feePendingStudent();
    }
}
