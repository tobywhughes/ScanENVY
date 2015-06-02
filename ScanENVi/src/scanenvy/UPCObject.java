/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanenvy;

/**
 *
 * @author Kirjsten
 */
public class UPCObject {
    
    private int upc, recycleType;
    private String item, manufact;
    
    
    public UPCObject(){
    }
    
    public UPCObject(int upcPar, String itemPar, String manufactPar, int recyclePar){
        upc = upcPar;
        item = itemPar;
        manufact = manufactPar;
        recycleType = recyclePar;
    }
    
    int getUPC()
    {
        return upc;
    }
    
    String getItem()
    {
        return item;
    }
    
    void setItem(String itemSet)
    {
        item = itemSet;
    }
    
    String getManufact()
    {
        return manufact;
    }
    
    void setManufact(String manufactSet)
    {
        manufact = manufactSet;
    }
    
    int getRecycleType()
    {
        return recycleType;
    }
    
    void setRecycleType(int recycleSet)
    {
        recycleType = recycleSet;
    }
    
}
