package Core;import javax.swing.*;import java.awt.*;import java.awt.event.ActionEvent;import java.awt.event.*;

public class inGame implements ActionListener {

    static JComboBox pickDifficulty;
    static JPanel panelMid, panelTop;
    static JButton buttons, button1;
    static JFrame frame;
    static JTextField textField;
    static JLabel difficultyLabel, scoreLabel, label3;
    static String[] difficulty = {"Easy", "Intermediate", "HARD"};
    static Integer [][] array; //no use for now

    JButton[][] buttonGrid;
    int knightRow1 = -1, knightCol1 = -1;
    int knightRow2 = -1, knightCol2 = -1;
    boolean isPlayerOneTurn = true;

    int[] xMove = {2, 1, -1, -2, -2, -1, 1, 2};
    int[] yMove = {1, 2, 2, 1, -1, -2, -2, -1};

public void initialize(){
    JFrame frame = new JFrame("KNIGHT's Tour Problem");
    frame.setSize(800, 800);
    frame.setLayout(new BorderLayout());
    frame.setResizable(false);

    panelTop = new JPanel();
    panelTop.setLayout(new FlowLayout());
    panelMid = new JPanel();

    difficultyLabel = new JLabel("Difficulty: ");
    pickDifficulty = new JComboBox<>(difficulty);
    pickDifficulty.setSelectedIndex(0);
    button1 = new JButton("Confirm");
    button1.addActionListener(this);
    scoreLabel = new JLabel("Scores: ");
    textField = new JTextField();
    textField.setEditable(false);
    textField.setPreferredSize(new Dimension(150,40));

    panelTop.add(difficultyLabel); panelTop.add(pickDifficulty); panelTop.add(button1); panelTop.add(scoreLabel); panelTop.add(textField);
    frame.add(panelTop, BorderLayout.NORTH);
    frame.add(panelMid, BorderLayout.CENTER);
    //frame.add(button);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    System.out.println("Hello world!");
}

public void actionPerformed(ActionEvent e) {
    if(e.getSource()==button1){
        String selectedDifficulty = (String) pickDifficulty.getSelectedItem();
        System.out.println("Selected Difficulty: " + selectedDifficulty);
        //assert selectedDifficulty != null;
        difficultyPicked(selectedDifficulty); // Call difficultyPicked
    }
}

public void createGUI(int a, int b) {
    panelMid.removeAll();
    panelMid.setLayout(new GridLayout(a, b)); //use the existing panelMid

    buttonGrid = new JButton[a][b]; //initialize the button grid with correct dimensions

    for (int i = 0; i < a; i++) {
        for (int j = 0; j < b; j++) {
            JButton tile = new JButton();
            tile.addActionListener(new TileHandler(i, j));

            if ((i + j) % 2 == 0) {
                tile.setBackground(new Color(0xeeeed2)); //light tile
            } else {
                tile.setBackground(new Color(0x769656));  //dark tile
            }
            buttonGrid[i][j] = tile; //store in the 2D array
            panelMid.add(tile);
        }
    }
    panelMid.revalidate();
    panelMid.repaint();
    if (frame != null) {
        frame.pack();
    }

    //reset knight positions and player turn for a new game
    knightRow1 = -1;
    knightCol1 = -1;
    knightRow2 = -1;
    knightCol2 = -1;
    isPlayerOneTurn = true;
}


    class TileHandler implements ActionListener {
        int row, col;

        TileHandler(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent e) {
            if (isPlayerOneTurn) {
                //player 1's first move
                if (knightRow1 == -1 && knightCol1 == -1) {
                    knightRow1 = row;
                    knightCol1 = col;
                    buttonGrid[row][col].setText("Player 1 Start");
                    buttonGrid[row][col].setFont(new Font("ARIAL", Font.PLAIN, 30));
                    buttonGrid[row][col].setForeground(Color.RED);
                    buttonGrid[row][col].setEnabled(false);
                    isPlayerOneTurn = false;
                    return;
                }
                //player 1's subsequent moves
                if (isValidKnightMove(knightRow1, knightCol1, row, col) && !isCellOccupied(row, col)) {
//                    buttonGrid[knightRow1][knightCol1].setText("");
                    buttonGrid[row][col].setText("1");
                    buttonGrid[row][col].setFont(new Font("ARIAL", Font.BOLD + Font.ITALIC, 30));
                    buttonGrid[row][col].setForeground(Color.RED);
                    buttonGrid[row][col].setEnabled(false);
                    knightRow1 = row;
                    knightCol1 = col;
                    isPlayerOneTurn = false;
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Knight Move for Player 1!", "Error", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                //player 2's first move
                if (knightRow2 == -1 && knightCol2 == -1) {
                    knightRow2 = row;
                    knightCol2 = col;
                    buttonGrid[row][col].setText("Player 2 Start");
                    buttonGrid[row][col].setEnabled(false);
                    isPlayerOneTurn = true;
                    return;
                }
                //player 2's subsequent moves
                if (isValidKnightMove(knightRow2, knightCol2, row, col) && !isCellOccupied(row, col)) {
//                    buttonGrid[knightRow2][knightCol2].setText("");
                    buttonGrid[row][col].setText("2");
                    buttonGrid[row][col].setEnabled(false);
                    knightRow2 = row;
                    knightCol2 = col;
                    isPlayerOneTurn = true;
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Knight Move for Player 2!", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        private boolean isCellOccupied(int row, int col) {
            return (row == knightRow1 && col == knightCol1) || (row == knightRow2 && col == knightCol2);
        }

        public boolean isValidKnightMove(int fromRow, int fromCol, int toRow, int toCol) {
            for (int i = 0; i < 8; i++) {
                if (fromRow + xMove[i] == toRow && fromCol + yMove[i] == toCol) {
                    return true;
                }
            }
            return false;
        }
    }
    public void difficultyPicked(String selectedDifficulty) {
        switch (selectedDifficulty) {
            case "Easy":
                createGUI(6, 3); //row, column
                break;
            case "Intermediate":
                createGUI(6, 8);
                break;
            case "HARD":
                createGUI(8, 8);
                break;
            default:
                createGUI(2, 2);
                break;
        }
    }
}




