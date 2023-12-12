package org.cis1200.helltaker;

import org.cis1200.helltaker.enums.*;
import org.cis1200.helltaker.objects.*;

import java.io.IOException;
import java.io.Reader;

public class Helltaker {
    // Important: boards are all 10x10 because I'm not coding responsive scaling
    // Further, the outer ring of tiles is assumed to be walls
    private Tile[][] board = new Tile[10][10];
    private Item[][] itemBoard = new Item[10][10];
    private int numMoves;
    private int playerX, playerY;
    private GameStatus status;

    // Initial test code using a hardcoded level 1
    // public Helltaker(){
    // board = new Tile[][]{
    // {new Wall(), new Wall(), new Wall(), new Wall(), new Wall(), new Wall(), new
    // Wall(), new Wall(), new Wall(), new Wall()}
    // , {new Wall(), new Wall(), new Wall(), new Wall(), new Wall(), new Wall(),
    // new Wall(), new Wall(), new Wall(), new Wall()}
    // , {new Wall(), new Wall(), new Wall(), new Wall(), new Wall(), new Floor(),
    // new Floor(), new Wall(), new Wall(), new Wall()}
    // , {new Wall(), new Wall(), new Floor(), new Floor(), new Floor(), new
    // Floor(), new Floor(), new Wall(), new Wall(), new Wall()}
    // , {new Wall(), new Wall(), new Floor(), new Floor(), new Floor(), new
    // Floor(), new Wall(), new Wall(), new Wall(), new Wall()}
    // , {new Wall(), new Floor(), new Floor(), new Wall(), new Wall(), new Wall(),
    // new Wall(), new Wall(), new Wall(), new Wall()}
    // , {new Wall(), new Floor(), new Floor(), new Floor(), new Floor(), new
    // Floor(), new Floor(), new Wall(), new Wall(), new Wall()}
    // , {new Wall(), new Floor(), new Floor(), new Floor(), new Floor(), new
    // Floor(), new Goal(), new Wall(), new Wall(), new Wall()}
    // , {new Wall(), new Wall(), new Wall(), new Wall(), new Wall(), new Wall(),
    // new Wall(), new Wall(), new Wall(), new Wall()}
    // , {new Wall(), new Wall(), new Wall(), new Wall(), new Wall(), new Wall(),
    // new Wall(), new Wall(), new Wall(), new Wall()}};
    // itemBoard = new Item[][]{
    // {null, null, null, null, null, null, null, null, null, null}
    // , {null, null, null, null, null, null, null, null, null, null}
    // , {null, null, null, null, null, null, new Player(0), null, null, null}
    // , {null, null, null, null, new Enemy(), null, null, null, null, null}
    // , {null, null, null, new Enemy(), null, new Enemy(), null, null, null, null}
    // , {null, null, null, null, null, null, null, null, null, null}
    // , {null, null, new Rock(), null, null, new Rock(), null, null, null, null}
    // , {null, null, new Rock(), null, new Rock(), null, null, null, null, null}
    // , {null, null, null, null, null, null, null, null, null, null}
    // , {null, null, null, null, null, null, null, null, null, null}};
    // playerX = 6;
    // playerY = 2;
    // numMoves = 23;
    // status = GameStatus.PLAYING;
    // }
    public Helltaker(String filename) throws IOException {
        LevelIO level = new LevelIO(filename);
        board = level.getBoard();
        itemBoard = level.getItemBoard();
        playerX = level.getPlayerX();
        playerY = level.getPlayerY();
        numMoves = level.getNumMoves();
        status = level.getStatus();
    }

    // For testing
    public Helltaker(Reader r) throws IOException {
        LevelIO level = new LevelIO(r);
        board = level.getBoard();
        itemBoard = level.getItemBoard();
        playerX = level.getPlayerX();
        playerY = level.getPlayerY();
        numMoves = level.getNumMoves();
        status = level.getStatus();
    }

    public GameStatus getStatus() {
        return status;
    }

    public int getNumMoves() {
        return numMoves;
    }

