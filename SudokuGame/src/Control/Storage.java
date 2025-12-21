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

    private final String p1 = "easy";
    private final String p2 = "medium";
    private final String p3 = "hard";
    private final String p4 = "incomplete";
    
    private final String f1 = "log.txt";
    private final String f2 = "game.txt";

    public Storage() {
        check(p1);
        check(p2);
        check(p3);
        check(p4);
    }

    private void check(String s) {
        File f = new File(s);
        if (f.exists() == false) {
            f.mkdir();
        }
    }

    public boolean hasUnfinishedGame() {
        File x = new File(p4, f2);
        if (x.exists()) {
            return true;
        }
        return false;
    }

    public boolean hasGame(Difficulty diff) {
        String s = getDir(diff);
        File f = new File(s);
        if (!f.exists()) return false;
        
        String[] l = f.list();
        if (l == null) return false;
        
        return l.length > 0;
    }

    public Game loadGame(Difficulty diff) throws IOException {
        File f = null;
        
        if (diff == null) {
             f = new File(p4, f2);
        } else {
            String s = getDir(diff);
            File d = new File(s);
            File[] all = d.listFiles();
            
            if (all != null && all.length > 0) {
                f = all[0];
            } else {
                throw new IOException();
            }
        }

        int[][] m = new int[9][9];
        Scanner scan = new Scanner(f);
        
        int r = 0;
        while (scan.hasNextLine()) {
            if (r >= 9) break;
            String line = scan.nextLine();
            String[] parts = line.split(",");
            for (int c = 0; c < 9; c++) {
                if (c < parts.length) {
                    m[r][c] = Integer.parseInt(parts[c].trim());
                }
            }
            r++;
        }
        scan.close();
        
        return new Game(m);
    }

    public void saveGame(Difficulty diff, Game g) throws IOException {
        String s = getDir(diff);
        String n = "sudoku_" + System.currentTimeMillis() + ".txt";
        File f = new File(s, n);
        
        doWrite(f, g.board);
    }

    public void saveUnfinishedGame(Game g) throws IOException {
        File f = new File(p4, f2);
        doWrite(f, g.board);
    }

    public void logAction(String action) throws IOException {
        File f = new File(p4, f1);
        FileWriter fw = new FileWriter(f, true);
        fw.write(action + "\n");
        fw.close();
    }

    private String getDir(Difficulty d) {
        if (d == Difficulty.HARD) return p3;
        if (d == Difficulty.MEDIUM) return p2;
        return p1; 
    }

    private void doWrite(File f, int[][] arr) throws IOException {
        FileWriter fw = new FileWriter(f);
        PrintWriter pw = new PrintWriter(fw);
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                pw.print(arr[i][j]);
                if (j != 8) {
                    pw.print(",");
                }
            }
            pw.println();
        }
        pw.close();
    }
}