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
    public Game loadGame(Difficulty d) throws IOException {
        File target;
        
        if (d == null) {
             target = new File(F_INC, GAME_NAME);
        } else {
            String p = resolveFolder(d);
            File dir = new File(p);
            File[] list = dir.listFiles();
            
            if (list == null || list.length == 0) {
                throw new IOException("Empty folder");
            }
            target = list[0];
        }

        int[][] data = parseFile(target);
        return new Game(data);
    }
    public void saveGame(Difficulty d, Game g) throws IOException {
        String p = resolveFolder(d);
        String n = "sudoku_" + System.currentTimeMillis() + ".txt";
        File f = new File(p, n);
        
        saveToFile(f, g.board);
    }

    public void saveUnfinishedGame(Game g) throws IOException {
        File f = new File(F_INC, GAME_NAME);
        saveToFile(f, g.board);
    }

    public void logAction(String s) throws IOException {
        File f = new File(F_INC, LOG_NAME);
        FileWriter fw = new FileWriter(f, true);
        fw.write(s + "\n");
        fw.close();
    }

    private String resolveFolder(Difficulty d) {
        if (d == Difficulty.EASY) return F_EASY;
        if (d == Difficulty.MEDIUM) return F_MED;
        if (d == Difficulty.HARD) return F_HARD;
        return F_EASY;
    }

    private int[][] parseFile(File f) throws IOException {
        int[][] b = new int[9][9];
        Scanner sc = new Scanner(f);
        
        int r = 0;
        while (sc.hasNextLine() && r < 9) {
            String l = sc.nextLine();
            String[] sp = l.split(",");
            for (int c = 0; c < 9; c++) {
                if (c < sp.length) {
                    b[r][c] = Integer.parseInt(sp[c].trim());
                } else {
                    b[r][c] = 0;
                }
            }
            r++;
        }
        sc.close();
        return b;
    }

    private void saveToFile(File f, int[][] b) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(f));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                pw.print(b[i][j]);
                if (j < 8) pw.print(",");
            }
            pw.println();
        }
        pw.close();
    }
}