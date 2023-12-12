package org.cis1200.helltaker;

import org.cis1200.helltaker.enums.*;
import org.cis1200.helltaker.objects.Floor;
import org.cis1200.helltaker.objects.Lock;
import org.cis1200.helltaker.objects.Player;
import org.cis1200.helltaker.objects.Rock;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

public class HelltakerTest {
    public static final String LEVEL1 = "23\n" +
            "PLAYING\n" +
            "0\n" +
            "W W W W W W W W W W \n" +
            "W W W W W W W W W W \n" +
            "W W W W W f f W W W \n" +
            "W W f f f f f W W W \n" +
            "W W f f f f W W W W \n" +
            "W f f W W W W W W W \n" +
            "W f f f f f f W W W \n" +
            "W f f f f f g W W W \n" +
            "W W W W W W W W W W \n" +
            "W W W W W W W W W W \n" +
            "_ _ _ _ _ _ _ _ _ _ \n" +
            "_ _ _ _ _ _ _ _ _ _ \n" +
            "_ _ _ _ _ _ P _ _ _ \n" +
            "_ _ _ _ E _ _ _ _ _ \n" +
            "_ _ _ E _ E _ _ _ _ \n" +
            "_ _ _ _ _ _ _ _ _ _ \n" +
            "_ _ R _ _ R _ _ _ _ \n" +
            "_ _ R _ R _ _ _ _ _ \n" +
            "_ _ _ _ _ _ _ _ _ _ \n" +
            "_ _ _ _ _ _ _ _ _ _ ";

    @Test
    public void testConstructor() throws IOException {
        Reader reader = new StringReader(LEVEL1);
        Helltaker helltaker = new Helltaker(reader);
        assertEquals(LEVEL1, helltaker.toString());
        assertEquals(GameStatus.PLAYING, helltaker.getStatus());
        assertEquals(23, helltaker.getNumMoves());
    }

    @Test
    public void testGetBoardEncapsulation() throws IOException {
        Reader reader = new StringReader(LEVEL1);
        Helltaker helltaker = new Helltaker(reader);
        GameObjs[][] board = helltaker.getBoard();
        board[0][0] = null;
        assertEquals(LEVEL1, helltaker.toString());
        assertNotEquals(board, helltaker.getBoard());
    }

    @Test
    public void testGetItemBoardEncapsulation() throws IOException {
        Reader reader = new StringReader(LEVEL1);
        Helltaker helltaker = new Helltaker(reader);
        GameObjs[][] itemBoard = helltaker.getItemBoard();
        itemBoard[0][0] = null;
        assertEquals(LEVEL1, helltaker.toString());
        assertNotEquals(itemBoard, helltaker.getItemBoard());
    }

    @Test
    public void testPlayerMoveOutOfMoves() throws IOException {
        Reader reader = new StringReader(
                "0\n" +
                        "PLAYING\n" +
                        "0\n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W f f W W W \n" +
                        "W W f f f f f W W W \n" +
                        "W W f f f f W W W W \n" +
                        "W f f W W W W W W W \n" +
                        "W f f f f f f W W W \n" +
                        "W f f f f f g W W W \n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W W W W W W \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ P _ _ _ \n" +
                        "_ _ _ _ E _ _ _ _ _ \n" +
                        "_ _ _ E _ E _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ R _ _ R _ _ _ _ \n" +
                        "_ _ R _ R _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ "
        );
        Helltaker helltaker = new Helltaker(reader);
        helltaker.playerMove(Direction.LEFT);
        assertEquals(GameStatus.LOST, helltaker.getStatus());
    }

    @Test
    public void testPlayerMoveAlmostEnoughMoves() throws IOException {
        Reader reader = new StringReader(
                "0\n" +
                        "PLAYING\n" +
                        "0\n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W f f W W W \n" +
                        "W W f f f f f W W W \n" +
                        "W W f f f f W W W W \n" +
                        "W f f W W W W W W W \n" +
                        "W f f f f f f W W W \n" +
                        "W f f f f f g W W W \n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W W W W W W \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ E _ _ _ _ _ \n" +
                        "_ _ _ E _ E _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ R _ _ R _ _ _ _ \n" +
                        "_ _ R _ R P _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ "
        );
        Helltaker helltaker = new Helltaker(reader);
        helltaker.playerMove(Direction.RIGHT);
        assertEquals(GameStatus.LOST, helltaker.getStatus());
    }

