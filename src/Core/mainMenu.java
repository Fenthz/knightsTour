package Core;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class mainMenu extends inGame{

    private JFrame frame;
    private JButton play, instruct, easy, medium, hard, back, quit;
    private JLabel title, diff, how;
    private JPanel text;
    private JTextPane inst;

    public mainMenu() {
        frame = new JFrame("Knight's Tour");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 550);
        frame.setResizable(false);
        frame.setLayout(null);

        title = new JLabel("Welcome to the Knight's Tour Game!");
        title.setBounds(150, 0, 500, 150);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(title);

        diff = new JLabel("Select Difficulty");
        diff.setBounds(265, 0, 500, 150);
        diff.setFont(new Font("Arial", Font.BOLD, 24));
        diff.setVisible(false);
        frame.add(diff);

        how = new JLabel("How to Play");
        how.setBounds(285, 0, 500, 100);
        how.setFont(new Font("Arial", Font.BOLD, 24));
        how.setVisible(false);
        frame.add(how);

        play = new JButton("Play");
        play.setBounds(250, 200, 200, 50);
        frame.add(play);

        instruct = new JButton("Instructions");
        instruct.setBounds(250, 270, 200, 50);
        frame.add(instruct);

        quit = new JButton("Quit");
        quit.setBounds(250, 340, 200, 50);
        quit.setVisible(true);
        frame.add(quit);

        text = new JPanel();
        text.setBounds(70, 80, 550, 300);
        text.setBackground(Color.LIGHT_GRAY);
        text.setVisible(false);
        frame.add(text);

        inst = new JTextPane();
        inst.setText("In a 2-player version of the Knight's Tour game, players take turns moving a knight on a chessboard following the standard L-shaped move. "
                + "The goal is to visit as many unique squares as possible without repeating any. Each player makes one move per turn, and once a square has been visited, "
                + "it cannot be used again. The game continues until no legal moves remain. The player who made the last valid move wins the game, encouraging both strategy "
                + "and spatial thinking with every turn.");

        inst.setEditable(false);
        inst.setBackground(Color.LIGHT_GRAY);
        inst.setFont(new Font("Arial", Font.PLAIN, 19));

        StyledDocument doc = inst.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        text.setLayout(new BorderLayout());
        text.add(inst, BorderLayout.CENTER);

        easy = new JButton("Easy");
        easy.setBounds(250, 160, 200, 50);
        easy.setVisible(false);
        frame.add(easy);

        medium = new JButton("Intermediate");
        medium.setBounds(250, 230, 200, 50);
        medium.setVisible(false);
        frame.add(medium);

        hard = new JButton("Hard");
        hard.setBounds(250, 300, 200, 50);
        hard.setVisible(false);
        frame.add(hard);

        back = new JButton("Back");
        back.setBounds(300, 400, 100, 30);
        back.setVisible(false);
        frame.add(back);


        play.addActionListener(e -> {
            play.setVisible(false);
            instruct.setVisible(false);
            title.setVisible(false);
            diff.setVisible(true);

            easy.setVisible(true);
            medium.setVisible(true);
            hard.setVisible(true);
            back.setVisible(true);
            text.setVisible(false);
            how.setVisible(false);

            quit.setVisible(false);

        });

        back.addActionListener(e -> {
            play.setVisible(true);
            instruct.setVisible(true);
            title.setVisible(true);
            diff.setVisible(false);

            easy.setVisible(false);
            medium.setVisible(false);
            hard.setVisible(false);
            back.setVisible(false);
            text.setVisible(false);
            how.setVisible(false);
            quit.setVisible(true);
        });

        instruct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                play.setVisible(false);
                instruct.setVisible(false);
                title.setVisible(false);
                diff.setVisible(false);

                easy.setVisible(false);
                medium.setVisible(false);
                hard.setVisible(false);
                back.setVisible(true);
                text.setVisible(true);
                how.setVisible(true);
                quit.setVisible(false);
            }
        });
        quit.addActionListener(e -> System.exit(0));

        easy.addActionListener(e -> {
            frame.dispose();
            Main app = new Main();
            app.initialize();
            app.difficultyPicked("Easy");
        });
        medium.addActionListener(e -> {
            frame.dispose();
            Main app = new Main();
            app.initialize();
            app.difficultyPicked("Intermediate");
        });
        hard.addActionListener(e -> {
            frame.dispose();
            Main app = new Main();
            app.initialize();
            app.difficultyPicked("HARD");
        });

        frame.setVisible(true);
    }
}
