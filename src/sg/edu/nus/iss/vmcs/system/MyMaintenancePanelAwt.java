/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.vmcs.system;

import sg.edu.nus.iss.vmcs.maintenance.CoinDisplay;
import sg.edu.nus.iss.vmcs.maintenance.DrinkDisplay;
import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 *
 * @author Srishti Miglani
 */
public interface MyMaintenancePanelAwt extends GUIPanel{
//    public void createMaintenancePanel();
    public void display() ;
    public void closeDown();
    public CoinDisplay getCoinDisplay();
    public DrinkDisplay getDrinksDisplay();
    public void displayPasswordState(boolean st);
    public void setActive(int comp, boolean st);
    public int getCurIdx();
    public void displayTotalCash(int tc) ;
    public void displayCoins(int cc);
    public void updateQtyDisplay(int type, int idx, int qty) throws VMCSException;
    public void updateCurrentQtyDisplay(int type, int qty) throws VMCSException;
    public void initCollectCash();
    public void initTotalCash();
    public void clearPassword();
    public void displayPrice(int price);
}