    @Test
    public void testPlayerMoveAllDirections() throws IOException {
        Reader reader = new StringReader(
                "23\n" +
                        "PLAYING\n" +
                        "0\n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W f f W W W \n" +
                        "W W f f f f f W W W \n" +
                        "W W f f f f W W W W \n" +
                        "W f f W W W W W W W \n" +
                        "W f f f f f f W W W \n" +
                        "W f f f f f g W W W \n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W W W W W W \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ E _ _ _ _ _ \n" +
                        "_ _ _ E _ E _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ P _ _ R _ _ _ _ \n" +
                        "_ _ _ _ R _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ "
        );
        Helltaker helltaker = new Helltaker(reader);
        assertEquals(Items.PLAYER, helltaker.getItemBoard()[6][2]);
        helltaker.playerMove(Direction.RIGHT);
        assertEquals(Items.PLAYER, helltaker.getItemBoard()[6][3]);
        helltaker.playerMove(Direction.LEFT);
        assertEquals(Items.PLAYER, helltaker.getItemBoard()[6][2]);
        helltaker.playerMove(Direction.DOWN);
        assertEquals(Items.PLAYER, helltaker.getItemBoard()[7][2]);
        helltaker.playerMove(Direction.UP);
        assertEquals(Items.PLAYER, helltaker.getItemBoard()[6][2]);
    }

    @Test
    public void testPlayerMoveIntoWall() throws IOException {
        Reader reader = new StringReader(LEVEL1);
        Helltaker helltaker = new Helltaker(reader);
        helltaker.playerMove(Direction.UP);
        assertEquals(LEVEL1, helltaker.toString());
    }

    @Test
    public void testPlayerKickRock() throws IOException {
        Reader reader = new StringReader(
                "23\n" +
                        "PLAYING\n" +
                        "0\n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W f f W W W \n" +
                        "W W f f f f f W W W \n" +
                        "W W f f f f W W W W \n" +
                        "W f f W W W W W W W \n" +
                        "W f f f f f f W W W \n" +
                        "W f f f f f g W W W \n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W W W W W W \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ E _ _ _ _ _ \n" +
                        "_ _ _ E _ E _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ P R _ R _ _ _ _ \n" +
                        "_ _ R _ R E _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ "
        );
        Helltaker helltaker = new Helltaker(reader);
        helltaker.playerMove(Direction.RIGHT);
        assertEquals(Items.PLAYER, helltaker.getItemBoard()[6][2]);
        assertEquals(Items.ROCK, helltaker.getItemBoard()[6][4]);
        assertEquals(22, helltaker.getNumMoves());
        helltaker.playerMove(Direction.DOWN);
        assertEquals(Items.PLAYER, helltaker.getItemBoard()[6][2]);
        assertEquals(Items.ROCK, helltaker.getItemBoard()[7][2]);
        assertEquals(21, helltaker.getNumMoves());
        helltaker.playerMove(Direction.RIGHT);
        helltaker.playerMove(Direction.RIGHT);
        assertEquals(Items.PLAYER, helltaker.getItemBoard()[6][3]);
        assertEquals(Items.ROCK, helltaker.getItemBoard()[6][4]);
        assertEquals(Items.ROCK, helltaker.getItemBoard()[6][5]);
        assertEquals(19, helltaker.getNumMoves());
        helltaker.playerMove(Direction.DOWN);
        helltaker.playerMove(Direction.RIGHT);
        assertEquals(Items.PLAYER, helltaker.getItemBoard()[7][3]);
        assertEquals(Items.ROCK, helltaker.getItemBoard()[7][4]);
        assertEquals(Items.ENEMY, helltaker.getItemBoard()[7][5]);
        assertEquals(17, helltaker.getNumMoves());
    }

