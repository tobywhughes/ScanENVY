/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanenvy.Toby;

import scanenvy.Product;

public class DisplayProduct{
	public Product product;
	public Voting comment;
	
	public DisplayProduct(Product inproduct, Voting incomment){
		product = inproduct;
		comment = incomment;
	}
}
