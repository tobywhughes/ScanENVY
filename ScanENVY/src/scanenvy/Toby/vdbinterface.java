/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanenvy.Toby;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class vdbinterface {
	
	private File file = new File("voting.envy");
	private FileInputStream istream;
	private ObjectInputStream ostream;
	private ArrayList<Voting> objectList = new ArrayList<Voting>();
	
	
	public vdbinterface(){
		try {
			istream = new FileInputStream(file);
			ostream = new ObjectInputStream(istream);
			parseFile();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void parseFile(){
		Voting object;
		if(ostream != null){
		try{
			while((object = (Voting)ostream.readObject()) != null){
				objectList.add(object);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		}
		

	}
	
	public ArrayList<Voting> getParsed(){
		return objectList;
	}
	
	public Voting findObject(String productID){
		System.out.println(objectList);
		System.out.println((objectList.get(1).getUPC()));
		Voting object = new Voting("5555");
		for(int i = 0; i < objectList.size(); i++){
			System.out.println(objectList.get(i).getUPC() + "++++" +productID);
			if(productID.equals(objectList.get(i).getUPC())){
				System.out.println("INSIDE");
				object = objectList.get(i);
			}
		}
		return object;
	}

}