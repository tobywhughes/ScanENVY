/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanenvy.Toby;

//Alonso's Class!!!!
public class Score implements java.io.Serializable{
	private int score;
	private int upvotes;
	private int downvotes;
	
	public Score(int score, int upvotes, int downvotes){
		this.score = score;
		this.upvotes = upvotes;
		this.downvotes = downvotes;
	}
	
	public void addUpvote(){
		upvotes += 1;
	}
	
	public void addDownvote(){
		downvotes -= 1;
	}
	
	private void totalScore(){
		score = upvotes + downvotes;
	}
	
	public int getScore(){
		return score;
	}
}
