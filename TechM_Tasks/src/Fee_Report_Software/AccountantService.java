package Fee_Report_Software;

public class AccountantService {
	private DatabaseService dbs = new DatabaseService();
	
	public int login(String name,String password) {
		return dbs.loginAccountant(name,password);
	}
	
	public boolean addStudent(Student student,int id) 
	{
		return dbs.addStudents(student,id);
	}
	
	public void viewAllStudent() 
	{
		dbs.viewAllStudents();
	}
	
	public void editStudent(int id,String new_name,String new_email,String new_course,double new_fee,double new_paid,double new_due,String new_address,String new_phone) 
	{
		dbs.editStudents(id,new_name,new_email,new_course,new_fee,new_paid,new_due,new_address,new_phone);
	}
	
	public void deleteStudent(int id) 
	{
		dbs.deleteStudents(id);
	}
	
	public void feePendingStudent() 
	{
		dbs.feePendingStudents();
	}
}
