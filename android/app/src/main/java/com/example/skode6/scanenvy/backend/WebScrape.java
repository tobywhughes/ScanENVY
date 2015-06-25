package com.example.skode6.scanenvy.backend;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 *
 * @author Tobias Hughes
 */
public class WebScrape {
    
    public ScrapeObject scrape(String address) throws IOException{
        Document doc = getSite(address);
        //Grabs content from website
        if(doc == null){
            return new ScrapeObject(address, "Connection Error", false);
        }
        //Makes search smaller and checks if UPC exists
        Elements tables = getTables(doc);
        if(tables == null){
            return new ScrapeObject(address, "UPC Does Not Exist", false);
        }
        //Gets item name
        String itemName = getItemName(tables);
        if(itemName == null){
            return new ScrapeObject(address, "Name Not Found", false);
        }
        
        return new ScrapeObject(address, itemName, true);
    }
    
    private Document getSite(String address) throws IOException{
        String fullAddress = "http://www.upcdatabase.com/item/" + address;
        try {
            Document doc = Jsoup.connect(fullAddress).get();
            return doc;
        }
        catch(IOException e){
            return null;
        }
    }
    
    private String getItemName(Elements parsed){
        for(Element tables : parsed){
            String text = tables.text();
            if (text.contains("Description")){
                Elements lines = tables.getElementsByTag("td");
                return lines.last().text();
            }
        }
        return null;
    }
    
    private Elements getTables(Document doc){
        Element parse = doc.getElementById("content");
        Elements parsed = parse.getElementsByTag("tr");
        if(parsed.text().equals("")){
            return null;
        }
        return parsed;
    }
    
}
