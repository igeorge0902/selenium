CREATE DATABASE IF NOT EXISTS feedback;
 
USE feedback;
 
CREATE TABLE IF NOT EXISTS HTML_reports (
  testrun_id int(11) UNSIGNED UNIQUE not null auto_increment,
	index_ mediumblob,
	output mediumblob,
	overview mediumblob,
	reportng_css mediumblob,
	reportng_js mediumblob,
	sorttable mediumblob,
	suite_groups mediumblob,
	suite_test1 mediumblob,
	suite_test2 mediumblob,
	suite_test3 mediumblob,
	suite_test4 mediumblob,
	suite_test5 mediumblob,
	suites mediumblob,
  PRIMARY KEY (testrun_id)); 
 
CREATE TABLE IF NOT EXISTS SUITE_MethodSummaryReport (
    id int(11) not null auto_increment,
    SUITE_NAME VARCHAR(400),
    TEST_NAME VARCHAR(400), 
    ITEST_CONTEXT VARCHAR(400), 
    TEST_RESULTS VARCHAR(40),
    DATUM DATE NOT NULL, 
    COMMENTS VARCHAR(400),
	testrun_id int(11) UNSIGNED UNIQUE not null,
    PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY(testrun_id)
    REFERENCES HTML_reports(testrun_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT);
