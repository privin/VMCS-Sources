/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.vmcs.system;

import java.awt.Frame;
import sg.edu.nus.iss.vmcs.customer.CustomerPanel;
import sg.edu.nus.iss.vmcs.customer.MyCustomerPanel;
import sg.edu.nus.iss.vmcs.customer.TransactionController;
import sg.edu.nus.iss.vmcs.machinery.MachineryController;
import sg.edu.nus.iss.vmcs.machinery.MachinerySimulatorPanel;
import sg.edu.nus.iss.vmcs.maintenance.MaintenanceController;
import sg.edu.nus.iss.vmcs.maintenance.MaintenancePanel;


/**
 *
 * @author Srishti Miglani
 */
public class SwingFactory extends AbstractGUIFactory {
    public MySimulationControlPanel createSimulatorPanel(SimulationController controller){
         SimulatorControlPanel simulatorPanel = new SimulatorControlPanel(controller);
         return simulatorPanel;
     }
    public MyMaintenancePanel createMaintainerPanel(MySimulationControlPanel fr, MaintenanceController mc){
        MaintenancePanel maintenancePanel = new MaintenancePanel((Frame) fr, mc);
        return maintenancePanel;
    }
    public MyMachinerySimulatorPanel createMachinerySimulatorPanel(MySimulationControlPanel frm, MachineryController machCtrl){
        MachinerySimulatorPanel machineryPanel = new MachinerySimulatorPanel(frm, machCtrl);
        return machineryPanel;
    }
    public MyCustomerPanel createCustomerPanel(MySimulationControlPanel fr, TransactionController ctrl) {
        CustomerPanel customerPanel = new CustomerPanel(fr, ctrl);
        return customerPanel;
    }
    
    
    
}
