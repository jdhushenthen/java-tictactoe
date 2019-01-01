/*******************************************************************
 * Author:        Janahan Dhushenthen                              *
 * Date:          May 17, 2016                                     *
 * Description:   This program is a tic-tac-toe game using buttons *
 *******************************************************************/

// awt: abstract window toolkit
import java.awt.*;
import java.awt.event.*;

// swing is newer graphics package
import javax.swing.*;


/*******************************************************************
 * This class instantiates the TicTacToe class defined below to    *
 *   create a pane                                                 *
 *******************************************************************/
public class TicTacToeJanahan
{
    /***************************************************************/
    // main METHOD
    public static void main (String[] args)
    {
        TicTacToe frame = new TicTacToe();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


/*******************************************************************
 * This is the TicTacToe object class.                             *
 * This class has all of the logic and the layout/design of the    *
 *   tic-tac-toe game                                              *
 *******************************************************************/
class TicTacToe extends JFrame implements ActionListener
{
    // Declare global variables
    Font chillerFont = new Font("Chiller", Font.PLAIN, 200);
    String player = "X";
    String result;
    int answer;
    Color clr = Color.BLUE;
    JButton [][] buttons = new JButton[3][3];


    /***************************************************************/
    // actionPerformed METHOD: button actions
    public void actionPerformed (ActionEvent ae)
    {
        // Check all buttons to identify which is clicked
        for(int r = 0; r < 3; r++)
        {
            for (int c = 0; c < 3; c++)
            {
                // Disable clicked button and set it to the correct letter/colour
                if(ae.getSource() == buttons[r][c])
                {
                    buttons[r][c].setEnabled(false);
                    buttons[r][c].setFont(chillerFont);
                    buttons[r][c].setBackground(clr);
                    buttons[r][c].setText(player);
                }
            }
        }

        // Disable all buttons and end game if player wins
        if (playerWon())
        {
            result = "Player "+player+" wins!";
            for(int r = 0; r < 3; r++)
            {
                for (int c = 0; c < 3; c++)
                {
                    buttons[r][c].setEnabled(false);
                }
            }
            gameOver();
        }

        // End game if there is a tie
        else if (tieGame())
        {
            result = "Cat's game!";
            gameOver();
        }

        // Change players if game isn't over
        else
        {
            if(player.equals("X"))
            {
                player = "O";
                clr = Color.RED;
            }
            else
            {
                player = "X";
                clr = Color.BLUE;
            }
        }
    }


    /***************************************************************/
    // playerWon METHOD: check if the current player has won
    public boolean playerWon()
    {
        // Checks rows for 3 consecutive X's/O's
        for (int r = 0; r < 3; r++) {
            if(buttons[r][0].getText().equals(player) && buttons[r][1].getText().equals(player) && buttons[r][2].getText().equals(player))
                return true;
        }

        // Checks columns for 3 consecutive X's/O's
        for (int c = 0; c < 3; c++) {
            if(buttons[0][c].getText().equals(player) && buttons[1][c].getText().equals(player) && buttons[2][c].getText().equals(player))
                return true;
        }

        // Checks diagonals for 3 consecutive X's/O's
        if (buttons[0][0].getText().equals(player) && buttons[1][1].getText().equals(player) && buttons[2][2].getText().equals(player))
            return true;
        if (buttons[0][2].getText().equals(player) && buttons[1][1].getText().equals(player) && buttons[2][0].getText().equals(player))
            return true;

        return false;
    }


    /***************************************************************/
    // tieGame METHOD: check all buttons to see if they have been clicked
    public boolean tieGame()
    {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (buttons[r][c].getText().equals(""))
                    return false;
            }
        }
        return true;
    }


    /***************************************************************/
    // resetGame METHOD: make the starting player X and clear all buttons
    public void resetGame()
    {
        player = "X";
        clr = Color.BLUE;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                buttons[r][c].setText("");
                buttons[r][c].setBackground(Color.WHITE);
                buttons[r][c].setEnabled(true);
            }
        }
    }


    /***************************************************************/
    // gameOver METHOD: create a dialog box asking to play again
    public void gameOver()
    {
        answer = JOptionPane.showConfirmDialog(null, "Would you like to play again?", result, JOptionPane.YES_NO_OPTION);
        if(answer == JOptionPane.YES_OPTION)
            resetGame();
    }


    /***************************************************************/
    // CONSTRUCTOR METHOD
    public TicTacToe()
    {
        super ("Janahan Tic Tac Toe"); // JFrame title

        /////////////////////////////////////////////////////////////
        // CONTENTPANE - Create the JPanel and set the layout
        JPanel pane = (JPanel) getContentPane();
        pane.setLayout(new GridLayout(3,3,5,5)); // 3x3 grid
        pane.setDoubleBuffered(true);

        // CONTENTPANE - Create all objects for the JPanel
        for(int r = 0; r < 3; r++)
        {
            for(int c = 0; c < 3; c++)
            {
                buttons[r][c] = new JButton(); // 9 buttons
                pane.add(buttons[r][c]);
                buttons[r][c].addActionListener(this);
                buttons[r][c].setBackground(Color.WHITE);
            }
        }
        /////////////////////////////////////////////////////////////

        setSize(600, 600); // width, height
        setVisible(true);
    }
}
