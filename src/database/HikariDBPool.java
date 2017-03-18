/* The HikariCP library has been used for connection pooling.
 * https://github.com/brettwooldridge/HikariCP
 * The code to setup the connection pool has been referenced from the below website.
 * https://examples.javacodegeeks.com/enterprise-java/hikaricp/hikaricp-connection-pooling-example/
 */
package database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariDBPool {
	private static Object lock = new Object();
	private static HikariDBPool hikariPool;
	private static DataSource datasource;

	private HikariDBPool(){
		setupDataSource();
	}

	/*
	 * The datasource setup code has been referenced from the web-site mentioned in the comments above
	 * This code sets up the Hikari connection pool
	 */
	private static void setupDataSource() {
		if(datasource == null){
			HikariConfig config = new HikariConfig();

			config.setDriverClassName("com.mysql.jdbc.Driver");
			config.setJdbcUrl("jdbc:mysql://"+"localhost/solarcity?useSSL=false&useUnicode=yes&characterEncoding=UTF-8");
			config.setUsername("root");
			config.setPassword("mysql123");
			config.setConnectionTimeout(30000);
			config.setIdleTimeout(600000);
			config.setMaxLifetime(1800000);
			config.setMaximumPoolSize(8);
			config.addDataSourceProperty("cachePrepStmts", "true");
			config.addDataSourceProperty("prepStmtCacheSize", "250");
			config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

			datasource = new HikariDataSource(config);
		}
	}

	/*
	 * This code ensures that there is a new HikariPool created if there isn't one already.
	 * This ensures that HikariDBPool is a singleton
	 */
	public static void checkInstance() {
		synchronized(lock){
			if (datasource == null || hikariPool == null) {
				System.out.println("Creating new instance of Hikari pool");
				hikariPool = new HikariDBPool();
			}
		}
	}

	/*
	 * This method returns a connection from the pool to the requesting method
	 */
	public static Connection getConnection(){
		try {
			checkInstance();
			return HikariDBPool.datasource.getConnection();
		} catch(MySQLSyntaxErrorException e){
			shutdownDataSource();
		}catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * This method puts an unused connection back into the pool
	 */
	public static void returnConnection(Connection conn){
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * This method shuts down the connection pool. This method is never called unless there is an
	 * exception when returning a DB connection.
	 */
	public static void shutdownDataSource() {
		synchronized(lock){
			hikariPool = null;
			datasource = null;
		}	
	}
}
