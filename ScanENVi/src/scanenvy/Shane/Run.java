/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanenvy.Shane;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.ArrayList;
import scanenvy.Product;

public class Run {
ArrayList<Product> list = new ArrayList<Product>();
ArrayList<Product> search = new ArrayList<Product>();




public void run(String upc){
	boolean isUPC = true;
	
	
	//Check if its an accurate barcode
	//isUPC = check(upc, isUPC);//checks if it contains integers and if its 12 digits
	
	String manufacturer; 
	
	if(isUPC){//if it contains integers		

	
		//retrieve manufacturer
		manufacturer = getManufacturer(upc);
	
	
		Product product = new Product(upc, manufacturer);
	
	
		list.add(product);
	
	
	
	}
	else{
	
		System.out.println("Error: Not UPC");
	}
}


public boolean check(String tempUpc, boolean isUPC){
	//check if integer
			for (int i = 0; i < tempUpc.length(); i++) {
                            if (Character.isDigit(tempUpc.charAt(i)) == false) {
                                return false;
                            }
                        }
			if(tempUpc.length()== 13|| tempUpc.length()== 12 || tempUpc.length()== 8) {
	        		return true;
                        }
                        else {
                            return false;
                        }
}

private String getManufacturer(String tempUpc){	
	//returns different manufacturer depending on upc length
	String tempManufacturer = "null";
	if(tempUpc.length()== 13){
	  tempManufacturer = tempUpc.substring(1, 7);// get the substring(manufacturer 
	}
	else if(tempUpc.length()== 12){
		tempManufacturer = tempUpc.substring(1, 6);// get the substring(manufacturer 
		
		}
	else if(tempUpc.length()== 8){
		tempManufacturer = tempUpc.substring(1, 7);// get the substring(manufacturer 
		
		}
	return tempManufacturer;
	
	
}

public void loadData(){
        
        //#############//
        //Test Products//
        //#############//
    
	String productName = "Coca-Cola";
	String manufacturer = "The Coca-Cola Company";
	int type = 8;
	String upc = "0049000006346";
	Product product = new Product(upc, productName, type, manufacturer);
	search.add(product);

	
	//object2
	productName = "Dr. Pepper";
	manufacturer = "Dr. Pepper / Seven-Up Inc";
	type= 8;
	upc = "0078000003154";
	Product product2 = new Product(upc, productName, type, manufacturer);
	search.add(product2);

	
	//object3
	productName = "Kirkland Signature Purified Drinking Water";
	manufacturer = "Kirkland";
	type= 1;
	upc = "0096619756803";
	Product product3 = new Product(upc, productName, type, manufacturer);
	search.add(product3);

	//Object4
	productName = "7up Diversion Safe";
	manufacturer = "Dr. Pepper / Seven-Up Inc.";
	type= 8;
	upc = "0078000000382";
	Product product4 = new Product(upc, productName, type, manufacturer);
	search.add(product4);
}
    public Product lookUp(String upc){
            Product productTemp = new Product();
            //check if object scanned has the same UPC as the object searched for
            System.out.println(search.get(0).getUpc());
            for(int i=0 ; i<search.size(); i++){
                    //if so set the name, manufacturer and type of that object search for
                    //to that product
                Product product2 = search.get(i);
                    String upc2 = search.get(i).getUpc();
                    if(upc.equals(product2.getUpc())){
                        System.out.println("Inside");
                        productTemp = search.get(i);
                    }

            }
            return productTemp;
    }
}