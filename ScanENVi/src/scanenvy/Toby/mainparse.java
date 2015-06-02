/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
package scanenvy.Toby;

import java.io.File;
import java.io.IOException;

public class mainparse {
	//Unit Test
	public static void main(String[] args) throws IOException{
		System.out.println("TEST");
		VotingDatabase vd = new VotingDatabase();
		Voting item1 = new Voting("1234");
		vd.pushToDatabase(item1);
		vdbinterface vdb = new vdbinterface();
		Voting item2 = vdb.findObject("1234");
		System.out.println("------------------");
		Database db = new Database();
		Product item3 = new Product("12345");
		db.pushToDatabase(item3);
		dbinterface dbi = new dbinterface();
		Product item4 = dbi.findObject("12345");
		System.out.println(item4.getUpc());
	}
	
	private String productID;
	private dbinterface pdb = new dbinterface();
	private vdbinterface vdb = new vdbinterface();
	private Product product;
	private Voting voting;
	private DisplayProduct displayProduct;
	
	public mainparse(){}
	
	public void setProductID(String ID){
		productID = ID; 
		System.out.println(productID);
	}
	public void getProductClass(){
		product = pdb.findObject(productID);
	}
	
	public void getCommentClass(){
		voting = vdb.findObject(productID);
	}
	
	public void format(){
		displayProduct = new DisplayProduct(product, voting);
	}
	
	public DisplayProduct getDisplayProduct(){
		return displayProduct;
	}
}
*/