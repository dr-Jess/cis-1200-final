package org.cis1200.helltaker;

import org.cis1200.helltaker.enums.GameStatus;
import org.cis1200.helltaker.objects.*;

import java.io.*;
import java.util.Scanner;

public class LevelIO {
    private Tile[][] board = new Tile[10][];
    private Item[][] itemBoard = new Item[10][];
    private int playerX = -1;
    private int playerY = -1;
    private int numMoves = -1;
    private GameStatus status = null;

    // call directly when testing
    public LevelIO(Reader r) throws IOException {
        BufferedReader reader = new BufferedReader(r);
        String line = reader.readLine();
        int lineNum = 1;
        int keys = -1;
        int playerCount = 0;
        while (line != null) {
            if (lineNum == 1) {
                try {
                    numMoves = Integer.parseInt(line);
                    if (numMoves < 0) {
                        throw new IOException("Invalid file format");
                    }
                } catch (NumberFormatException e) {
                    throw new IOException("Invalid file format");
                }
            } else if (lineNum == 2) {
                switch (line) {
                    case "PLAYING" -> status = GameStatus.PLAYING;
                    case "WON" -> status = GameStatus.WON;
                    case "LOST" -> status = GameStatus.LOST;
                    default -> throw new IOException("Invalid file format");
                }
            } else if (lineNum == 3) {
                try {
                    keys = Integer.parseInt(line);
                    if (keys < 0) {
                        throw new IOException("Invalid file format");
                    }
                } catch (NumberFormatException e) {
                    throw new IOException("Invalid file format");
                }
            } else if (lineNum <= 13) {
                String[] tiles = line.split(" ");
                board[lineNum - 4] = stringsToTiles(tiles);
            } else if (lineNum <= 23) {
                String[] items = line.split(" ");
                itemBoard[lineNum - 14] = stringsToItems(items, keys);
                for (int i = 0; i < 10; i++) {
                    if (itemBoard[lineNum - 14][i] instanceof Player) {
                        playerX = i;
                        playerY = lineNum - 14;
                        playerCount++;
                    }
                }
            } else {
                throw new IOException("Invalid file format");
            }
            line = reader.readLine();
            lineNum++;
        }
        reader.close();
        if (lineNum != 24 || status == null || keys == -1 || playerX == -1 || playerY == -1
                || playerCount != 1 || numMoves == -1) {
            throw new IOException("Invalid file format");
        }
    }

    public LevelIO(String filename) throws IOException {
        this(new FileReader("files" + File.separator + filename + ".txt"));
    }

    public Tile[][] getBoard() {
        return board;
    }

    public Item[][] getItemBoard() {
        return itemBoard;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public int getNumMoves() {
        return numMoves;
    }

    public GameStatus getStatus() {
        return status;
    }

    public static void writeLevel(Helltaker h, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter("files" + File.separator + filename + ".txt")
        );
        writer.write(h.toString());
        writer.close();
    }

    public static void devLevelBuilder() {
        try {
            Scanner s = new Scanner(System.in);
            String filename = s.nextLine();
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("files" + File.separator + filename + ".txt")
            );
            int lineCount = 0;
            while (lineCount < 23) {
                if (lineCount > 3) {
                    int tileCount = 0;
                    String line = s.nextLine();
                    String[] split = line.split(" ");
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < Integer.parseInt(split[0]); i++) {
                        if (tileCount == 10) {
                            writer.newLine();
                            break;
                        }
                        sb.append(split[1]);
                        sb.append(" ");
                        tileCount++;
                    }
                } else {
                    String line = s.nextLine();
                    writer.write(line);
                    writer.newLine();
                }
                lineCount++;
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        devLevelBuilder();
    }

    public static Tile[] stringsToTiles(String[] tiles) throws IOException {
        if (tiles.length != 10) {
            throw new IOException("Invalid file format");
        }
        Tile[] tileRow = new Tile[10];
        for (int i = 0; i < 10; i++) {
            switch (tiles[i]) {
                case "k" -> tileRow[i] = new Key();
                case "a" -> tileRow[i] = new AltSpike(false);
                case "A" -> tileRow[i] = new AltSpike(true);
                case "S" -> tileRow[i] = new Spike();
                case "W" -> tileRow[i] = new Wall();
                case "f" -> tileRow[i] = new Floor();
                case "g" -> tileRow[i] = new Goal();
                default -> throw new IOException("Invalid file format");
            }
        }
        return tileRow;
    }

    public static Item[] stringsToItems(String[] items, int keys) throws IOException {
        if (items.length != 10) {
            throw new IOException("Invalid file format");
        }
        Item[] itemRow = new Item[10];
        for (int i = 0; i < 10; i++) {
            switch (items[i]) {
                case "P" -> itemRow[i] = new Player(keys);
                case "R" -> itemRow[i] = new Rock();
                case "E" -> itemRow[i] = new Enemy();
                case "L" -> itemRow[i] = new Lock();
                case "_" -> itemRow[i] = null;
                default -> throw new IOException("Invalid file format");
            }
        }
        return itemRow;
    }
}
