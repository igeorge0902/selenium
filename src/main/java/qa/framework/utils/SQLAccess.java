package main.java.qa.framework.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
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

import org.apache.ibatis.jdbc.ScriptRunner;

import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.main.WebElements;

public class SQLAccess extends TestBase implements WebElements {
	
	private String dbDriverClass;
	private String dbUrl;
	private String dbUserName;
	private String dbPassWord;
	//private static String filePath;
	private static String index;
	private static String output;
	private static String overview;
	private static String reportng_css;
	private static String reportng_js;
	private static String sorttable;
	private static String suite_groups;
	private static String suite_test1;
	private static String suite_test2;
	private static String suite_test3;
	private static String suite_test4;
	private static String suite_test5;
	private static String suites;

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
		// this.filePath = filePath;

	}

	public void SetUpDataBase() throws Exception {

		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName(dbDriverClass);

			// Setup the connection with the DB
			connect = DriverManager.getConnection(dbUrl, dbUserName, dbPassWord);			
			
			Log.info("MySql connection is " + connect.isValid(3000));

		} catch (SQLException e) {
			Log.info(e.getSQLState());
		}

	}
	//TODO: insert scripts should run in the order to match foreign key insert (first the script that holds the foreign key reference) -- @AfterClass in TestBase
	// it also should be run with runSqlScript method

	public void generateMethodSummaryReport(String suite, String testname)
			throws Exception {

		if (testname == null) {
			return;
		}
		
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName(dbDriverClass);

			// Setup the connection with the DB
			connect = DriverManager.getConnection(dbUrl, dbUserName, dbPassWord);

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
		
			// PreparedStatements can use variables and are more efficient
			PreparedStatement getLastInsertId = connect.prepareStatement("select LAST_INSERT_ID() from feedback.HTML_reports");
			ResultSet rs = getLastInsertId.executeQuery();
			
			if (rs.next())
				{
			 
				long insertId = rs.getLong("last_insert_id()");
				long time = System.currentTimeMillis();
				java.sql.Timestamp timestamp = new java.sql.Timestamp(time);
				
			preparedStatement = connect.prepareStatement("insert into  feedback.SUITE_MethodSummaryReport values (default, ?, ?, ?, ? , ?, ?, ?)");

			preparedStatement.setString(1, suite);
			preparedStatement.setString(2, testname);
			preparedStatement.setString(3, testname);
			preparedStatement.setString(4, testname);
			preparedStatement.setTimestamp(5, timestamp);
			preparedStatement.setString(6, testname);
			preparedStatement.setLong(7, insertId);
			
				}
			
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.getLocalizedMessage();

		} finally {

			close();
		}

	}

	public void insertReport() throws Exception {

		Path testOutput = Paths.get("test-output/html");

		if (testOutput.toFile().exists()) {
			try {
				System.out.println("Real path: "
						+ testOutput.toRealPath(LinkOption.NOFOLLOW_LINKS));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("The directory does not exist");
		}

		//filePath = urlsFile;
		index = testOutput + File.separator + "index.html";
		output = testOutput + File.separator + "output.html";
		overview = testOutput + File.separator + "overview.html";
		reportng_css = testOutput + File.separator + "reportng.css";
		reportng_js = testOutput + File.separator + "reportng.js";
		sorttable = testOutput + File.separator + "sorttable.js";
		suite_groups = testOutput + File.separator + "suite1_groups.html";
		suite_test1 = testOutput + File.separator + "suite1_test1_results.html";
		suite_test2 = testOutput + File.separator + "suite1_test2_results.html";
		suite_test3 = testOutput + File.separator + "suite1_test3_results.html";
		suite_test4 = testOutput + File.separator + "suite1_test4_results.html";
		suite_test5 = testOutput + File.separator + "suite1_test5_results.html";
		suites = testOutput + File.separator + "suites.html";

		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName(dbDriverClass);

			// Setup the connection with the DB
			connect = DriverManager.getConnection(dbUrl, dbUserName, dbPassWord);

			String sql = "insert into feedback.HTML_reports  "
					+ "(id, index_, output, overview,	reportng_css, "
					+ "reportng_js, sorttable, suite_groups, suite_test1,suite_test2, "
					+ "suite_test3, suite_test4, suite_test5, suites) values (default, ?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement statement = connect.prepareStatement(sql);

			InputStream inputStream = new FileInputStream(new File(index));
			statement.setBlob(1, inputStream);
			InputStream inputStream2 = new FileInputStream(new File(output));
			statement.setBlob(2, inputStream2);
			InputStream inputStream3 = new FileInputStream(new File(overview));
			statement.setBlob(3, inputStream3);
			InputStream inputStream4 = new FileInputStream(new File(reportng_css));
			statement.setBlob(4, inputStream4);
			InputStream inputStream5 = new FileInputStream(new File(reportng_js));
			statement.setBlob(5, inputStream5);
			InputStream inputStream6 = new FileInputStream(new File(sorttable));
			statement.setBlob(6, inputStream6);
			InputStream inputStream7 = new FileInputStream(new File(suite_groups));
			statement.setBlob(7, inputStream7);
			InputStream inputStream8 = new FileInputStream(new File(suite_test1));
			statement.setBlob(8, inputStream8);
			InputStream inputStream9 = new FileInputStream(new File(suite_test2));
			statement.setBlob(9, inputStream9);
			InputStream inputStream10 = new FileInputStream(new File(suite_test3));
			statement.setBlob(10, inputStream10);
			InputStream inputStream11 = new FileInputStream(new File(suite_test4));
			statement.setBlob(11, inputStream11);
			InputStream inputStream12 = new FileInputStream(new File(suite_test5));
			statement.setBlob(12, inputStream12);
			InputStream inputStream13 = new FileInputStream(new File(suites));
			statement.setBlob(13, inputStream13);
			
			/*
			if (filePath != null) {
				InputStream inputStream14 = new FileInputStream(new File(filePath));
				statement.setBlob(14, inputStream14);
			}
			

			else {
				TestBase.createFile(urlsFile);
				Log.info("Empty urls.txt file was created. Test urls were retreived from property file.");
				filePath = urlsFile;
				InputStream inputStream15 = new FileInputStream(
						new File(filePath));
				statement.setBlob(15, inputStream15);
			}
			*/

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
			resultSet = statement.executeQuery("select * from feedback.comments");
			writeResultSet(resultSet);

			// PreparedStatements can use variables and are more efficient
			preparedStatement = connect.prepareStatement("insert into  feedback.comments values (default, ?, ?, ?, ? , ?, ?)");
			// "myuser, webpage, datum, summery, COMMENTS from feedback.comments");
			// Parameters start with 1
			preparedStatement.setString(1, "Test");
			preparedStatement.setString(2, "TestEmail");
			preparedStatement.setString(3, "TestWebpage");
			preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
			preparedStatement.setString(5, "TestSummary");
			preparedStatement.setString(6, "TestComment");
			preparedStatement.executeUpdate();

			preparedStatement = connect.prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments");
			resultSet = preparedStatement.executeQuery();
			writeResultSet(resultSet);

			// Remove again the insert comment
			preparedStatement = connect.prepareStatement("delete from feedback.comments where myuser= ? ; ");
			preparedStatement.setString(1, "Test");
			preparedStatement.executeUpdate();

			resultSet = statement.executeQuery("select * from feedback.comments");
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

	public void runSqlScript(String sqlPath) throws ClassNotFoundException, SQLException, FileNotFoundException {

		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName(dbDriverClass);

			// Setup the connection with the DB
			connect = DriverManager.getConnection(dbUrl, dbUserName, dbPassWord);			
			connect.isValid(3000);
			
			Log.info("MySql connection is " + connect.isValid(3000));
				
		// Initialize object for ScripRunner
		ScriptRunner sr = new ScriptRunner(connect);

		// Give the input file to Reader
		Reader reader = new BufferedReader(new FileReader(sqlPath));

		// Exctute script
		sr.runScript(reader);
		Log.info("Script run: "+ sqlPath);

	} catch (SQLException ex) {
		ex.getLocalizedMessage();
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
