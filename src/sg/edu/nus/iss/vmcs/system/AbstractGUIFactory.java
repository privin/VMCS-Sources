/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.vmcs.system;

import sg.edu.nus.iss.vmcs.customer.MyCustomerPanel;
import sg.edu.nus.iss.vmcs.customer.TransactionController;
import sg.edu.nus.iss.vmcs.machinery.MachineryController;
import sg.edu.nus.iss.vmcs.maintenance.MaintenanceController;
import sg.edu.nus.iss.vmcs.store.StoreController;

/**
 *
 * @author Sanskar Deepak
 */
public abstract class AbstractGUIFactory {

    private static AbstractGUIFactory guiFactory = null;

    public abstract MySimulationControlPanel createSimulatorPanel(SimulationController controller);

    public abstract MyMaintenancePanel createMaintainerPanel(MySimulationControlPanel fr, MaintenanceController mc);

    public abstract MyMachinerySimulatorPanel createMachinerySimulatorPanel(MySimulationControlPanel frm, MachineryController machCtrl);

    public abstract MyCustomerPanel createCustomerPanel(MySimulationControlPanel fr, TransactionController ctrl);

    public static AbstractGUIFactory getFactory(String type) {
        if (guiFactory == null) {
            if (type.equals("Swing")) {
                guiFactory = new SwingFactory();
            }
            return guiFactory;
        } else {
            return guiFactory;
        }

    }
}
