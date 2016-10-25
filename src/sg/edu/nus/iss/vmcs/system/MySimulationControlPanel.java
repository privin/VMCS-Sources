/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.vmcs.system;

/**
 *
 * @author Srishti Miglani
 */
public interface MySimulationControlPanel  extends GUIPanel{
//      public void createSimulatorControlPanel(); 
      public void display();
      public void setSimulationActive (boolean isOn);
      public void setButtonState (int id, boolean state);
      public void closeDown ();
      public void setActive (int id, boolean state); 
}
