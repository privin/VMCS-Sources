/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.store;

import java.io.IOException;

import sg.edu.nus.iss.vmcs.denomination.Cents100Dispenser;
import sg.edu.nus.iss.vmcs.denomination.Cents10Dispenser;
import sg.edu.nus.iss.vmcs.denomination.Cents20Dispenser;
import sg.edu.nus.iss.vmcs.denomination.Cents50Dispenser;
import sg.edu.nus.iss.vmcs.denomination.Cents5Dispenser;
import sg.edu.nus.iss.vmcs.denomination.DispenseChain;

/**
 * This object represents the store of cash in the vending machine.
 * 
 * @see CashStoreItem
 * @see Coin
 * @see DrinksBrand
 * @see DrinksStore
 * @see DrinksStoreItem
 * @see Store
 * @see StoreController
 * @see StoreItem
 * @see StoreObject
 * 
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class CashStore extends Store {
	/**This is the constant for coin invalid weight.*/
	public final static int INVALID_COIN_WEIGHT = 9999;
	public final static int DEMONINATION_100=100;
	public final static int DEMONINATION_50=50;
	public final static int DEMONINATION_20=20;
	public final static int DEMONINATION_10=10;
	public final static int DEMONINATION_5=5;
	
	public CashStore(PropertyLoader propertyLoader) {
		super(propertyLoader);
	}
	
	/**
	 * This constructor creates an instance of the CashStore object.
	 */
	public CashStore() {
	}
	@Override
	public CashStoreIterator CreateIterator() {
		return new CashStoreIterator(this);
	}


		/**
		 * This method find and returns the index of the coin in the CashStore of the given Coin&#46;
		 * @param c the Coin of interest&#46;
		 * @return the index of the given Coin&#46; Return -1 if unknown Coin is detected.
		 */
		public int findCashStoreIndex (Coin c) {
			int size = getStoreSize();
			for (int i = 0; i < size; i++) {
				StoreItem item = (CashStoreItem) getStoreItem(i);
				Coin current = (Coin) item.getContent();
				if (current.getWeight() == c.getWeight())
					return i;
			}
			return -1;
		}

	/**
	 * This method determine whether the given weight of the {@link Coin} is valid.
	 * @param weight the weight of the Coin to be tested.
	 * @return TRUE if the weight is valid, otherwise, return FALSE.
	 */
	public boolean isValidWeight(double weight){
		int size = getStoreSize();
		for (int i = 0; i < size; i++) {
			StoreItem item = (CashStoreItem) getStoreItem(i);
			Coin current = (Coin) item.getContent();
			if (current.getWeight() == weight)
				return true;
		}
		return false;
	}

	@Override
	public void initStore() throws IOException {
		System.out.println("loading cash file:"+propertyLoader);
		// get the cash file from the environment property file;
		int numOfItems = propertyLoader.getNumOfItems();
		setStoreSize(numOfItems);
		DispenseChain c1=null,c2=null,c3=null,c4=null,c5=null;

		for (int i = 0; i < numOfItems; i++) {

			CashStoreItem item = (CashStoreItem) propertyLoader.getItem(i);
			addItem(i, item);
			Coin coin=(Coin)item.getContent();
			switch(coin.getValue()){

			case(CashStore.DEMONINATION_100):
				c1=new Cents100Dispenser(item);
			break;

			case(CashStore.DEMONINATION_50):

				c2=new Cents50Dispenser(item);
			break;
			case(CashStore.DEMONINATION_20):

				c3=new Cents20Dispenser(item);
			break;
			case(CashStore.DEMONINATION_10):

				c4=new Cents10Dispenser(item);
			break;
			case(CashStore.DEMONINATION_5):

				c5=new Cents5Dispenser(item);
			break;

			default:
				break;
			}
		}
		c1.setNextChain(c2);
		c2.setNextChain(c3);
		c3.setNextChain(c4);
		c4.setNextChain(c5);

	}

	/**
	 * This method will locate a {@link Coin} denomination held, with the input data 
	 * (coin weight)&#46; If found, it returns an existence identifier (reference)&#46;
	 * Otherwise, it informs the requestor that the coin is invalid.
	 * @param weight the weight of the coin to be found.
	 * @return Coin the coin which has the input weight.
	 */
		public Coin findCoin(double weight){
			int size = getStoreSize();
			for (int i = 0; i < size; i++) {
				StoreItem item = (CashStoreItem) getStoreItem(i);
				Coin current = (Coin) item.getContent();
				if (current.getWeight() == weight)
					return current;
			}
			return null;
		}
}//End of class CashStore