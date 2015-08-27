package utils;

import utils.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TweetMapGenerator {
	
	private Map<Integer, String[]> tweets; 
	
	public TweetMapGenerator(){ 
		this.tweets = new HashMap<Integer, String[]>();
	}
	
	public void MapActualizer(String SqlQuery){
		System.out.println("Entrem");
		
		try {
			DAO database = new DAO();
			
			ResultSet tweetQ = database.executeSQL(SqlQuery);

			while(tweetQ.next()){
				String user = tweetQ.getString(1);
				String firstname = tweetQ.getString(2);
				String surname = tweetQ.getString(3);
				String tweetid = tweetQ.getString(4);
				String text = tweetQ.getString(6);
				String likes = tweetQ.getString(7);
				String dislikes = tweetQ.getString(8);
				String datet = tweetQ.getString(9);
				String fathertweet = tweetQ.getString(10);
								
				String[] value = {user, firstname, surname, text, likes, dislikes, datet, fathertweet};
				
				this.tweets.put(Integer.parseInt(tweetid), value);
			}
			
			database.disconnectBD();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Map<Integer, String[]> getMap(){
		return this.tweets;
	}
	
}
