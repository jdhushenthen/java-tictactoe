import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class TicTacToe extends JFrame implements ActionListener {
    Font chillerFont = new Font("Chiller", 0, 200);
    String player = "X";
    String result;
    int answer;
    Color clr;
    JButton[][] buttons;

    public void actionPerformed(ActionEvent ae) {
        int r;
        int c;
        for(r = 0; r < 3; ++r) {
            for(c = 0; c < 3; ++c) {
                if(ae.getSource() == this.buttons[r][c]) {
                    this.buttons[r][c].setEnabled(false);
                    this.buttons[r][c].setFont(this.chillerFont);
                    this.buttons[r][c].setBackground(this.clr);
                    this.buttons[r][c].setText(this.player);
                }
            }
        }

        if(this.playerWon()) {
            this.result = "Player " + this.player + " wins!";

            for(r = 0; r < 3; ++r) {
                for(c = 0; c < 3; ++c) {
                    this.buttons[r][c].setEnabled(false);
                }
            }

            this.gameOver();
        } else if(this.tieGame()) {
            this.result = "Cat\'s game!";
            this.gameOver();
        } else if(this.player.equals("X")) {
            this.player = "O";
            this.clr = Color.RED;
        } else {
            this.player = "X";
            this.clr = Color.BLUE;
        }

    }

    public boolean playerWon() {
        int c;
        for(c = 0; c < 3; ++c) {
            if(this.buttons[c][0].getText().equals(this.player) && this.buttons[c][1].getText().equals(this.player) && this.buttons[c][2].getText().equals(this.player)) {
                return true;
            }
        }

        for(c = 0; c < 3; ++c) {
            if(this.buttons[0][c].getText().equals(this.player) && this.buttons[1][c].getText().equals(this.player) && this.buttons[2][c].getText().equals(this.player)) {
                return true;
            }
        }

        if(this.buttons[0][0].getText().equals(this.player) && this.buttons[1][1].getText().equals(this.player) && this.buttons[2][2].getText().equals(this.player)) {
            return true;
        } else if(this.buttons[0][2].getText().equals(this.player) && this.buttons[1][1].getText().equals(this.player) && this.buttons[2][0].getText().equals(this.player)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean tieGame() {
        for(int r = 0; r < 3; ++r) {
            for(int c = 0; c < 3; ++c) {
                if(this.buttons[r][c].getText().equals("")) {
                    return false;
                }
            }
        }

        return true;
    }

    public void resetGame() {
        this.player = "X";
        this.clr = Color.BLUE;

        for(int r = 0; r < 3; ++r) {
            for(int c = 0; c < 3; ++c) {
                this.buttons[r][c].setText("");
                this.buttons[r][c].setBackground(Color.WHITE);
                this.buttons[r][c].setEnabled(true);
            }
        }

    }

    public void gameOver() {
        this.answer = JOptionPane.showConfirmDialog((Component)null, "Would you like to play again?", this.result, 0);
        if(this.answer == 0) {
            this.resetGame();
        }

    }

    public TicTacToe() {
        super("Janahan Tic Tac Toe");
        this.clr = Color.BLUE;
        this.buttons = new JButton[3][3];
        JPanel pane = (JPanel)this.getContentPane();
        pane.setLayout(new GridLayout(3, 3, 5, 5));
        pane.setDoubleBuffered(true);

        for(int r = 0; r < 3; ++r) {
            for(int c = 0; c < 3; ++c) {
                this.buttons[r][c] = new JButton();
                pane.add(this.buttons[r][c]);
                this.buttons[r][c].addActionListener(this);
                this.buttons[r][c].setBackground(Color.WHITE);
            }
        }

        this.setSize(600, 600);
        this.setVisible(true);
    }
}
