/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanenvy.Toby;

import scanenvy.Product;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class dbinterface {
	
	private File file = new File("products.envy");
	private FileInputStream istream;
	private ObjectInputStream ostream;
	private ArrayList<Product> objectList = new ArrayList<Product>();
	
	
	public dbinterface(){
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
            Product object;
            if(ostream != null){
		try{
			while((object = (Product)ostream.readObject()) != null && ostream.available() > 0){
				objectList.add(object);
			}
		}
		catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
            }
	}
	
	public ArrayList<Product> getParsed(){
		return objectList;//Kir look out
	}
	
	public Product findObject(String productID){//Kir look out
		//System.out.println(objectList);
		Product object;
                object = new Product("55555");
		for(int i = 0; i < objectList.size(); i++){
			if(productID.equals(objectList.get(i).getUpc())){
				System.out.println("INSIDE");
				object = objectList.get(i);
			}
		}
		return object;
	}

}
