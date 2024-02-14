package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnection
{
	public static void main(String[] args)
	{
		String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
		String query = "select * from assignement.emp where empno = 7925";
		try {
			Connection connection = DriverManager.getConnection(url);
			System.out.println("database connected.....");
			Statement stmt = connection.createStatement();
			System.out.println("platform connected.....");
			ResultSet result = stmt.executeQuery(query);
			System.out.println("resultSet connected.....");
			while(result.next())
			{
				int empno = result.getInt(1);
				System.out.println("Empolyee Name EmpNo: "+empno);
				String empName = result.getString(2);
				System.out.println("Employee Name is: "+empName);
				String job = result.getString(3);
				System.out.println("Employee job is: "+job);
				String mgr = result.getString(4);
				System.out.println("Employee mgr is: "+mgr);	
				String date = result.getString(5);
				System.out.println("Employee joining date: "+date);
				int sal = result.getInt(6);
				System.out.println("Employee salary is: "+sal);
				String comm = result.getString(7);
				System.out.println("Employee commision is: "+comm);
				int depet = result.getInt(8);
				System.out.println("Employee deptNo is: "+depet);
				
			}		
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
