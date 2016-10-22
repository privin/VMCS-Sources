package sg.edu.nus.iss.vmcs.denomination;

import sg.edu.nus.iss.vmcs.customer.TransactionController;

public interface DispenseChain {
	
	void setNextChain(DispenseChain nextChain);
	
	boolean dispense(int changeRequired,int changeBal,TransactionController txCtrl);
	
}
