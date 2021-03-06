package sg.edu.nus.iss.vmcs.denomination;

import sg.edu.nus.iss.vmcs.customer.TransactionController;
import sg.edu.nus.iss.vmcs.store.CashStore;
import sg.edu.nus.iss.vmcs.store.CashStoreItem;
import sg.edu.nus.iss.vmcs.util.VMCSException;

public class Cents20Dispenser implements DispenseChain {

	private static CashStoreItem item;

	public Cents20Dispenser(CashStoreItem item) {
		super();
		Cents20Dispenser.setItem(item);
	}

	public Cents20Dispenser() {
		// TODO Auto-generated constructor stub
	}

	private static DispenseChain chain; 
	public static DispenseChain getChain() {
		return chain;
	}

	public static void setChain(DispenseChain chain) {
		Cents20Dispenser.chain = chain;
	}

	@Override
	public void setNextChain(DispenseChain nextChain) {
		Cents20Dispenser.setChain(nextChain);	
	}

	@Override
	public boolean dispense(int changeRequired,int totalChange,TransactionController txCtrl) {
    try{
    	int changeBal=changeRequired;
    	int quantityRequired=0;
    	int value=CashStore.DEMONINATION_20;
    	int quantity=item.getQuantity();
    	
    	while(changeBal>0&&changeBal>=value&&quantity>0){
			changeBal-=value;
			quantityRequired++;
			quantity--;
		}
		txCtrl.getMainController().getMachineryController().giveChange(item,quantityRequired);
		System.out.println("20 Denominations:"+quantityRequired);
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

	public static CashStoreItem getItem() {
		return item;
	}

	public static void setItem(CashStoreItem item) {
		Cents20Dispenser.item = item;
	}

}
