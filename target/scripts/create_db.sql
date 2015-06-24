CREATE DATABASE IF NOT EXISTS feedback;
 
USE feedback;
 
CREATE TABLE IF NOT EXISTS HTML_reports 
(
    id int(11) UNSIGNED not null auto_increment,
	index_ blob,
	output blob,
	overview blob,
	reportng_css blob,
	reportng_js blob,
	sorttable blob,
	suite_groups blob,
	suite_test1 blob,
	suite_test2 blob,
	suite_test3 blob,
	suite_test4 blob,
	suite_test5 blob,
	suites blob,
	PRIMARY KEY (id))
	ENGINE = MyISAM DEFAULT COLLATE utf8_bin; 
 
CREATE TABLE IF NOT EXISTS SUITE_MethodSummaryReport (
    id int(11) not null auto_increment,
    SUITE_NAME VARCHAR(255),
    TEST_NAME VARCHAR(255), 
    ITEST_CONTEXT VARCHAR(255), 
    TEST_RESULTS VARCHAR(40),
	TIME_ timestamp,    
	COMMENTS VARCHAR(255),
	testrun_id int(11) UNSIGNED not null,
    PRIMARY KEY (id), 
	CONSTRAINT FOREIGN KEY `fk_cat` (testrun_id)
    REFERENCES HTML_reports(id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT)
	ENGINE = MyISAM DEFAULT COLLATE utf8_bin;
	
	