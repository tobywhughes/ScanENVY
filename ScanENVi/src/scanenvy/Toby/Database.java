/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanenvy.Toby;


import scanenvy.Product;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Database
{
	private ObjectOutputStream output;
	private dbinterface pdb = new dbinterface();
	private ArrayList<Product> objectList;
	private File pdbfile;
	
    public Database()//Kir look out 
    {
        
    	try{
    		objectList = pdb.getParsed();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
		File pdbfile = new File("products.envy");
		try{
			output = new ObjectOutputStream(new FileOutputStream(pdbfile));
		}
		catch(Exception e){
			e.printStackTrace();
		}
    }

	public void pushToDatabase(Product product) throws IOException{
		objectList.add(product);
		for (Product products : objectList){
			output.writeObject(products);
		}
	}
        
        public ArrayList getObjectList()//Kir look out
        {
            return objectList;
        }
        
        public Product findProduct(String UPC)//Changed by Kir
        {
            return pdb.findObject(UPC);
        }
        
}
