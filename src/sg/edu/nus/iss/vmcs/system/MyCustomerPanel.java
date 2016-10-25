/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.vmcs.system;

import java.awt.Dialog;
import java.awt.Frame;
import sg.edu.nus.iss.vmcs.customer.CustomerPanel;
import sg.edu.nus.iss.vmcs.customer.TransactionController;

/**
 *
 * @author Srishti Miglani
 */
public interface MyCustomerPanel{
    public CustomerPanel createCustomerPanel(MySimulationControlPanel scp, TransactionController tx);

}
