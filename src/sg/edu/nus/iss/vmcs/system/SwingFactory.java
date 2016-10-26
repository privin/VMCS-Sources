/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.vmcs.system;

import java.awt.Frame;
import sg.edu.nus.iss.vmcs.customer.CustomerPanelAwt;
import sg.edu.nus.iss.vmcs.customer.MyCustomerPanel;
import sg.edu.nus.iss.vmcs.customer.TransactionController;
import sg.edu.nus.iss.vmcs.machinery.MachineryController;
import sg.edu.nus.iss.vmcs.machinery.MachinerySimulatorPanelAwt;
import sg.edu.nus.iss.vmcs.maintenance.MaintenanceController;
import sg.edu.nus.iss.vmcs.maintenance.MaintenancePanel;


/**
 *
 * @author Srishti Miglani
 */
public class SwingFactory extends AbstractGUIFactory {
    
    private static SwingFactory swingFactory = null; 
    
    protected SwingFactory(){
        
    }
    
    public MySimulationControlPanel createSimulatorPanel(SimulationController controller){
         SimulatorControlPanelAwt simulatorPanel = new SimulatorControlPanelAwt(controller);
         return simulatorPanel;
     }
    public MyMaintenancePanelAwt createMaintainerPanel(MySimulationControlPanel fr, MaintenanceController mc){
        MaintenancePanel maintenancePanel = new MaintenancePanel((Frame) fr, mc);
        return maintenancePanel;
    }
    public MyMachinerySimulatorPanel createMachinerySimulatorPanel(MySimulationControlPanel frm, MachineryController machCtrl){
        MachinerySimulatorPanelAwt machineryPanel = new MachinerySimulatorPanelAwt(frm, machCtrl);
        return machineryPanel;
    }
    public MyCustomerPanel createCustomerPanel(MySimulationControlPanel fr, TransactionController ctrl) {
        CustomerPanelAwt customerPanel = new CustomerPanelAwt(fr, ctrl);
        return customerPanel;
    }
    
    public static SwingFactory getInstance(){
        if(swingFactory == null){
            swingFactory = new SwingFactory();
            return swingFactory;
        }
        return swingFactory;
    }
    
    
}
