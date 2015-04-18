package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import main.TestBase;
import main.WebElements;

public class SQLAccess extends TestBase implements WebElements {

	private String dbDriverClass;
	private String dbUrl;
	private String dbUserName;
	private String dbPassWord;
	private static String filePath;

	private static Connection connect = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	public SQLAccess(String dbDriverClass, String dbUrl, String dbUserName,
			String dbPassWord) {

		this.dbDriverClass = dbDriverClass;
		this.dbUrl = dbUrl;
		this.dbUserName = dbUserName;
		this.dbPassWord = dbPassWord;
		//this.filePath = filePath;

	}

	public void SetUpDataBase() throws Exception {

		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName(dbDriverClass);

			// Setup the connection with the DB
			connect = DriverManager
					.getConnection(dbUrl, dbUserName, dbPassWord);

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			Log.info(connect.toString());

		} catch (SQLException e) {
			Log.info(e.getSQLState());
		}

	}

	@SuppressWarnings("deprecation")
	public static void generateMethodSummaryReport(String suite, String testname)
			throws Exception {

		PropertyUtils.loadPropertyFile(proprtyFile);

		String dbDriverClass = PropertyUtils.getProperty("dbDriverClass");
		String dbUrl = PropertyUtils.getProperty("dbUrl");
		String dbUserName = PropertyUtils.getProperty("dbUserName");
		String dbPassWord = PropertyUtils.getProperty("dbPassWord");

		if (testname == null) {
			return;
		}

		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName(dbDriverClass);

			// Setup the connection with the DB
			connect = DriverManager
					.getConnection(dbUrl, dbUserName, dbPassWord);

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			// PreparedStatements can use variables and are more efficient
			preparedStatement = connect
					.prepareStatement("insert into  feedback.SUITE_MethodSummaryReport values (default, ?, ?, ?, ? , ?, ?)");

			preparedStatement.setString(1, suite);
			preparedStatement.setString(2, testname);
			preparedStatement.setString(3, testname);
			preparedStatement.setString(4, testname);
			preparedStatement.setDate(5, new java.sql.Date(2009, 12, 11));
			preparedStatement.setString(6, testname);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw e;

		} finally {

			close();
		}

	}

	public static void insertReport() throws Exception {

		PropertyUtils.loadPropertyFile(proprtyFile);

		String dbDriverClass = PropertyUtils.getProperty("dbDriverClass");
		String dbUrl = PropertyUtils.getProperty("dbUrl");
		String dbUserName = PropertyUtils.getProperty("dbUserName");
		String dbPassWord = PropertyUtils.getProperty("dbPassWord");

		Path testOutput = Paths.get("test-output/html");

		if (testOutput.toFile().exists()) {
			try {
				System.out.println("Real path: " + testOutput.toRealPath(LinkOption.NOFOLLOW_LINKS));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("The directory does not exist");
		}

		String[] children = testOutput.toFile().list();

		try {
			filePath = urlsFile;
			
			} catch (NullPointerException e) {
			
				e.getMessage();
		}
		
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName(dbDriverClass);

			// Setup the connection with the DB
			connect = DriverManager.getConnection(dbUrl, dbUserName, dbPassWord);

			String sql = "insert into feedback.report (html_files, urls) values (?, ?)";
			PreparedStatement statement = connect.prepareStatement(sql);

			for (int i = 0; i < children.length; i++) {

				// TODO: unkown source error to be fixed
				InputStream inputStream = new FileInputStream(new File(children[i]));

				statement.setBlob(1, inputStream);
			}
			
			if (filePath != null) {
			InputStream inputStream = new FileInputStream(new File(filePath));
			statement.setBlob(2, inputStream);
			}
			
			else {
				TestBase.createFile(urlsFile);
				Log.info("Empty urls.txt file was created. Test urls were retreived from property file.");
				filePath = urlsFile;
				InputStream inputStream = new FileInputStream(new File(filePath));
				statement.setBlob(2, inputStream);
			}
			
			int row = statement.executeUpdate();

			if (row > 0) {
				System.out.println("Files were inserted into the db.");
			}

			close();

		} catch (SQLException ex) {

			ex.printStackTrace();

		} catch (IOException ex) {

			ex.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	public void readDataBase() throws Exception {

		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName(dbDriverClass);

			// Setup the connection with the DB
			connect = DriverManager
					.getConnection(dbUrl, dbUserName, dbPassWord);

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			// Result set get the result of the SQL query
			resultSet = statement
					.executeQuery("select * from feedback.comments");
			writeResultSet(resultSet);

			// PreparedStatements can use variables and are more efficient
			preparedStatement = connect
					.prepareStatement("insert into  feedback.comments values (default, ?, ?, ?, ? , ?, ?)");
			// "myuser, webpage, datum, summery, COMMENTS from feedback.comments");
			// Parameters start with 1
			preparedStatement.setString(1, "Test");
			preparedStatement.setString(2, "TestEmail");
			preparedStatement.setString(3, "TestWebpage");
			preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
			preparedStatement.setString(5, "TestSummary");
			preparedStatement.setString(6, "TestComment");
			preparedStatement.executeUpdate();

			preparedStatement = connect
					.prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments");
			resultSet = preparedStatement.executeQuery();
			writeResultSet(resultSet);

			// Remove again the insert comment
			preparedStatement = connect
					.prepareStatement("delete from feedback.comments where myuser= ? ; ");
			preparedStatement.setString(1, "Test");
			preparedStatement.executeUpdate();

			resultSet = statement
					.executeQuery("select * from feedback.comments");
			writeMetaData(resultSet);

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	private void writeMetaData(ResultSet resultSet) throws SQLException {
		// Now get some metadata from the database
		// Result set get the result of the SQL query

		System.out.println("The columns in the table are: ");

		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
			System.out.println("Column " + i + " "
					+ resultSet.getMetaData().getColumnName(i));
		}
	}

	private static void writeResultSet(ResultSet resultSet) throws SQLException {

		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			String user = resultSet.getString("myuser");
			String website = resultSet.getString("webpage");
			String summary = resultSet.getString("summary");
			Date date = resultSet.getDate("datum");
			String comment = resultSet.getString("comments");
			System.out.println("User: " + user);
			System.out.println("Website: " + website);
			System.out.println("Summary: " + summary);
			System.out.println("Date: " + date);
			System.out.println("Comment: " + comment);
		}
	}

	// You need to close the resultSet
	private static void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}
