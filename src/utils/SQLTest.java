package utils;

import utils.MySQLAccess;

public class SQLTest {
  public static void main(String[] args) throws Exception {
    MySQLAccess dao = new MySQLAccess();
    dao.readDataBase();
  }

} 


