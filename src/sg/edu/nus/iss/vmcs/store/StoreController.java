/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.store;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This control object manages changes in CashStore attributes and 
 * the DrinksStore attributes.
 *
 * @see CashStore
 * @see CashStoreItem
 * @see Coin
 * @see DrinksBrand
 * @see DrinksStore
 * @see DrinksStoreItem
 * @see Store
 * @see StoreItem
 * @see StoreObject
 * 
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class StoreController implements Observer{
	private Map<Integer, Store> stores;

	/**
	 * This constructor creates an instance of StoreController object.
	 * @param cashLoader the cash loader.
	 * @param drinksLoader the drinks loader.
	 */
	public StoreController(
		PropertyLoader cashLoader,
		PropertyLoader drinksLoader) {
		stores = new HashMap<Integer, Store>();
		stores.put(Store.CASH, new CashStore(cashLoader));
		stores.put(Store.DRINK, new DrinksStore(drinksLoader));
	}

	/**
	 * This method instantiate the {@link CashStore}, {@link DrinksStore} and initialize it.
	 * @throws IOException if fail to initialize stores; reading properties.
	 */
	public void initialize() throws IOException {
		initializeStores();
	}

	/**
	 * This method initiates the initialization of the {@link DrinkStore} and {@link CashStore}
	 * from data read-in from an input file.
	 * @throws IOException if fail to initialize stores; reading properties.
	 */
	private void initializeStores() throws IOException {
		for (Map.Entry<Integer, Store> entry : stores.entrySet()) {
			entry.getValue().initStore();
		}
	}

	/**
	 * This method will instruct the {@link CashStore} to store the {@link Coin} sent as input, and
	 * update the display on the Machinery Simulator Panel.
	 * @param c the Coin to be stored.
	 */
	public void storeCoin(Coin c) {		
		CashStoreIterator id = ((CashStore)getStore(Store.CASH)).CreateIterator();
                        int idx=id.findCashStoreIndex(c);
		CashStoreItem item;
		item = (CashStoreItem) this.getStoreItem(Store.CASH, --idx);
		item.increment();
	}

	/**
	 * This method return the total size of the {@link Store} of the given type of {@link Store}.
	 * @param type the type of the Store (either CASH or DRINK).
	 * @return the size of the store of the given type of Store.
	 */
	public int getStoreSize(int type) {
		return getStore(type).getStoreSize();
	}

	/**
	 * This method returns an array of {@link StoreItem} of the given type of {@link Store}.
	 * @param type the type of Store.
	 * @return an array of StoreItem.
	 */
	public StoreItem[] getStoreItems(int type) {
		return getStore(type).getItems();		
	}

	/**
	 * This method will either:
	 * <br>
	 * - instruct the {@link CashStore} to update the quantity of a {@link Coin} denomination to new
	 * value supplied and update the total cash held in the {@link CashStore}; or
	 * <br>
	 * - instruct the {@link DrinksStore} to update the drinks stock for a {@link DrinksBrand} required
	 * to a new value supplied.
	 * @param type the type of Store.
	 * @param idx the index of the StoreItem.
	 * @param qty the quantity of the StoreItem.
	 */
	public void changeStoreQty(int type, int idx, int qty) {
			System.out.println("StoreController.changeStoreQty: type:"+ type+ " qty:"+ qty);
			getStore(type).setQuantity(idx, qty);			
	}

	/**
	 * This method returns the {@link StoreItem} with the given {@link Store} type and index of {@link StoreItem}.
	 * @param type the type of Store.
	 * @param idx the index of the StoreItem.
	 * @return the StoreItem.
	 */
	public StoreItem getStoreItem(int type, int idx) {
		return getStore(type).getStoreItem(idx);		
	}

	/**
	 * This method will sets the price for the {@link StoreItem} with the given index and price.
	 * @param idx the index of the StoreItem.
	 * @param pr the price of the StoreItem.
	 */
	public void setPrice(int idx, int pr)  {
		DrinksStoreItem item;

		item = (DrinksStoreItem) getStore(Store.DRINK).getStoreItem(idx);
		DrinksBrand bd;

		bd = (DrinksBrand) item.getContent();

		bd.setPrice(pr);
	}

	/**
	 * This method returns the total number of cash held in the {@link CashStore}.
	 * @return the total number of cash held.
	 */
	public int getTotalCash(){
		int i;
		int size;

		size = getStore(Store.CASH).getStoreSize(); 
		CashStoreItem item;
		int qty;
		int val;
		int tc = 0;
		Coin c;

		for (i = 0; i < size; i++) {
			item = (CashStoreItem) getStore(Store.CASH).getStoreItem(i);
			qty = item.getQuantity();
			c = (Coin) item.getContent();
			val = c.getValue();
			tc = tc + qty * val;
		}
		return tc;
	}

	/**
	 * This method will instruct the {@link CashStore} to store the {@link Coin} sent as input, and then
	 * update the display on the {@link sg.edu.nus.iss.vmcs.machinery.MachinerySimulatorPanel}.
	 * @return the number of cash transfered.
	 */
	public int transferAll()  {
		int i;
		int cc = 0; // coin quauntity;
		int size = getStore(Store.CASH).getStoreSize();

		CashStoreItem item;
		for (i = 0; i < size; i++) {
			item = (CashStoreItem) getStore(Store.CASH).getStoreItem(i);
			cc = cc + item.getQuantity();
			item.setQuantity(0);
		}

		return cc;
	}

	/**
	 * This method will close down the store management function of the vending machine.
	 * This involves saving the attributes of the stores to the property file.
	 * @throws IOException if fail to save cash properties and drinks properties.
	 */
	public void closeDown() throws IOException {
		// save back cash property;
		for (Map.Entry<Integer, Store> entry : stores.entrySet()) {
        	entry.getValue().saveProps();
        }
	}

	/**
	 * This method instructs the {@link DrinksStore} to dispense one drink, and then updates the 
	 * {@link sg.edu.nus.iss.vmcs.machinery.MachinerySimulatorPanel}&#46; It returns TRUE or FALSE to indicate whether dispensing
	 * was successful&#46;
	 * @param idx the index of the drinks to be dispensed&#46;
	 */
	public void dispenseDrink(int idx)  {
		DrinksStoreItem item;
		item = (DrinksStoreItem) getStoreItem(Store.DRINK, idx);
		item.decrement();
	}

	/**
	 * This method returns a {@link Store} of a specified type (i&#46;e&#46; Cash or Drink)&#46;
	 * @param type the type of Store&#46;
	 * @return the Store of the specified type&#46;
	 */
	public Store getStore(int type) {
		return stores.get(type);
	}

	/**
	 * This method instructs the {@link CashStore} to issue a number of {@link Coin} of a specific
	 * denomination, and then updates the {@link sg.edu.nus.iss.vmcs.machinery.MachinerySimulatorPanel}&#46; It return TRUE
	 * or FALSE to indicate whether the change issue was successful&#46;
	 * @param idx the index of the Coin&#46;
	 * @param numOfCoins the number of Coin to deduct&#46; 
	 */
	public void giveChange(CashStoreItem item, int numOfCoins)  {
		//CashStoreItem item;
		//item = (CashStoreItem) getStoreItem(Store.CASH, idx);
		for (int i = 0; i < numOfCoins; i++)
			item.decrement();
	}

    @Override
    public void update(Observable o, Object arg) {
        try {        	
            for (Map.Entry<Integer, Store> entry : stores.entrySet()) {
            	entry.getValue().saveProps();
            }
        } catch (IOException ex) {
            Logger.getLogger(StoreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        

}//End of class StoreController