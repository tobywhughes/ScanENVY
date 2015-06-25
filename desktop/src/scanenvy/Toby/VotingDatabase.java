/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanenvy.Toby;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class VotingDatabase {
	
	private ObjectOutputStream output;
	private File vdbfile;
	private vdbinterface vdb = new vdbinterface();
	public ArrayList<Voting> objectList;
	
	public VotingDatabase(){
		try{
		objectList = vdb.getParsed();
		}catch(Exception e){
			e.printStackTrace();
		}
		File vdbfile = new File("voting.envy");
		try{
			output = new ObjectOutputStream(new FileOutputStream(vdbfile));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void pushToDatabase(Voting votes) throws IOException{
		objectList.add(votes);
		System.out.println(votes.getUPC());
		System.out.println(objectList);
		for (Voting voting : objectList){
			System.out.println(voting.getUPC());
				output.writeObject(voting);
		}
		output.close();
	}
	
}