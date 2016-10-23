/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.store;

import java.util.HashMap;
import java.util.Observable;
import sg.edu.nus.iss.vmcs.machinery.MachineryController;
import sg.edu.nus.iss.vmcs.system.MainController;

/**
 * This entity object implements a generic storage item class&#46; It performs actions like;
 * returning content (Store Item identification), setting quantity, returning quantity,
 * increment quantity, decrement (release) quantity&#46;
 *
 * @see CashStore
 * @see CashStoreItem
 * @see Coin
 * @see DrinksBrand
 * @see DrinksStore
 * @see DrinksStoreItem
 * @see Store
 * @see StoreController
 * @see StoreObject
 * 
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class StoreItem extends Observable{
	private StoreObject content;
	private int quantity; 

	/**
	 * This constructor creates an instance of StoreItem.
	 * @param content the content of the StoreItem.
	 * @param quantity the quantity of the content.
         * 
	 */
        
	public StoreItem(StoreObject content, int quantity) {
		this.content = content;
		this.quantity = quantity;
                System.out.println("sdfsfsd" +MainController.getMainController().getMachineryController());
                this.addObserver(MainController.getMainController().getMachineryController());
	}

	/**
	 * This method returns the content of the StoreItem.
	 * @return the StoreObject.
	 */
	public StoreObject getContent() {
		return content;
	}

	/**
	 * This method sets the Store Object held by the Store Item.
	 * @param ct the StoreObject.
	 */
	public void setContent(StoreObject ct) {
		content = ct;
	}

	/**
	 * This method sets the total number of StoreItem held.
	 * @param quantity the number of StoreItem.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
                System.out.println("quantity sdf set quantity" +quantity);
                setChanged();
                notifyObservers(quantity);
	}

	/**
	 * This method returns the total number of StoreItem held&#46;
	 * @return the number of StoreItem.&#46;
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * This method increase the quantity by 1.
	 */
	public void store() {
		quantity++;
                setChanged();
                notifyObservers(quantity);
	}
	
	/**
	 * This method decrease the quantity by 1 but not less than 0.
	 */
	public void decrement() {
		quantity--;
		if (quantity < 0)
			quantity = 0;
               System.out.println("quantity sdf decrement" +quantity);
                setChanged();
                notifyObservers(quantity);

	}

	/**
	 * This method increase the quantity by 1.
	 */
	public void increment() {
		quantity++;
                setChanged();
                notifyObservers(quantity);
	}
        
        
}//End of class StoreItem