CREATE DATABASE IF NOT EXISTS feedback;
 
USE feedback;
 
CREATE TABLE IF NOT EXISTS HTML_Reports 
(
    id int(11) UNSIGNED not null auto_increment,
	Report_files mediumblob,
	testrun_id int(11) UNSIGNED not null,
	PRIMARY KEY (id))
	ENGINE = MyISAM DEFAULT COLLATE utf8_bin; 


CREATE TABLE IF NOT EXISTS testruns 
(
    id int(11) UNSIGNED not null auto_increment,
	TIME_ timestamp,    
	PRIMARY KEY (id))
	ENGINE = InnoDb DEFAULT COLLATE utf8_bin; 
 
CREATE TABLE IF NOT EXISTS SUITE_MethodSummaryReport (
    id int(11) not null auto_increment,
    SUITE_NAME VARCHAR(255),
    TEST_NAME VARCHAR(255), 
    TEST_PASSED int(11),
    TEST_FAILS int(11),
    TEST_SKIPPED int(11),
    CONFIG_FAILS int(11), 
	TIME_ timestamp,    
	COMMENTS VARCHAR(255),
	testrun_id int(11) UNSIGNED not null,
    PRIMARY KEY (id), 
	CONSTRAINT FOREIGN KEY `fk_cat` (testrun_id)
    REFERENCES testruns(id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT)
	ENGINE = InnoDb DEFAULT COLLATE utf8_bin;
	
CREATE TABLE IF NOT EXISTS METHOD_Results (
    id int(11) not null auto_increment,
    METHOD_NAME VARCHAR(255),
    TEST_RESULT VARCHAR(255),  
	DESCRIPTION VARCHAR(255),
	TIME_ timestamp,    
	testrun_id int(11) UNSIGNED not null,
    PRIMARY KEY (id), 
	CONSTRAINT FOREIGN KEY `fk_cat` (testrun_id)
    REFERENCES testruns(id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT)
	ENGINE = InnoDb DEFAULT COLLATE utf8_bin;