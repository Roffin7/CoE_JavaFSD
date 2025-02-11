package Fee_Report_Software;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean exit = true;
		
		while (exit) 
		{
			System.out.println("-----Fee Report System-----");
			System.out.print("1.Admin Login\n2.Accountant Login\n3.Exit\n");
			System.out.print("\nChoose an option: ");
			int n = sc.nextInt();sc.nextLine();
			
			switch (n) 
			{
				case 1:{
							AdminUI adminUI = new AdminUI();
							adminUI.start();
							break;
					   }
				
				case 2:{
							AccountantUI accountantUI = new AccountantUI();
							break;
					   }
				
				case 3:{
							exit = false;
							break;
					   }
				
				default:{
							System.out.println("Invalid Number");
						}
			}
		}
		
		System.out.println("Thank you!");
		
	}

}
