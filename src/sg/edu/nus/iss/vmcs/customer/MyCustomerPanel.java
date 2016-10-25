/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.vmcs.customer;

import sg.edu.nus.iss.vmcs.system.GUIPanel;

/**
 *
 * @author Sanskar
 */
public interface MyCustomerPanel  extends GUIPanel{
//    public void createCustomerPanel();
    public void display();
    public void setTotalMoneyInserted(int i);
    public int addMoney(int i);
    public int getTotalMoneyInserted() ;
    public void setChange(int i);
    public void setChange(String s);
    public String getChange();
    public void setCan(String name);
    public String getCan();
    public void resetCan();
    public void resetTotalInserted();
    public void resetChange();
    public void closeDown();
    public void displayInvalidCoin(boolean isOn);
    public void displayChangeStatus(boolean isOn);
    public void setDrinkSelectionBoxActive(boolean active);
    public void setCoinInputBoxActive(boolean active);
    public void setTerminateButtonActive(boolean active);
    public CoinInputBox getCoinInputBox() ;
    public DrinkSelectionBox getDrinkSelectionBox();
    public void setActive(int comp, boolean st);
}
