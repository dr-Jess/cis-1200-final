package org.cis1200.helltaker;

import org.cis1200.helltaker.enums.Direction;
import org.cis1200.helltaker.enums.GameStatus;

import java.util.Scanner;

public class PlayTest {
    public static void main(String[] args) {
        Helltaker h = null;
        try {
            h = new Helltaker("level1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(h);
        Scanner s = new Scanner(System.in);
        while (h.getStatus() == GameStatus.PLAYING) {
            String input = s.nextLine();
            if (input.equals("w")) {
                h.playerMove(Direction.UP);
            } else if (input.equals("a")) {
                h.playerMove(Direction.LEFT);
            } else if (input.equals("s")) {
                h.playerMove(Direction.DOWN);
            } else if (input.equals("d")) {
                h.playerMove(Direction.RIGHT);
            } else if (input.equals("t")) {
                input = s.nextLine();
                try {
                    LevelIO.writeLevel(h, input);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (input.equals("y")) {
                input = s.nextLine();
                Helltaker h2 = null;
                try {
                    h2 = new Helltaker(input);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (h2 != null) {
                    h = h2;
                }
            } else {
                continue;
            }
            System.out.println(h);
        }
    }
}
