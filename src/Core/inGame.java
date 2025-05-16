package Core;import javax.swing.*;import java.awt.*;import java.awt.event.ActionEvent;import java.awt.event.*;

public class inGame implements ActionListener {

    static JComboBox pickDifficulty;
    static JPanel panelMid, panelTop;
    static JButton buttons, button1;
    static JFrame frame;
    static JTextField textField;
    static JLabel difficultyLabel, scoreLabel, label3;
    static String[] difficulty = {"Easy", "Medium", "HARD"};
    static Integer [][] array;

//    public static void main() {
//        inGame kt = new inGame();
//        kt.initialize();
//    }

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

//replace this with a 2d Array in the future
public void createGUI(int a, int b) {
    //initialize();
    panelMid.removeAll();
    panelMid.setLayout(new GridLayout(a, b)); // Use the existing panelMid
    for (int i = 0; i < a; i++) {
        for (int j = 0; j < b; j++) {
            buttons = new JButton("Button " + (i * b + j));

            panelMid.add(buttons);
            //panelMid.add(new JButton("Button " + (i * b + j))); // Add buttons to panelMid
        }
    }
    panelMid.revalidate();
    panelMid.repaint();
    if (frame != null) {
        frame.pack();
    }
}

public void difficultyPicked(String selectedDifficulty) {
    switch (selectedDifficulty) {
        case "Easy":
            createGUI(3, 6); //row, column
            break;
        case "Medium":
            createGUI(4, 8);
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

