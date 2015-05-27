/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanenvy;


/**
 * Write a description of class Barcode here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Product implements java.io.Serializable
{
    private String upc;
    private String name;
    private String manuId;
    private String manufact;
    private int rType;
    
    public Product(String code,String name,String manufact,int rType)
    {
        upc=code;
        this.name=name;
        this.manufact=manufact;
        this.rType=rType;
    }
    public Product(String name, String manufact,int type,String upc)
    {
        this.upc=upc;
        this.name=name;
        rType=type;
        this.manufact=manufact;
    }
    public Product(String code, String manuID)
    {
    	upc=code;
        this.manuId=manuId;
    }
    
    public Product(String code){
    	upc=code;
    }
    
    public Product(){
    	
    	
    }
    
    public String getName(){
    	return "Example Product";
    }
    public String getRecycleType(){
    	return "1";
    }
    public String getUpc()
    {
        return upc;
    }
    public void setUpc(String upc)
    {
        this.upc=upc;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getManuId()
    {
        return manuId;
    }
    public void setManuId(String manuId)
    {
        this.manuId=manuId;
    }
    public String getManufact()
    {
        return manufact;
    }
    public void setManufact(String manufact)
    {
        this.manufact=manufact;
    }
    public int getRType()
    {
        return rType;
    }
    public void setRType(int rType)
    {
        this.rType=rType;
    }
}
