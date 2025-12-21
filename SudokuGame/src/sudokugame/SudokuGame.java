/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sudokugame;
import view.Controllable;
import view.ViewFacade;
import view.SudokuGameView;

import Control.Viewable;
import Control.ControllerFacade;
/**
 *
 * @author Abdelrahman Elsayed
 */


    /**
     * @param args the command line arguments
     */
   



public class SudokuGame {

    public static void main(String[] args) {

        // Controller Layer
        Viewable controller = new ControllerFacade();

        // View Facade (Presentation → Controller)
        Controllable facade = new ViewFacade(controller);

        // GUI
        new SudokuGameView(facade);
    }
}
