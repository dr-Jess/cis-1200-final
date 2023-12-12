package org.cis1200.helltaker;

/*
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * This class sets up the top-level frame and widgets for the GUI.
 * 
 * This game adheres to a Model-View-Controller design framework. This
 * framework is very effective for turn-based games. We STRONGLY
 * recommend you review these lecture slides, starting at slide 8,
 * for more details on Model-View-Controller:
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 * 
 * In a Model-View-Controller framework, Game initializes the view,
 * implements a bit of controller functionality through the reset
 * button, and then instantiates a GameBoard. The GameBoard will
 * handle the rest of the game's view and controller functionality, and
 * it will instantiate a TicTacToe object to serve as the game's model.
 */
public class RunHelltaker implements Runnable {
    public void run() {
        // NOTE: the 'final' keyword denotes immutability even for local variables.

        // Top-level frame in which game components live
        final JFrame frame = new JFrame("Helltaker");
        frame.setLocation(320, 320);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Setting up...");
        status_panel.add(status);

        // Game board
        final GameBoard board = new GameBoard(status);
        frame.add(board, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        control_panel.setLayout(new BoxLayout(control_panel, BoxLayout.X_AXIS));

        // Note here that when we add an action listener to the reset button, we
        // define it as an anonymous inner class that is an instance of
        // ActionListener with its actionPerformed() method overridden. When the
        // button is pressed, actionPerformed() will be called.
        final JTextField fileName = new JTextField("Enter file name");
        fileName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                fileName.setText("");
                fileName.removeFocusListener(this);
            }
        });
        final JButton save = new JButton("Save");
        save.addActionListener(e -> board.save(fileName.getText()));
        control_panel.add(save);

        control_panel.add(fileName);

        final JButton load = new JButton("Load");
        load.addActionListener(e -> board.load(fileName.getText()));
        control_panel.add(load);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JOptionPane.showMessageDialog(
                frame, "Welcome to Helltaker!\n" +
                        "Use the arrow keys or WASD to move around.\n" +
                        "Your goal is to move the player to the green exits within the number" +
                        "of moves allowed.\n"
                        +
                        "You can pick up keys to unlock locked tiles, " +
                        "and you can kick enemies/boxes around.\n"
                        +
                        "Kick an enemy into something solid, and it will disappear. " +
                        "Also, watch out for spikes (they'll remove an extra turn). \n"
                        +
                        "Use the control panel to input filenames in the text field " +
                        "and save/load your game.\n"
                        +
                        "level1, level2, ... level8 are preloaded level files.\n" +
                        "Have fun!",
                "Welcome to Helltaker!", JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon("files" + java.io.File.separator + "player.png")
        );

        // Start the game
        board.load("level1");
    }
}