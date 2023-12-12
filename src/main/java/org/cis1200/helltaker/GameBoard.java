package org.cis1200.helltaker;

/*
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */

import org.cis1200.helltaker.enums.Direction;
import org.cis1200.helltaker.enums.GameObjs;
import org.cis1200.helltaker.enums.GameStatus;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class instantiates a TicTacToe object, which is the model for the game.
 * As the user clicks the game board, the model is updated. Whenever the model
 * is updated, the game board repaints itself and updates its status JLabel to
 * reflect the current state of the model.
 * 
 * This game adheres to a Model-View-Controller design framework. This
 * framework is very effective for turn-based games. We STRONGLY
 * recommend you review these lecture slides, starting at slide 8,
 * for more details on Model-View-Controller:
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 * 
 * In a Model-View-Controller framework, GameBoard stores the model as a field
 * and acts as both the controller (with a MouseListener) and the view (with
 * its paintComponent method and the status JLabel).
 */
@SuppressWarnings("serial")
public class GameBoard extends JPanel {

    private Helltaker helltaker; // model for the game
    private JLabel status; // current status text

    // Game constants
    public static final int BOARD_WIDTH = 320;
    public static final int BOARD_HEIGHT = 320;

    /**
     * Initializes the game board.
     */
    public GameBoard(JLabel statusInit) {

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        status = statusInit; // initializes the status JLabel
        try {
            helltaker = new Helltaker("level1"); // initializes model for the game
        } catch (IOException e) {
            status.setText("Error loading level1!");
        }

        /*
         * Listens for mouseclicks. Updates the model, then updates the game
         * board based off of the updated model.
         */
        // addMouseListener(new MouseAdapter() {
        // @Override
        // public void mouseReleased(MouseEvent e) {
        // Point p = e.getPoint();
        //
        // // updates the model given the coordinates of the mouseclick
        // ttt.playTurn(p.x / 100, p.y / 100);
        //
        // updateStatus(); // updates the status JLabel
        // repaint(); // repaints the game board
        // }
        // });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                Direction direction = null;
                switch (e.getKeyCode()) {
                    case java.awt.event.KeyEvent.VK_UP:
                        direction = Direction.UP;
                        break;
                    case java.awt.event.KeyEvent.VK_DOWN:
                        direction = Direction.DOWN;
                        break;
                    case java.awt.event.KeyEvent.VK_LEFT:
                        direction = Direction.LEFT;
                        break;
                    case java.awt.event.KeyEvent.VK_RIGHT:
                        direction = Direction.RIGHT;
                        break;
                    case KeyEvent.VK_W:
                        direction = Direction.UP;
                        break;
                    case KeyEvent.VK_S:
                        direction = Direction.DOWN;
                        break;
                    case KeyEvent.VK_A:
                        direction = Direction.LEFT;
                        break;
                    case KeyEvent.VK_D:
                        direction = Direction.RIGHT;
                        break;
                    default:
                        break;
                }
                if (direction != null) {
                    helltaker.playerMove(direction);
                }
                updateStatus();
                repaint();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });
    }

    public void save(String fileName) {
        try {
            LevelIO.writeLevel(helltaker, fileName);
            updateStatus();
            status.setText(status.getText() + " | Saved!");
        } catch (IOException e) {
            status.setText("Error saving file");
        }
        requestFocusInWindow();
    }

    public void load(String fileName) {
        try {
            helltaker = new Helltaker(fileName);
            updateStatus();
            status.setText(status.getText() + " | Loaded!");
            repaint();
        } catch (IOException e) {
            status.setText("Error loading file");
        }
        requestFocusInWindow();
    }

    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    private void updateStatus() {
        GameStatus gs = helltaker.getStatus();
        switch (gs) {
            case LOST -> status.setText("You lost!");
            case WON -> status.setText("You won!");
            case PLAYING -> status.setText("Moves left: " + helltaker.getNumMoves());
            default -> status.setText("Error");
        }
    }

    /**
     * Draws the game board.
     * 
     * There are many ways to draw a game board. This approach
     * will not be sufficient for most games, because it is not
     * modular. All of the logic for drawing the game board is
     * in this method, and it does not take advantage of helper
     * methods. Consider breaking up your paintComponent logic
     * into multiple methods or classes, like Mushroom of Doom.
     */

    public static Image enumToImage(GameObjs gameObjs) {
        BufferedImage img = null;
        try {
            img = ImageIO
                    .read(new File("files" + File.separator + gameObjs.getImageName() + ".png"));
        } catch (IOException e) {
            System.out.println("Image file(s) not found!");
            e.printStackTrace();
        }
        return img;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        GameObjs[][] board = helltaker.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                g.drawImage(enumToImage(board[i][j]), j * 32, i * 32, null);
            }
        }
        GameObjs[][] itemBoard = helltaker.getItemBoard();
        for (int i = 0; i < itemBoard.length; i++) {
            for (int j = 0; j < itemBoard[i].length; j++) {
                if (itemBoard[i][j] != null) {
                    g.drawImage(enumToImage(itemBoard[i][j]), j * 32, i * 32, null);
                }
            }
        }
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}
