package Fee_Report_Software;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseService {
	private Connection conn;
	
	public DatabaseService() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			//System.out.println("Driver loaded successfully");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fee_management","root","password");
			//System.out.println("Database Connected Successfully");
		}
		catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Failed to connect database");
		}
	}
	
	public boolean loginOperations(String name,String password) {
		String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
		try(PreparedStatement pst  = conn.prepareStatement(query))
		{
			pst.setString(1, name);
			pst.setString(2, password);
			ResultSet res = pst.executeQuery();
			return res.next();
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean registration(String name,String password)
	{
		String query = "INSERT INTO admin (username,password) VALUES(?,?)";
		
		try(PreparedStatement pst = conn.prepareStatement(query))
		{
			pst.setString(1, name);
			pst.setString(2, password);
			int rows = pst.executeUpdate();
			return rows > 0;
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			return false;	
		}
	}
	
	public boolean addAccountants(Accountant accountant) 
	{
		String query = "INSERT INTO accountant (name,email,phone,password) VALUES(?,?,?,?)";
		
		try(PreparedStatement pst = conn.prepareStatement(query))
		{
			pst.setString(1, accountant.getName());
			pst.setString(2, accountant.getEmail());
			pst.setString(3, accountant.getPhone());
			pst.setString(4, accountant.getPassword());
			int rows = pst.executeUpdate();
			return rows > 0;
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public void viewAccountants()
	{
		String query = "SELECT * FROM accountant";
		
		try(Statement st = conn.createStatement())
		{
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) 
			{
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String phone = rs.getString(4);
				String password = rs.getString(5);
				
				System.out.println("Id:"+id+", "+"Name:"+name+", "+"Email:"+email+", "+"Phone:"+phone+", "+"Password:"+password+".");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
	}
	
	public void editAccountants(int id,String new_name,String new_email,String new_phone,String new_password) 
	{
		String query = "UPDATE accountant SET name = ?,email = ?,phone = ?,password = ? WHERE id = ?";
		
		try(PreparedStatement pst = conn.prepareStatement(query))
		{
			pst.setString(1, new_name);
			pst.setString(2, new_email);
			pst.setString(3, new_phone);
			pst.setString(4, new_password);
			pst.setInt(5, id);
			
			int rows = pst.executeUpdate();
			
			if (rows > 0) 
			{
				System.out.println("\nSuccessfully Updated");
			}
			else 
			{
				System.out.println("\nInvalid ID");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void deleteAccountants(int id) 
	{
		String query = "DELETE FROM accountant WHERE id = ?";
		
		try(PreparedStatement pst = conn.prepareStatement(query))
		{
			pst.setInt(1, id);
			int row = pst.executeUpdate();
			
			if (row > 0) 
			{
				System.out.println("\nData deleted successfully");
			}
			else 
			{
				System.out.println("\nInvalid ID");
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
		
		// -----------Database Connection for Students-----------
		
		public int loginAccountant(String name,String password) 
		{
			String query = "SELECT * FROM accountant WHERE name = ? AND password = ?";
			try(PreparedStatement pst  = conn.prepareStatement(query))
			{
				pst.setString(1, name);
				pst.setString(2, password);
				ResultSet res = pst.executeQuery();
				
				if (res.next()) {  // Move the cursor to the first row
		            int id = res.getInt("id");  // Use column label or index
		            return id;
		        } else {
		            System.out.println("Invalid credentials.");
		            return 0;
		        }
			}
			catch(SQLException e) 
			{
				e.printStackTrace();
				return 0;
			}
		}
		
		public boolean addStudents(Student student,int id) 
		{
			String query = "INSERT INTO student (name,email,phone,course,fee,paid,due,address,accountant_id) VALUES(?,?,?,?,?,?,?,?,?)";
			
			try(PreparedStatement pst = conn.prepareStatement(query))
			{
				pst.setString(1, student.getName());
				pst.setString(2, student.getEmail());
				pst.setString(3, student.getPhone());
				pst.setString(4, student.getCourse());
				pst.setDouble(5, student.getFee());
				pst.setDouble(6, student.getPaid());
				pst.setDouble(7, student.getDue());
				pst.setString(8, student.getAddress());
				pst.setInt(9, id);
				int rows = pst.executeUpdate();
				return rows > 0;
			}
			catch(SQLException e) 
			{
				e.printStackTrace();
				return false;
			}
		}
		
		public void viewAllStudents() 
		{
			String query = "SELECT * FROM student";
			
			try(Statement st = conn.createStatement())
			{
				ResultSet rs = st.executeQuery(query);
				
				while (rs.next()) 
				{
					int id = rs.getInt(1);
					String name = rs.getString(2);
					String email = rs.getString(3);
					String phone = rs.getString(4);
					String course = rs.getString(5);
					double fee = rs.getDouble(6);
					double paid = rs.getDouble(7);
					double due = rs.getDouble(8);
					String address = rs.getString(9);
					int accountant_id = rs.getInt(10);
					System.out.println("Student_Id: "+id+", "+"Name: "+name+", "+"Email: "+email+", "+"Phone: "+phone+", "+"Course: "+course+", "+"Fee: "+fee+", "+"Paid: "+paid+", "+"Due: "+due+", "+"Address: "+address+", "+"Accountant_id: "+accountant_id+".");
				}
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}	
		}
		
		public void editStudents(int id,String new_name,String new_email,String new_course,double new_fee,double new_paid,double new_due,String new_address,String new_phone) 
		{
			String query = "UPDATE student SET name = ?,email = ?,phone = ?,course = ?,fee = ?,paid = ?,due = ?,address = ? WHERE id = ?";
			
			try(PreparedStatement pst = conn.prepareStatement(query))
			{
				pst.setString(1, new_name);
				pst.setString(2, new_email);
				pst.setString(3, new_phone);
				pst.setString(4, new_course);
				pst.setDouble(5, new_fee);
				pst.setDouble(6, new_paid);
				pst.setDouble(7, new_due);
				pst.setString(8, new_address);
				pst.setInt(9, id);
				int rows = pst.executeUpdate();
				
				if (rows > 0) 
				{
					System.out.println("\nSuccessfully Updated");
				}
				else 
				{
					System.out.println("\nInvalid ID");
				}
			}
			catch(SQLException e) 
			{
				e.printStackTrace();
			}
			
		}
		
		public void deleteStudents(int id) 
		{
			String query = "DELETE FROM student WHERE id = ?";
			
			try(PreparedStatement pst = conn.prepareStatement(query))
			{
				pst.setInt(1, id);
				int row = pst.executeUpdate();
				
				if (row > 0) 
				{
					System.out.println("\nData deleted successfully");
				}
				else 
				{
					System.out.println("\nInvalid ID");
				}
			}
			catch(SQLException e) 
			{
				e.printStackTrace();
			}
			
		}
		
		public void feePendingStudents() 
		{
			String query ="SELECT * FROM student WHERE due > 0";
			try (PreparedStatement ps = conn.prepareStatement(query))
			{
			        ResultSet rs = ps.executeQuery();

			        System.out.println("\n----- Fee Pending Students -----");
			        while (rs.next()) {
			            System.out.println("ID: " + rs.getInt("id"));
			            System.out.println("Name: " + rs.getString("name"));
			            System.out.println("Email: " + rs.getString("email"));
			            System.out.println("Phone: " + rs.getString("phone"));
			            System.out.println("Course: " + rs.getString("course"));
			            System.out.println("Fee: " + rs.getDouble("fee"));
			            System.out.println("Paid: " + rs.getDouble("paid"));
			            System.out.println("Due: " + rs.getDouble("due"));
			            System.out.println("Address: " + rs.getString("address"));
			            System.out.println("-----------------------------");
			        }
			    } catch (SQLException e) {
			        System.out.println("\nError fetching fee pending students: " + e.getMessage());
			    }
		}

}
