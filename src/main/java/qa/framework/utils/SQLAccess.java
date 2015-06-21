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
	private static long lastInsertId;
	
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
		
			 
				long time = System.currentTimeMillis();
				java.sql.Timestamp timestamp = new java.sql.Timestamp(time);
				
			preparedStatement = connect.prepareStatement("insert into  feedback.SUITE_MethodSummaryReport values (default, ?, ?, ?, ? , ?, ?, ?)");

			preparedStatement.setString(1, suite);
			preparedStatement.setString(2, testname);
			preparedStatement.setString(3, testname);
			preparedStatement.setString(4, testname);
			preparedStatement.setTimestamp(5, timestamp);
			preparedStatement.setString(6, testname);
			preparedStatement.setLong(7, lastInsertId);
			
				
			
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			Log.info(e.getLocalizedMessage());

		} finally {
			Log.info("TestRuns was inserted into the db.");

			close();
		}

	}

	public boolean insertReport() throws Exception {
				
		Path testOutput = Paths.get("test-output/html");

		if (testOutput.toFile().exists()) {
			try {
				System.out.println("Real path: "
						+ testOutput.toRealPath(LinkOption.NOFOLLOW_LINKS));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Log.info(testOutput.toFile().toString() + " directory does not exist");
		}

		//filePath = urlsFile;
		index = testOutput.toFile().toString() + File.separator + "index.html";
		output = testOutput.toFile().toString() + File.separator + "output.html";
		overview = testOutput.toFile().toString() + File.separator + "overview.html";
		reportng_css = testOutput.toFile().toString() + File.separator + "reportng.css";
		reportng_js = testOutput.toFile().toString() + File.separator + "reportng.js";
		sorttable = testOutput.toFile().toString() + File.separator + "sorttable.js";
		suite_groups = testOutput.toFile().toString() + File.separator + "suite1_groups.html";
		suite_test1 = testOutput.toFile().toString() + File.separator + "suite1_test1_results.html";
		suite_test2 = testOutput.toFile().toString() + File.separator + "suite1_test2_results.html";
		suite_test3 = testOutput.toFile().toString() + File.separator + "suite1_test3_results.html";
		suite_test4 = testOutput.toFile().toString() + File.separator + "suite1_test4_results.html";
		suite_test5 = testOutput.toFile().toString() + File.separator + "suite1_test5_results.html";
		suites = testOutput.toFile().toString() + File.separator + "suites.html";

		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName(dbDriverClass);

			// Setup the connection with the DB
			connect = DriverManager.getConnection(dbUrl, dbUserName, dbPassWord);

			String sql = "insert into feedback.HTML_reports  "
					+ "(id, index_, output, overview,	reportng_css, "
					+ "reportng_js, sorttable, suite_groups, suite_test1,suite_test2, "
					+ "suite_test3, suite_test4, suite_test5, suites) values (default, ?,?,?,?,?,?,?,?,?,?,?,?,?)";
						
			PreparedStatement statement = connect.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

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
			
			PreparedStatement getLastInsertId = connect.prepareStatement("select LAST_INSERT_ID() from feedback.HTML_reports");
			ResultSet rs = getLastInsertId.executeQuery();
			while (rs.next()) {

				lastInsertId = rs.getLong("last_insert_id()");
			}

			if (row > 0) {
				Log.info("Files were inserted into the db.");
			}

			close();

		} catch (SQLException ex) {

			ex.printStackTrace();
			Log.info(ex.getSQLState());

		} catch (IOException ex) {
			ex.printStackTrace();
			Log.info(ex.getCause());

		}
		return true;
	}

	  public void createProcedureGetTestRun() throws SQLException {
		    String createProcedure = null;

		    String queryDrop = "DROP PROCEDURE IF EXISTS GET_TEST_RUN";

		    createProcedure =
		        "create procedure GET_TEST_RUN(IN id int(11)) " +
		          "begin " +
		            "select * " +
		              "from feedback.SUITE_MethodSummaryReport " +
		              "where feedback.SUITE_MethodSummaryReport.id = id; " +
		          "end";
		    Statement stmt = null;
		    Statement stmtDrop = null;

		    try {
		      System.out.println("Calling DROP PROCEDURE");
		      stmtDrop = connect.createStatement();
		      stmtDrop.execute(queryDrop);
		    } catch (SQLException e) {
		      SQLAccess.printSQLException(e);
		    } finally {
		      if (stmtDrop != null) { stmtDrop.close(); }
		    }


		    try {
		      stmt = connect.createStatement();
		      stmt.executeUpdate(createProcedure);
		    } catch (SQLException e) {
		    	SQLAccess.printSQLException(e);
		    } finally {
		      if (stmt != null) { stmt.close(); }
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
	
	  public static void printSQLException(SQLException ex) {
		    for (Throwable e : ex) {
		      if (e instanceof SQLException) {
		        if (ignoreSQLException(((SQLException)e).getSQLState()) == false) {
		          e.printStackTrace(System.err);
		          System.err.println("SQLState: " + ((SQLException)e).getSQLState());
		          System.err.println("Error Code: " + ((SQLException)e).getErrorCode());
		          System.err.println("Message: " + e.getMessage());
		          Throwable t = ex.getCause();
		          while (t != null) {
		            System.out.println("Cause: " + t);
		            t = t.getCause();
		          }
		        }
		      }
		    }
		  }
	  
	  public static boolean ignoreSQLException(String sqlState) {
		    if (sqlState == null) {
		      System.out.println("The SQL state is not defined!");
		      return false;
		    }
		    // X0Y32: Jar file already exists in schema
		    if (sqlState.equalsIgnoreCase("X0Y32"))
		      return true;
		    // 42Y55: Table already exists in schema
		    if (sqlState.equalsIgnoreCase("42Y55"))
		      return true;
		    return false;
		  }

}