    public GameObjs[][] getBoard() {
        GameObjs[][] enumBoard = new GameObjs[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                enumBoard[i][j] = board[i][j].getEnum();
            }
        }
        return enumBoard;
    }

    public GameObjs[][] getItemBoard() {
        GameObjs[][] enumItemBoard = new GameObjs[itemBoard.length][itemBoard[0].length];
        for (int i = 0; i < itemBoard.length; i++) {
            for (int j = 0; j < itemBoard[i].length; j++) {
                if (itemBoard[i][j] == null) {
                    enumItemBoard[i][j] = null;
                } else {
                    enumItemBoard[i][j] = itemBoard[i][j].getEnum();
                }
            }
        }
        return enumItemBoard;
    }

    public void playerMove(Direction direction) {
        if (numMoves == 0) {
            status = GameStatus.LOST;
            return;
        }
        int xChange = 0;
        int yChange = 0;
        switch (direction) {
            case UP -> yChange = -1;
            case DOWN -> yChange = 1;
            case LEFT -> xChange = -1;
            case RIGHT -> xChange = 1;
            default -> {
                return;
            }
        }
        // Check if tile is solid
        if (!board[playerY + yChange][playerX + xChange].isSolid()) {
            // Check if there's no item there
            if (itemBoard[playerY + yChange][playerX + xChange] != null) {
                // There's an item, kick it
                Collision c = itemBoard[playerY + yChange][playerX + xChange].collide(
                        (Player) itemBoard[playerY][playerX],
                        board[playerY + (2 * yChange)][playerX + (2 * xChange)],
                        itemBoard[playerY + (2 * yChange)][playerX + (2 * xChange)]
                );
                switch (c) {
                    case STOP -> {
                        numMoves--;
                    }
                    case SLIDE -> {
                        numMoves--;
                        itemBoard[playerY + (2 * yChange)][playerX
                                + (2 * xChange)] = itemBoard[playerY + yChange][playerX + xChange];
                        itemBoard[playerY + yChange][playerX + xChange] = null;
                    }
                    case BREAK -> {
                        numMoves--;
                        itemBoard[playerY + yChange][playerX + xChange] = null;
                    }
                    case UNLOCK -> {
                        numMoves--;
                        itemBoard[playerY + yChange][playerX
                                + xChange] = itemBoard[playerY][playerX];
                        itemBoard[playerY][playerX] = null;
                        playerX += xChange;
                        playerY += yChange;
                    }
                    default -> {

                    }
                }
            } else {
                // There's no item, move player
                numMoves--;
                itemBoard[playerY + yChange][playerX + xChange] = itemBoard[playerY][playerX];
                itemBoard[playerY][playerX] = null;
                playerX += xChange;
                playerY += yChange;
            }
            // Updates for next round
            for (int i = 0; i < itemBoard.length; i++) {
                for (int j = 0; j < itemBoard[i].length; j++) {
                    board[i][j].nextTurn();
                    if (itemBoard[i][j] != null) {
                        itemBoard[i][j].nextTurn();
                        Action a = board[i][j].objectOnTile(itemBoard[i][j]);
                        switch (a) {
                            case ADDKEY -> {
                                ((Player) itemBoard[i][j]).addKey();
                                board[i][j] = new Floor();
                            }
                            case REMOVETURN -> {
                                numMoves--;
                            }
                            case ENDGAME -> {
                                status = GameStatus.WON;
                            }
                            case BREAK -> {
                                itemBoard[i][j] = null;
                            }
                            case NONE -> {
                            }
                            default -> {
                            }
                        }
                    }
                }
            }
            return;
        }
        return;
    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boardString.append(board[i][j].toString());
                boardString.append(" ");
            }
            boardString.append("\n");
        }
        StringBuilder itemBoardString = new StringBuilder();
        for (int i = 0; i < itemBoard.length; i++) {
            for (int j = 0; j < itemBoard[i].length; j++) {
                if (itemBoard[i][j] == null) {
                    itemBoardString.append("_");
                } else {
                    itemBoardString.append(itemBoard[i][j].toString());
                }
                itemBoardString.append(" ");
            }
            itemBoardString.append("\n");
        }
        itemBoardString.deleteCharAt(itemBoardString.length() - 1);
        return numMoves + "\n" +
                status + "\n" +
                ((Player) itemBoard[playerY][playerX]).getKeys() + "\n" +
                boardString.toString() +
                itemBoardString.toString();
    }
}
