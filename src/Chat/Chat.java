package Chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class Chat extends Component implements ActionListener {
    private JFrame frame = new JFrame("Chat");

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuFile = new JMenu("File");
    private JMenu menuHelp = new JMenu("Help");
    private JMenuItem itemOpen = new JMenuItem("Open");
    private JMenuItem itemSaveAs = new JMenuItem("Save as");

    private JTextArea textArea = new JTextArea();

    private JPanel panel = new JPanel();

    private JLabel label = new JLabel("Enter Text");
    private JTextField textField = new JTextField(10);

    private JButton send = new JButton("Send");
    private JButton reset = new JButton("Reset");

    public Chat() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        menuBar.add(menuFile);
        menuBar.add(menuHelp);
        itemOpen.addActionListener(this);
        itemSaveAs.addActionListener(this);
        menuFile.add(itemOpen);
        menuFile.add(itemSaveAs);


        send.addActionListener(e -> textArea.append(textField.getText() + "\n"));
        reset.addActionListener(e -> textField.setText(""));
        panel.add(label);
        panel.add(textField);
        panel.add(send);
        panel.add(reset);

        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, textArea);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        final JFileChooser fileChooser = new JFileChooser();
        if (event.getActionCommand().equals("Open")) {
            int returnVal = fileChooser.showOpenDialog(Chat.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                System.out.println(file.getName());
            }
        } else if (event.getActionCommand().equals("Save as")) {
            int returnVal = fileChooser.showOpenDialog(Chat.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                saveToFile(file);
            }
        }
    }

    public void saveToFile(File file) {
        Path path = Paths.get(file.getAbsolutePath());
        try {
            Files.write(path, Collections.singleton("Hello World"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
