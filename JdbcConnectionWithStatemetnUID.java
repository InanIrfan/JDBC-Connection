package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnectionWithStatemetnUID 
{
    public static void main(String[] args)
    {
		String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
		String query = "insert into assignement.emp (empno,ename,job,mgr,hiredate,sal,comm,deptno) value(1259,'Farook','SE',7395,'01-Jan-24',500000,30000,1)";
		String update = "update assignement.emp set hiredate='17-JAN-24' where ename='farook'";
		String delete = "delete FROM assignement.emp where ename='farook'";
		try {
			Connection connection = DriverManager.getConnection(url);
			System.out.println("database connected...");
			Statement statement = connection.createStatement();
			System.out.println("platform connected...");
			int result = statement.executeUpdate(query);
			if (result > 0)
			{
				System.out.println("data inserted successfully...");
				query = "select * from assignement.emp where ename='farook'";
				ResultSet resultSet = statement.executeQuery(query);
				System.out.println("resultset connected...");
				 for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++)
				 {
		                System.out.print(resultSet.getMetaData().getColumnName(i) + "\t");
		         }
		          System.out.println();
		         while (resultSet.next())
		         {
		                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) 
		                {
		                    System.out.print(resultSet.getString(i) + "\t");
		                }
		          }
		          System.out.println();
			}
			else
			{
				System.out.println("data not inserted...");
			}
			int updated = statement.executeUpdate(update);
			if(updated > 0)
			{
				System.out.println("data updated...");
				ResultSet resultSet = statement.executeQuery(query);
				System.out.println("resultset connected...");
				for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++)
				 {
		                System.out.print(resultSet.getMetaData().getColumnName(i) + "\t");
		         }
		          System.out.println();
		         while (resultSet.next())
		         {
		                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) 
		                {
		                    System.out.print(resultSet.getString(i) + "\t");
		                }
		          }
		          System.out.println();
			}
			else
			{
				System.out.println("data not updated...");
			}
			int deleted = statement.executeUpdate(delete);
			if(deleted > 0)
			{
				System.out.println("data deleted...");
			}
			else
			{
				System.out.println("data not deleted...");
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
