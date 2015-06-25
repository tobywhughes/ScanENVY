/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanenvy.Toby;

import java.util.HashMap;
import java.util.Map;

public class Voting implements java.io.Serializable{
	private String upc;
	private Map<String, Score> rType = new HashMap<String, Score>();
	
    public Voting(String ID, Map<String, Score> type){
    	upc = ID;
    	rType = type;
    }
    
    public Voting(String ID){
    	upc = ID;
    	Score score = new Score(0,0,0);
    	rType.put("1", score); 
    }
	
    public String getTop(){
    	int max = 0;
    	String returnString = "";
    	for(String key : rType.keySet()){
    		if(rType.get(key).getScore() > max){
    			max = rType.get(key).getScore();
    			returnString = key;
    		}
    	}
    	return returnString;
    }
    
    public void setUPC(String ID){
    	upc = ID;
    }
    
    public String getUPC(){
    	return upc;
    }
    
    public void addOption(String type){
    	Score score = new Score(0, 0, 0);
    	rType.put(type, score);
    }
    
    public Map<String, Score> getMap(){
    	return rType;
    }
    
    public int getScore(String key){
    	return rType.get(key).getScore();
    }
    
    public void upvote(String key){
    	rType.get(key).addUpvote();
    }
    public void downvote(String key){
    	rType.get(key).addDownvote();
    }
}
