/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.vmcs.store;

/**
 *
 * @author samru
 */
public class CashStoreIterator extends StoreIterator{
	private CashStore store;
	public CashStoreIterator(CashStore store) {
		super(store);
		this.store=store;
	}
	
	public int findCashStoreIndex (Coin c) {
		super.First();
		while(super.hasNext()){
			StoreItem item=super.next();
			if(item!=null){
				Coin current = (Coin) item.getContent();
				if (current.getWeight() == c.getWeight()){
					return super.getCurrent();
				}
			}
		}
		
		return -1;
        }
    
        public Coin findCoin(double weight){
            
            super.First();
		while(super.hasNext()){
			StoreItem item=super.next();
			if(item!=null){
				Coin current = (Coin) item.getContent();
				if (current.getWeight() == weight){
					return current;
				}
			}
		}
		
		return null;
	}
}
