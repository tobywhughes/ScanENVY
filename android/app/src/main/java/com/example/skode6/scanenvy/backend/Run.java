
package com.example.skode6.scanenvy.backend;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class Run {
	ArrayList<Product> list = new ArrayList<Product>();
	ArrayList<Product> search = new ArrayList<Product>();

	public void run(String upc) {
		boolean isUPC = true;
		//Check if its an accurate barcode
		//isUPC = check(upc, isUPC);//checks if it contains integers and if its 12 digits

		String manufacturer;

		if (isUPC) {//if it contains integers
			//retrieve manufacturer
			manufacturer = getManufacturer(upc);
			Product product = new Product(upc, manufacturer);
			list.add(product);
		} else {
			System.out.println("Error: Not UPC");
		}
	}

	public ArrayList<Product> getProductList() {
		loadData();
		return list;
	}

	public boolean check(String tempUpc, boolean isUPC) {
		//check if integer
		for (int i = 0; i < tempUpc.length(); i++) {
			if (Character.isDigit(tempUpc.charAt(i)) == false) {
				return false;
			}
		}
		if (tempUpc.length() == 13 || tempUpc.length() == 12 || tempUpc.length() == 8) {
			return true;
		} else {
			return false;
		}
	}

	private String getManufacturer(String tempUpc) {
		//returns different manufacturer depending on upc length
		String tempManufacturer = "null";
		if (tempUpc.length() == 13) {
			tempManufacturer = tempUpc.substring(1, 7);// get the substring(manufacturer
		} else if (tempUpc.length() == 12) {
			tempManufacturer = tempUpc.substring(1, 6);// get the substring(manufacturer

		} else if (tempUpc.length() == 8) {
			tempManufacturer = tempUpc.substring(1, 7);// get the substring(manufacturer

		}
		return tempManufacturer;
	}

	public void loadData() {
		String productName = "Coca-Cola";
		String manufacturer = "The Coca-Cola Company";
		int type = 8;
		String upc = "0049000006346";
		Product product = new Product(upc, productName, type, manufacturer);
		search.add(product);


		//object2
		productName = "Dr. Pepper";
		manufacturer = "Dr. Pepper / Seven-Up Inc";
		type = 8;
		upc = "0078000003154";
		Product product2 = new Product(upc, productName, type, manufacturer);
		search.add(product2);


		//object3
		productName = "Kirkland Signature Purified Drinking Water";
		manufacturer = "Kirkland";
		type = 1;
		upc = "0096619756803";
		Product product3 = new Product(upc, productName, type, manufacturer);
		search.add(product3);

		//Object4
		productName = "7up Diversion Safe";
		manufacturer = "Dr. Pepper / Seven-Up Inc.";
		type = 8;
		upc = "0078000000382";
		Product product4 = new Product(upc, productName, type, manufacturer);
		search.add(product4);
	}

	public Product lookUp(String upc) throws IOException {
		Product product = new Product(upc);
        ScrapeObject scrapeName = null;
		//TODO implement database system

		//Currently ALWAYS returns false since database doesn't exist
		boolean existsInDatabase = checkDatabase(upc);

		//If the object isn't in the database, it is created
		//Currently just a basic system as webscraper is unfinished
		if (existsInDatabase != true) {
			//WebScrape scraper = new WebScrape();
            AsyncTask<String, Void, ScrapeObject> rp = new RetrieveProduct().execute(upc);
            try {
                scrapeName = (ScrapeObject) rp.get();
            }
            catch (CancellationException ce){}
            catch (ExecutionException ee) {}
            catch (InterruptedException ie) {}
			product.setName(scrapeName.getName());
		}


		return product;
	}




/*
* Temp Methods
*/

/*
* Method to stand in for however we decide to check the database.
* Making this now so I can implement the scraper
* The actual function is COMPLETELY useless and only
* being used to give a visual idea of eventual structure of code
*/

	private boolean checkDatabase(String upc) {
		//Returns false since not implemented yet.
		return false;
	}
}