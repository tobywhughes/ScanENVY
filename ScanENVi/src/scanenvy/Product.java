package scanenvy;

/**
 * Class that holds information about a specific product
 * 
 * @version 6/2/15
 */
public class Product implements java.io.Serializable
{
    private String upc;
    private String name;
    private String manuId;
    private String manufact;
    private int rType;
    
    
    public Product(String code,String name,int rType, String manufact)
    {
        upc=code;
        this.name=name;
        this.manufact=manufact;
        this.rType=rType;
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
    
    
    /*
    Getter And Setter Methods for Product Class
    */
    
    public String getName(){
    	return name;
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
