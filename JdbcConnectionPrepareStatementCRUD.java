package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcConnectionPrepareStatementCRUD {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;
		while (!exit) {
			System.out.println("Enter the number one number from below this");
			System.out.println("1. For to display the details");
			System.out.println("2. For to insert the data");
			System.out.println("3. For to update the data");
			System.out.println("4. For to delet the data");
			System.out.println("5. For exit");
			int number = scanner.nextInt();
			switch (number) {
			case 1:
				try {
					Connection connection = DriverManager.getConnection(url);
					//System.out.println("database Connected...........");
					String select = "select * from assignement.bank where AcHolderNumber like CONCAT('%',?)";
					PreparedStatement preparedStatement = connection.prepareStatement(select);
					//System.out.println("Platform Created...........");
					//to display the data from database
					System.out.println("Enter the Last Digits of Bank Account Number : ");
					int acNumber = scanner.nextInt();
					preparedStatement.setInt(1, acNumber);
					ResultSet resultSet = preparedStatement.executeQuery();
					System.out.println("resultset connected...");
					if (resultSet.next()) {
						for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
							System.out.printf("%-15s", resultSet.getMetaData().getColumnName(i));
						}
						System.out.println();
						do {
							for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
								System.out.printf("%-15s", resultSet.getString(i));
							}
						} while (resultSet.next());
						System.out.println();
					} else {
						System.out.println("No results found for the given account number.");
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				//to insert the data in database
				String insert = "insert into assignement.bank (BName, AcHoldName, IFSCcode, MobileNumber, BankBalance, BankId, AcHolderNumber) value(?,?,?,?,?,?,?)";
				try {
					Connection connection = DriverManager.getConnection(url);
					//System.out.println("database connected...");
					PreparedStatement preparedStatement = connection.prepareStatement(insert);
					//System.out.println("platform connected...");
					System.out.println("Enter the Bank name: ");
					String bname = scanner.next();
					System.out.println("Enter the Acholder name: ");
					String acname = scanner.next();
					System.out.println("Enter the ifscCode number:");
					String ifsc = scanner.next();
					System.out.println("Enter the mobile number:");
					String mobile = scanner.next();
					System.out.println("Enter the bank balance:");
					int balance = scanner.nextInt();
					System.out.println("Enter the bank id:");
					int bankid = scanner.nextInt();
					System.out.println("Enter the account number :");
					int acNumber = scanner.nextInt();
					preparedStatement.setString(1, bname);
					preparedStatement.setString(2, acname);
					preparedStatement.setString(3, ifsc);
					preparedStatement.setString(4, mobile);
					preparedStatement.setInt(5, balance);
					preparedStatement.setInt(6, bankid);
					preparedStatement.setInt(7, acNumber);
					int result = preparedStatement.executeUpdate();
					if (result > 0) {
						System.out.println("data inserted successfully...");
						insert = "select * from assignement.bank where AcHolderNumber like CONCAT('%',?)";
						preparedStatement = connection.prepareStatement(insert);
						System.out.println("Enter the  Last Digits of inserted Bank Account Number Example (0 to 9): ");
						int acNumber1 = scanner.nextInt();
						preparedStatement.setInt(1, acNumber1);
						ResultSet resultSet = preparedStatement.executeQuery();
						System.out.println("resultset connected...");
						for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
							System.out.printf("%-15s", resultSet.getMetaData().getColumnName(i));
						}
						System.out.println();
						while (resultSet.next()) {
							for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
								System.out.printf("%-15s", resultSet.getString(i));
							}
							System.out.println();
						}

					} else {
						System.out.println("data not inserted...");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				//to update the data in database
				String update = "update assignement.bank set bankbalance=? where bankid=?";
				try {
					Connection connection = DriverManager.getConnection(url);
					PreparedStatement updatePreparedStatement = connection.prepareStatement(update);
					System.out.println("Enter the bank balance to update:");
					int balance1 = scanner.nextInt();
					System.out.println("Enter the bank id :");
					int bankid1 = scanner.nextInt();
					updatePreparedStatement.setInt(1, balance1);
					updatePreparedStatement.setInt(2, bankid1);
					int updated = updatePreparedStatement.executeUpdate();
					if (updated > 0) {
						System.out.println("data updated...");
						String select = "select * from assignement.bank where AcHolderNumber like CONCAT('%',?)";
						PreparedStatement preparedStatement = connection.prepareStatement(select);
						System.out.println("Enter the  Last Digits of inserted Bank Account Number Example (0 to 9): ");
						int acNumber1 = scanner.nextInt();
						preparedStatement.setInt(1, acNumber1);
						ResultSet resultSet = preparedStatement.executeQuery();
						System.out.println("resultset connected...");
						for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
							System.out.printf("%-15s", resultSet.getMetaData().getColumnName(i));
						}
						System.out.println();
						while (resultSet.next()) {
							for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
								System.out.printf("%-15s", resultSet.getString(i));
							}
							System.out.println();
						}
					} else {
						System.out.println("data not updated...");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:
				//to delete the data from database
				String delete = "delete FROM assignement.bank where bankid=?";
				try {
					Connection connection = DriverManager.getConnection(url);
					PreparedStatement deletPreparedStatement = connection.prepareStatement(delete);
					System.out.println("Enter the bankid to delete from the record:");
					int bankid = scanner.nextInt();
					deletPreparedStatement.setInt(1, bankid);
					int deleted = deletPreparedStatement.executeUpdate();
					if (deleted > 0) {
						System.out.println("data deleted...");
					} else {
						System.out.println("data not deleted...");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 5:
				// Exit the program
				exit = true;
				break;
			default:
				System.err.println("Plesase select above the numbers..");

			}
			if (!exit) {
				System.out.println("Do you want to select another option? (y/n)");
				String choice = scanner.next();
				if (!choice.equalsIgnoreCase("y")) {
					exit = true;
				}
			}
		}
		System.out.println("Exiting program...");
	}
}



