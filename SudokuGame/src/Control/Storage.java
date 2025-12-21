/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**
 *
 * @author YOUSSEF FATHY
 */


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Storage {

    private static final String F_EASY = "easy";
    private static final String F_MED = "medium";
    private static final String F_HARD = "hard";
    private static final String F_INC = "incomplete";
    
    private static final String LOG_NAME = "log.txt";
    private static final String GAME_NAME = "game.txt";

    public Storage() {
        makeFolder(F_EASY);
        makeFolder(F_MED);
        makeFolder(F_HARD);
        makeFolder(F_INC);
    }
    private void makeFolder(String s) {
        File f = new File(s);
        if (!f.exists()) {
            f.mkdir();
        }
    }

    public boolean hasUnfinishedGame() {
        File f = new File(F_INC, GAME_NAME);
        return f.exists();
    }

    public boolean hasGame(Difficulty d) {
        String path = resolveFolder(d);
        File dir = new File(path);
        if (dir.exists() && dir.isDirectory()) {
            String[] content = dir.list();
            return content != null && content.length > 0;
        }
        return false;
    }