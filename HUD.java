import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Battleship GUI.
 * Homework 12.
 *
 *     @author Luke Batchelder
 *     @author Jessica Diehl
 */
public class HUD {
    static Color myBattleshipColor = new Color(100,100,100);
    static Color hitBattleshipColor = new Color(255, 0, 0);
    static Color missBattleshipColor = new Color(0, 0, 255);
    static Color topGridForeground = new Color(169,169,169);
    static Color topGridBackground = new Color(210,210,210);
    static int numRows = 10;
    static int numCols = 10;
    static JLabel[][] battleSpaceGrid = new JLabel[numRows][numCols];
    static JLabel[][] battleMapGrid = new JLabel[numRows][numCols];
    static int[] currGuess = {0,0};
    static JTextField guessBox = new JTextField(5);


    /**
     * Denotes a battleship hit at the given coordinates
     * in the Space map, the player's attempted shots
     *
     * @param		x	x coordinate of shot
     * @param		y 	y coordinate of shot
     * @return			None
     *
     */
    public static void setSpaceHit(int x, int y)
    {
        battleSpaceGrid[y][x].setBackground(hitBattleshipColor);
        battleSpaceGrid[y][x].setText("");
    }


    /**
     * Denotes a battleship missed at the given coordinates
     * in the Space map, the player's attempted shots
     *
     * @param		x	x coordinate of shot
     * @param		y 	y coordinate of shot
     * @return			None
     *
     */
    public static void setSpaceMiss(int x, int y)
    {
        battleSpaceGrid[y][x].setBackground(missBattleshipColor);
        battleSpaceGrid[y][x].setText("");
    }


    /**
     * Denotes a battleship hit at the given coordinates
     * in the Map map, the opponent's attempted shots
     *
     * @param		x	x coordinate of shot
     * @param		y 	y coordinate of shot
     * @return			None
     *
     */
    public static void setMapHit(int x, int y)
    {
        battleMapGrid[y][x].setBackground(hitBattleshipColor);
        battleMapGrid[y][x].setText("");
    }


    /**
     * Denotes a battleship missed at the given coordinates
     * in the Map map, the opponent's attempted shots
     *
     * @param		x	x coordinate of shot
     * @param		y 	y coordinate of shot
     * @return			None
     *
     */
    public static void setMapMiss(int x, int y)
    {
        battleMapGrid[y][x].setBackground(missBattleshipColor);
        battleMapGrid[y][x].setText("");
    }


    /**
     * Denotes a battleship is contained at the given
     * coordinates, used on the Map map
     *
     * @param		x	x coordinate of shot
     * @param		y 	y coordinate of shot
     * @return			None
     *
     */
    public static void setBattleship(int x, int y) {
        battleMapGrid[y][x].setBackground(myBattleshipColor);
        battleMapGrid[y][x].setBorder(new LineBorder(myBattleshipColor));
    }


    /**
     * Gets the player's guess from the text box
     * and returns an integer array with the guess.
     * Also sets the static variable currGuess to the
     * player's guess. in coordinates, 0=x, 1=y.
     *
     * @return		guess	player's coordinate guess
     *
     */
    public static int[] getGuess()
    {
        int[] guess = {0,0};
        try
        {
            //get the text box data
            String input = guessBox.getText().toString();
            //split the data into a string array
            String[] strGuess = input.split(",");
            //turn the data into integer values
            guess[0] = Integer.parseInt(strGuess[0]);
            guess[1] = Integer.parseInt(strGuess[1]);
        }
        catch(Exception e)
        {
            System.out.print("Error getting data");
        }
        //set global guess value and also return the same value
        currGuess = guess;
        return guess;
    }


    /**
     * Creates player's boards and displays them in
     * a window.  Includes text box and button for
     * firing. Clicking a label in the Space map
     * will fill the text box with the coordinates.
     * Clicking the "Fire!" button will fire at those
     * coordinates or using the <enter> key will fire
     * at that location.
     *
     * @return			None
     *
     */
    public static void setupWindow() {
        //initialize frame
        JFrame frame = new JFrame("Battleship Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 800);

        //create panels
        JPanel northPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel aesteticPanel = new JPanel();
        JPanel southPanel = new JPanel();

        //create label for north
        JLabel title = new JLabel("Battleship");
        title.setFont(new Font("Serif", Font.BOLD, 36));
        northPanel.add(title);


        //create center table container
        centerPanel.setLayout(new GridLayout(2,1));
        //centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        //centerPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

        //create grid for left
        topPanel.setLayout(new GridLayout(numRows, numCols+1));
        for(int i = 0; i < numRows; i++)
        {
            for(int j = 0; j < numCols; j++)
            {
                //board that contains your attempted shots
                //battle space
                battleSpaceGrid[i][j] = new JLabel();
                battleSpaceGrid[i][j].setSize(10,10);
                battleSpaceGrid[i][j].setBorder(new LineBorder(topGridForeground));
                battleSpaceGrid[i][j].setBackground(Color.WHITE);
                battleSpaceGrid[i][j].setOpaque(true);
                battleSpaceGrid[i][j].setText(j+","+i);
                battleSpaceGrid[i][j].setForeground(Color.GRAY);
                //add mouse listener to fill text box with coordinates
                battleSpaceGrid[i][j].addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        JLabel thisLabel = (JLabel)e.getSource();
                        String text = thisLabel.getText();
                        guessBox.setText(text);
                    }

                });
                topPanel.add(battleSpaceGrid[i][j]);
            }
        }

        //create grid for right
        bottomPanel.setLayout(new GridLayout(numRows, numCols));
        for(int i = 0; i < numRows; i++)
        {
            for(int j = 0; j < numCols; j++)
            {
                //board that contains your battleships,
                //opponents's incoming hits
                //battle map
                battleMapGrid[i][j] = new JLabel();
                battleMapGrid[i][j].setSize(10,10);
                battleMapGrid[i][j].setBorder(new LineBorder(Color.BLACK));
                battleMapGrid[i][j].setBackground(topGridBackground);
                battleMapGrid[i][j].setOpaque(true);
                battleMapGrid[i][j].setText(j+","+i);
                battleMapGrid[i][j].setForeground(Color.GRAY);
                bottomPanel.add(battleMapGrid[i][j]);
            }
        }

        //add left and right grids to the center panel
        topPanel.setSize(300,300);
        bottomPanel.setSize(300,300);
        centerPanel.setSize(300,700);
        centerPanel.add(BorderLayout.NORTH, topPanel);
        centerPanel.add(BorderLayout.SOUTH, bottomPanel);

        //create text box and button for south
        JButton button1 = new JButton("Fire!");
        southPanel.add(guessBox);
        //add event listener to press enter to fire at coords
        guessBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                getGuess();
                setSpaceHit(currGuess[0], currGuess[1]);
            }
        });
        southPanel.add(button1);
        //add event listener for button to fire at coords
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                getGuess();
                setSpaceHit(currGuess[0], currGuess[1]);
            }
        });

        //add panels to frame
        frame.getContentPane().add(BorderLayout.NORTH, northPanel);
        frame.getContentPane().add(BorderLayout.CENTER, centerPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, southPanel);

        //make frame visible
        frame.setVisible(true);
    }


    /**
     * Main function calls the board setup
     *
     * @param		args	does not take cmdline args
     * @return			None
     *
     */
    public static void main (String args[]) {
        //sets up the window with the playing boards
        setupWindow();

        //press X in the top right of window to quit the game
    }
}

