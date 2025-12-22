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
        new File(p1).mkdirs();
        new File(p2).mkdirs();
        new File(p3).mkdirs();
        new File(p4).mkdirs();
    }

    public boolean hasUnfinishedGame() {
        return new File(p4, f2).exists();
    }

    public boolean hasGame(Difficulty d) {
        File dir = new File(getDir(d));
        File[] files = dir.listFiles((dir1, name) -> name.endsWith(".txt"));
        return files != null && files.length > 0;
    }

    

    public Game loadUnfinishedGame() throws IOException {
        return readFile(new File(p4, f2));
    }

    private Game readFile(File f) throws IOException {
        int[][] m = new int[9][9];
        try (Scanner scan = new Scanner(f)) {
            int r = 0;
            while (scan.hasNextLine() && r < 9) {
                String line = scan.nextLine().trim();
                if (line.isEmpty()) continue;
               String[] parts = line.split("[,\\s]+");
                for (int c = 0; c < 9 && c < parts.length; c++) {
                    m[r][c] = Integer.parseInt(parts[c]);
                }
                r++;
            }
        }
        return new Game(m);
    }

    public void saveGame(Difficulty diff, Game g) throws IOException {
        File f = new File(getDir(diff), "sudoku_gen.txt");
        doWrite(f, g.board);
    }

    public void saveUnfinishedGame(Game g) throws IOException {
        doWrite(new File(p4, f2), g.board);
    }

    public void logAction(String action) throws IOException {
        try (FileWriter fw = new FileWriter(new File(p4, f1), true)) {
            fw.write(action + "\n");
        }
    }

    private String getDir(Difficulty d) {
        if (d == Difficulty.HARD) return p3;
        if (d == Difficulty.MEDIUM) return p2;
        return p1;
    }

    public Game loadGame(Difficulty diff) throws IOException {
        File f = new File(getDir(diff), "sudoku_gen.txt");
        if (!f.exists()) throw new IOException("File not found");
        int[][] m = new int[9][9];
        try (Scanner scan = new Scanner(f)) {
            int r = 0;
            while (scan.hasNextLine() && r < 9) {
                String line = scan.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\s+");
                for (int c = 0; c < 9 && c < parts.length; c++) {
                    m[r][c] = Integer.parseInt(parts[c]);
                }
                r++;
            }
        }
        return new Game(m);
    }

    private void doWrite(File f, int[][] arr) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(f))) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    pw.print(arr[i][j] + (j == 8 ? "" : " "));
                }
                pw.println();
            }
        }
    }
}