    @Test
    public void testPlayerKickEnemy() throws IOException {
        Reader reader = new StringReader(
                "23\n" +
                        "PLAYING\n" +
                        "0\n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W f f W W W \n" +
                        "W W f f f f f W W W \n" +
                        "W W f f f f W W W W \n" +
                        "W f f W W W W W W W \n" +
                        "W f f f f f f W W W \n" +
                        "W f f f f f g W W W \n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W W W W W W \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ E _ _ _ _ _ \n" +
                        "_ _ _ E _ E _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ P E _ E _ _ _ _ \n" +
                        "_ _ E _ E R _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ "
        );
        Helltaker helltaker = new Helltaker(reader);
        helltaker.playerMove(Direction.RIGHT);
        assertEquals(Items.PLAYER, helltaker.getItemBoard()[6][2]);
        assertNull(helltaker.getItemBoard()[6][3]);
        assertEquals(22, helltaker.getNumMoves());
        helltaker.playerMove(Direction.DOWN);
        assertEquals(Items.PLAYER, helltaker.getItemBoard()[6][2]);
        assertNull(helltaker.getItemBoard()[7][2]);
        assertEquals(21, helltaker.getNumMoves());
        helltaker.playerMove(Direction.RIGHT);
        helltaker.playerMove(Direction.RIGHT);
        assertEquals(Items.PLAYER, helltaker.getItemBoard()[6][3]);
        assertNull(helltaker.getItemBoard()[6][4]);
        assertEquals(Items.ENEMY, helltaker.getItemBoard()[6][5]);
        assertEquals(19, helltaker.getNumMoves());
        helltaker.playerMove(Direction.DOWN);
        helltaker.playerMove(Direction.RIGHT);
        assertEquals(Items.PLAYER, helltaker.getItemBoard()[7][3]);
        assertNull(helltaker.getItemBoard()[7][4]);
        assertEquals(Items.ROCK, helltaker.getItemBoard()[7][5]);
        assertEquals(17, helltaker.getNumMoves());
    }

    @Test
    public void testPlayerMoveLockKey() throws IOException {
        Reader reader = new StringReader(
                "100\n" +
                        "PLAYING\n" +
                        "0\n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W W W W W W \n" +
                        "W W W W f f f W W W \n" +
                        "W W f W f f f W W W \n" +
                        "W W f W k f f W W W \n" +
                        "W f f W W W W W W W \n" +
                        "W f f f f f f W W W \n" +
                        "W f f f f f g W W W \n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W W W W W W \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ L _ _ _ _ \n" +
                        "_ _ _ _ _ E _ _ _ _ \n" +
                        "_ _ _ _ _ P _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ R _ _ R _ _ _ _ \n" +
                        "_ _ R _ R _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ "
        );
        Helltaker helltaker = new Helltaker(reader);
        helltaker.playerMove(Direction.UP);
        assertEquals(Items.PLAYER, helltaker.getItemBoard()[4][5]);
        assertEquals(Items.LOCK, helltaker.getItemBoard()[2][5]);
        assertNull(helltaker.getItemBoard()[3][5]);
        assertEquals(99, helltaker.getNumMoves());
        helltaker.playerMove(Direction.LEFT);
        assertEquals("1", helltaker.toString().split("\n")[2]);
        assertEquals(Items.PLAYER, helltaker.getItemBoard()[4][4]);
        assertEquals(Tiles.FLOOR, helltaker.getBoard()[4][4]);
        assertEquals(98, helltaker.getNumMoves());
        helltaker.playerMove(Direction.RIGHT);
        helltaker.playerMove(Direction.UP);
        helltaker.playerMove(Direction.UP);
        assertEquals("0", helltaker.toString().split("\n")[2]);
        assertEquals(Items.PLAYER, helltaker.getItemBoard()[2][5]);
        assertEquals(95, helltaker.getNumMoves());
    }

    @Test
    public void testRockIntoLockAndKickLock() {
        Player player = new Player(0);
        Floor floor = new Floor();
        Lock lock = new Lock();
        Rock rock = new Rock();
        assertEquals(Collision.STOP, rock.collide(player, floor, lock));
        assertEquals(Collision.STOP, lock.collide(player, floor, null));
    }

