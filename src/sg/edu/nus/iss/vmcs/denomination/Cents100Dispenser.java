package sg.edu.nus.iss.vmcs.denomination;

import sg.edu.nus.iss.vmcs.customer.TransactionController;
import sg.edu.nus.iss.vmcs.store.CashStore;
import sg.edu.nus.iss.vmcs.store.CashStoreItem;
import sg.edu.nus.iss.vmcs.store.Coin;
import sg.edu.nus.iss.vmcs.store.DrinksBrand;
import sg.edu.nus.iss.vmcs.store.DrinksStore;
import sg.edu.nus.iss.vmcs.store.DrinksStoreItem;
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.store.StoreObject;
import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 * This entity object implements a generic Store&#46; It has methods to load (add) {@link StoreItem}
 * into the Store and release {@link StoreItem} from the Store.
 * 
 * @see CashStore
 * @see CashStoreItem
 * @see Coin
 * @see DrinksBrand
 * @see DrinksStore
 * @see DrinksStoreItem
 * @see StoreController
 * @see StoreItem
 * @see StoreObject
 * 
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class Cents100Dispenser implements DispenseChain{
	
	private static CashStoreItem item;

	public static CashStoreItem getItem() {
		return item;
	}

	public static void setItem(CashStoreItem item) {
		Cents100Dispenser.item = item;
	}

	public Cents100Dispenser(CashStoreItem item) {
		
		super();
		
		Cents100Dispenser.setItem(item);
	}

	public Cents100Dispenser() {
		// TODO Auto-generated constructor stub
	}

	private static DispenseChain chain; 
	public static DispenseChain getChain() {
		return chain;
	}

	public static void setChain(DispenseChain chain) {
		Cents100Dispenser.chain = chain;
	}

	@Override
	public void setNextChain(DispenseChain nextChain) {
		Cents100Dispenser.setChain(nextChain);
			
	}

	@Override
	public boolean dispense(int changeRequired,int totalChange,TransactionController txCtrl) {
    try{
    	int changeBal=changeRequired;
    	int quantityRequired=0;
    	int value=CashStore.DEMONINATION_100;
    	int quantity=item.getQuantity();
    	
    	while(changeBal>0&&changeBal>=value&&quantity>0){
			changeBal-=value;
			quantityRequired++;
			quantity--;
		}
		txCtrl.getMainController().getMachineryController().giveChange(item,quantityRequired);
		System.out.println("100 Denominations:"+quantityRequired);
		if(changeBal>0 &&(changeBal<value || quantity<=0))
			return chain.dispense(changeBal,totalChange, txCtrl);
		else if(changeBal==0)
			txCtrl.getCustomerPanel().setChange(totalChange-changeBal);
    }
    catch(VMCSException ex){
		txCtrl.terminateFault();
		return false;
	}
    return true;
	}

}
