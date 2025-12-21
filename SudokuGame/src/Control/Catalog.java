/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**
 *
 * @author Abdelrahman Elsayed
 */
public class Catalog {
    private final boolean current;
    private final boolean allModesExist;

    public Catalog(boolean current, boolean allModesExist) {
        this.current = current;
        this.allModesExist = allModesExist;
    }
    
     public boolean hasCurrentGame() {
        return current;
    }

    public boolean hasAllModes() {
        return allModesExist;
    }
    
}
