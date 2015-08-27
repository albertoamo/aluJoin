package utils;

import java.sql.*;
import java.sql.ResultSet;

//Esta clase se usa para interaccionar mediante SQL con nuestra BBDD

public class DAO 
{
		private Connection connection;
		private Statement statement;

		public DAO() throws Exception 
		{
			//Información del nombre de usuario y pass para acceder
			String user = "mysql";
			String password="prac";
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost/", user, password);
			
			statement = connection.createStatement();
			
			createDatabase();
			
			connection=DriverManager.getConnection("jdbc:mysql://localhost/Practica4?" //nombre de la BBDD = databaseprac2
					+ "user="+user+"&password="+password);
				
			statement=connection.createStatement();
		}
		
		//execute queries
		public ResultSet executeSQL(String query) throws SQLException
		{			
			return statement.executeQuery(query);
		}
		
		//execute inserts
		public int executeUpdate(String query) throws SQLException
		{			
			return statement.executeUpdate(query);
		}
		
		//Desconexión
		public void disconnectBD() throws SQLException
		{
			statement.close();
			connection.close();
		}
		
		public void createDatabase() throws Exception {
			
			String SQL = "create database if not exists Practica4;";
			
			statement.executeUpdate(SQL);
			
			SQL = "use Practica4;";
			
			statement.executeUpdate(SQL);
			
			SQL = "CREATE TABLE IF NOT EXISTS `User`"+
					"("+
							"usernameid		int(4) NOT NULL AUTO_INCREMENT,"+
							"username		varchar(16),"+
							"firstname		varchar(16),"+
							"firstsurname	varchar(16),"+
							"gender			varchar(8),"+
							"mail 			varchar(24),"+
							"birthdate		varchar(32),"+
							"pass			varchar(20),"+
							"description 	varchar(255),"+
							"admin 			bool, " +		
							
							"PRIMARY KEY (usernameid)"+
						");";
			statement.executeUpdate(SQL);
			
			SQL = "CREATE TABLE IF NOT EXISTS Follower"+
						"("+
							"usernameid		int(4),"+
						    "followid		int(4),"+
							
						    "FOREIGN KEY (usernameid) REFERENCES `User`(usernameid),"+
							"FOREIGN KEY (followid) REFERENCES `User` (usernameid) "+
						    "ON DELETE CASCADE "+
						");";
			
			statement.executeUpdate(SQL);
			
						
			SQL = "CREATE TABLE IF NOT EXISTS Tweet"+
						"("+
							"tweetid 		int(4) NOT NULL AUTO_INCREMENT,"+
						    "usernameid		int(4) NOT NULL,"+
							"textt			varchar(140) NOT NULL,"+
						    "likes			int(4),"+
						    "dislikes		int(4),"+
							"datet			varchar(32),"+
						    "fathertweet		int(4),"+

						    "PRIMARY KEY (tweetid),"+
							"FOREIGN KEY (usernameid) REFERENCES `User`(usernameid) "+
						    "ON DELETE CASCADE" +

						");";
			
			statement.executeUpdate(SQL);

			SQL = "ALTER TABLE Tweet "+
							"ADD FOREIGN KEY (fathertweet) REFERENCES Tweet(tweetid);";
			statement.executeUpdate(SQL);
			
			SQL = "CREATE TABLE IF NOT EXISTS Retweet"+
						"("+
							"usernameid		int(4) NOT NULL,"+
						    "tweetid			int(4) NOT NULL,"+
						    
						    "FOREIGN KEY (usernameid) REFERENCES `User`(usernameid),"+
							"FOREIGN KEY (tweetid) REFERENCES Tweet(tweetid) "+
							"ON DELETE CASCADE" +
						");";
			
			statement.executeUpdate(SQL);
			
			SQL = "CREATE TABLE IF NOT EXISTS Votes"+
					"("+
						"usernameid		int(4) NOT NULL,"+
					    "tweetid		int(4) NOT NULL,"+
					    
					    "FOREIGN KEY (usernameid) REFERENCES `User`(usernameid),"+
						"FOREIGN KEY (tweetid) REFERENCES Tweet(tweetid) "+
						"ON DELETE CASCADE" + 
					");";
		
			statement.executeUpdate(SQL);
			
		}
		
}