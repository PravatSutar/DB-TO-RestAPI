package com.company.fs.analytics.restapi.controller;

import java.io.FileReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.company.fs.analytics.restapi.beans.DataAcknowledgement;

/**
 * The class establishes connection with database and get the data based on the
 * time-stamp received as parameters. 
 *
 * It reads the database properties from the properties file - dbconnection.properties
 */
public class DatabaseConnector {

	String result = null;
     
	private static final Logger log = LoggerFactory.getLogger(DatabaseConnector.class);
 
	public List<DataAcknowledgement> getData(String tran_ts) {
		ArrayList<DataAcknowledgement> dataAcks = new ArrayList<DataAcknowledgement>();
		
		try {			
			Properties props = new Properties();
			String dbPropertyFile = "C:\\Users\\pravat.sutar\\FSCodebase\\SpringBootRestUtility\\src\\main\\resources\\dbconnection.properties";
			FileReader fReader = new FileReader(dbPropertyFile);

			props.load(fReader);

			String dbDriverClass = props.getProperty("db.driver.class");
			String dbConnUrl = props.getProperty("db.conn.url");
			String dbUserName = props.getProperty("db.username");
			String dbPassword = props.getProperty("db.password");

			if (!"".equals(dbDriverClass) && !"".equals(dbConnUrl)) {
				Class.forName(dbDriverClass);

				Connection con = DriverManager.getConnection(dbConnUrl, dbUserName, dbPassword);

				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from stg.transaction where tran_ts > '" + tran_ts + "' order by tran_ts asc");
				
				log.info("DatabaseConnector: The query string: " + rs);
				while (rs.next()) {
					result = rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3);
					log.info("DatabaseConnector: Data Received from database " + result);
					DataAcknowledgement dataAck = new DataAcknowledgement(rs.getString(1), rs.getString(2),	rs.getString(3));
					dataAcks.add(dataAck);
				}
				con.close();
			}
		}
		catch (Exception e) {
			log.error("Error " + e);
		}		
		return dataAcks;
	}
    /*
     * The main method is used for unit testing. It passes a time-stamp and
     * get data from the database which are older than that specified time-stamp.
     */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DatabaseConnector databaseConnector = new DatabaseConnector();
		System.out.println("Data Retrieved: " + databaseConnector.getData("2019-07-03 03:00:01.103012"));
	}
}
