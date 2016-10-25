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
public abstract class StoreIterator {
	private Store store;
	private int current;
	
	public StoreIterator(Store store){
		current=0;
		this.store=store;
	}
	
	public boolean hasNext(){
		if(this.store.getItems()==null||this.store.getStoreSize()<=0){
			return false;
		}
		if(current>=this.store.getStoreSize()){
			return false;
		}
		return true;
	}
	
	public StoreItem next(){
		int index=current;
		current++;
		return this.store.getStoreItem(index);
	}
	
	
	public void First(){
		current=0;
	}
	
	
	public int getCurrent(){
		return this.current;
	}
}