    @Test
    public void testSpike() throws IOException {
        Reader reader = new StringReader(
                "100\n" +
                        "PLAYING\n" +
                        "0\n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W f f W W W \n" +
                        "W W f f f f f W W W \n" +
                        "W W f f f f W W W W \n" +
                        "W f f W W W W W W W \n" +
                        "W S f S S f S W W W \n" +
                        "W f f f f f g W W W \n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W W W W W W \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ E _ _ _ _ _ \n" +
                        "_ _ _ E _ E _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ P _ _ E _ _ _ _ \n" +
                        "_ _ E _ E R _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ "
        );
        Helltaker helltaker = new Helltaker(reader);
        helltaker.playerMove(Direction.LEFT);
        helltaker.playerMove(Direction.RIGHT);
        assertEquals(97, helltaker.getNumMoves());
        helltaker.playerMove(Direction.RIGHT);
        helltaker.playerMove(Direction.RIGHT);
        assertEquals(93, helltaker.getNumMoves());
        helltaker.playerMove(Direction.RIGHT);
        assertEquals(91, helltaker.getNumMoves());
        assertNull(helltaker.getItemBoard()[6][5]);
        assertNull(helltaker.getItemBoard()[6][6]);
    }

    @Test
    public void testAltSpike() throws IOException {
        Reader reader = new StringReader(
                "100\n" +
                        "PLAYING\n" +
                        "0\n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W f f W W W \n" +
                        "W W f f f f f W W W \n" +
                        "W W f f f f W W W W \n" +
                        "W f f W W W W W W W \n" +
                        "W a f a A f a W W W \n" +
                        "W f f f f f g W W W \n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W W W W W W \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ E _ _ _ _ _ \n" +
                        "_ _ _ E _ E _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ P _ _ E _ _ _ _ \n" +
                        "_ _ E _ E R _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ "
        );
        Helltaker helltaker = new Helltaker(reader);
        helltaker.playerMove(Direction.LEFT);
        helltaker.playerMove(Direction.RIGHT);
        assertEquals(97, helltaker.getNumMoves());
        helltaker.playerMove(Direction.RIGHT);
        helltaker.playerMove(Direction.RIGHT);
        assertEquals(93, helltaker.getNumMoves());
        helltaker.playerMove(Direction.RIGHT);
        assertEquals(92, helltaker.getNumMoves());
        assertNull(helltaker.getItemBoard()[6][5]);
        assertNull(helltaker.getItemBoard()[6][6]);
        helltaker.playerMove(Direction.LEFT);
        assertEquals(91, helltaker.getNumMoves());
    }

    @Test
    public void testGoalExtraMoves() throws IOException {
        Reader reader = new StringReader(
                "10\n" +
                        "PLAYING\n" +
                        "0\n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W f f W W W \n" +
                        "W W f f f f f W W W \n" +
                        "W W f f f f W W W W \n" +
                        "W f f W W W W W W W \n" +
                        "W f f f f f f W W W \n" +
                        "W f f f f f g W W W \n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W W W W W W \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ E _ _ _ _ _ \n" +
                        "_ _ _ E _ E _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ R _ _ R _ _ _ _ \n" +
                        "_ _ R _ R P _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ "
        );
        Helltaker helltaker = new Helltaker(reader);
        helltaker.playerMove(Direction.RIGHT);
        assertEquals(GameStatus.WON, helltaker.getStatus());
    }

    @Test
    public void testGoalLastMove() throws IOException {
        Reader reader = new StringReader(
                "1\n" +
                        "PLAYING\n" +
                        "0\n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W f f W W W \n" +
                        "W W f f f f f W W W \n" +
                        "W W f f f f W W W W \n" +
                        "W f f W W W W W W W \n" +
                        "W f f f f f f W W W \n" +
                        "W f f f f f g W W W \n" +
                        "W W W W W W W W W W \n" +
                        "W W W W W W W W W W \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ E _ _ _ _ _ \n" +
                        "_ _ _ E _ E _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ R _ _ R _ _ _ _ \n" +
                        "_ _ R _ R P _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ \n" +
                        "_ _ _ _ _ _ _ _ _ _ "
        );
        Helltaker helltaker = new Helltaker(reader);
        helltaker.playerMove(Direction.RIGHT);
        assertEquals(GameStatus.WON, helltaker.getStatus());
    }
}
