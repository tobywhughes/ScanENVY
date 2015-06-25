/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanenvy;

/**
 *
 * @author Tobias Hughes
 */
public class ScrapeObject {
    private String upc;
    private String name;
    private boolean noError;
    
    public ScrapeObject(String upc, String name, boolean noError){
        this.upc = upc;
        this.name = name;
        this.noError = noError;
    }
    
    public void setUPC(String upc)
    {
        this.upc = upc;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setNoError(boolean noError){
        this.noError = noError;
    }
    
    public String getUPC(){
        return upc;
    }
    
    public String getName(){
        return name;
    }
    
    public boolean noError(){
        return noError;
    }
}
