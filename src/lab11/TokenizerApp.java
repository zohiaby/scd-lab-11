package lab11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TokenizerApp {
    private JFrame frame;
    private JPanel topPanel;
    private JPanel buttonPanel;
    private JTextField verse1Field;
    private JTextField verse2Field;
    private JButton tokenizeButton;
    private JComboBox<String> rootDropdown;
    private DefaultComboBoxModel<String> rootModel;
    private DefaultListModel<String> tokenListModel;
    private JList<String> tokenList;

    private DatabaseHandler databaseHandler;

    public TokenizerApp() {
        frame = new JFrame("Verse Tokenizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        topPanel = new JPanel(new BorderLayout());

        JPanel versePanel = new JPanel();
        verse1Field = new JTextField("Enter Verse 1", 20);
        verse2Field = new JTextField("Enter Verse 2", 20);
        versePanel.add(new JLabel("Verse 1:"));
        versePanel.add(verse1Field);
        versePanel.add(new JLabel("Verse 2:"));
        versePanel.add(verse2Field);

        tokenizeButton = new JButton("Tokenize");
        topPanel.add(versePanel, BorderLayout.CENTER);
        topPanel.add(tokenizeButton, BorderLayout.SOUTH);

        buttonPanel = new JPanel(new GridLayout(2, 1));
        rootModel = new DefaultComboBoxModel<>();
        rootDropdown = new JComboBox<>(rootModel);
        rootModel.addElement("Root1");
        rootModel.addElement("Root2");
        rootModel.addElement("Root3");

        JPanel rootsPanel = new JPanel(new BorderLayout());
        rootsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        rootsPanel.add(rootDropdown, BorderLayout.CENTER);

        JButton rootsButton = new JButton("Add Root");
        rootsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rootModel.addElement((String) rootDropdown.getSelectedItem());
            }
        });

        rootsButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        buttonPanel.add(rootsPanel);
        buttonPanel.add(rootsButton);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.WEST);

        tokenListModel = new DefaultListModel<>();
        tokenList = new JList<>(tokenListModel);
        JScrollPane tokenScrollPane = new JScrollPane(tokenList);
        frame.add(tokenScrollPane, BorderLayout.CENTER);

        tokenizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tokenizeAndDisplay();
            }
        });

        databaseHandler = new DatabaseHandler();
    }

    private void tokenizeAndDisplay() {
        String verse1 = verse1Field.getText();
        String verse2 = verse2Field.getText();
        String selectedRoot = (String) rootDropdown.getSelectedItem();

        storeVersesInDatabase(verse1, verse2);

        List<String> tokens = tokenizeVerses(verse1, verse2);

        for (int i = 0; i < tokens.size(); i++) {
            tokens.set(i, selectedRoot + " " + tokens.get(i));
        }

        tokenListModel.clear();
        for (String token : tokens) {
            tokenListModel.addElement(token);
        }
    }

    private List<String> tokenizeVerses(String verse1, String verse2) {
        List<String> tokens = new ArrayList<>();
        // Implement your tokenization logic here
        // Example: tokens.add("word1");
        //         tokens.add("word2");
        return tokens;
    }

    private void storeVersesInDatabase(String verse1, String verse2) {
        databaseHandler.storeVerse(1, verse1, verse2);

        JOptionPane.showMessageDialog(frame, "Verses stored in the database successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void display() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TokenizerApp app = new TokenizerApp();
            app.display();
        });
    }
}
