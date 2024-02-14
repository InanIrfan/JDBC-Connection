package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JdbcConnectionPrepareStatement
{
	public static void main(String[] args) {
	String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
	String query = "select * from assignement.bank where AcHolderNumber like CONCAT('%',?)";
	try 
	{
		Connection connection = DriverManager.getConnection(url);
		System.out.println("Connected...........");
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
		System.out.println("Platform Created...........");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Last Digits of Bank Account Number : ");
		int acNumber = scanner.nextInt();
		preparedStatement.setInt(1, acNumber);
		ResultSet resultSet  = preparedStatement.executeQuery();
		System.out.println("resultset connected...");
		if(resultSet.next())
		{
		for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++)
		 {
                System.out.printf("%-15s",resultSet.getMetaData().getColumnName(i));
         }
          System.out.println();
         do
         {
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) 
                {
                    System.out.printf("%-15s",resultSet.getString(i));
                }
          }while (resultSet.next());
          System.out.println();	
		}
        else
		{
			System.out.println("No results found for the given account number.");
		}
		
	} 
	catch (SQLException e) 
	{
		e.printStackTrace();
	}
 }
}
