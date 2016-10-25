/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.vmcs.system;

import sg.edu.nus.iss.vmcs.machinery.MachineryController;
import sg.edu.nus.iss.vmcs.machinery.MachinerySimulatorPanel;
import sg.edu.nus.iss.vmcs.machinery.StoreViewer;

/**
 *
 * @author Srishti Miglani
 */
public interface MyMachinerySimulatorPanel extends GUIPanel{
//    void createMachinerySimulatorPanel();
    public void display();
    public void closeDown();
    public void setDoorState(boolean state);
    public void refresh();
    public void dispose();
    public void setActive(boolean state);  
    public StoreViewer getCashStoreDisplay();

    public StoreViewer getDrinksStoreDisplay();
}
