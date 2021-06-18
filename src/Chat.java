import javax.swing.*;
import javax.swing.event.AncestorListener;
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

    public Chat() {
        JFrame frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenu menuHelp = new JMenu("Help");
        menuBar.add(menuFile);
        menuBar.add(menuHelp);
        JMenuItem optionOpen = new JMenuItem("Open");
        JMenuItem optionSaveAs = new JMenuItem("Save as");
        optionOpen.addActionListener(this);
        optionSaveAs.addActionListener(this);
        menuFile.add(optionOpen);
        menuFile.add(optionSaveAs);

        JTextArea textArea = new JTextArea();

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter Text");
        JTextField textField = new JTextField(10);
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");
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
        if (event.getActionCommand() == "Open") {
            int returnVal = fileChooser.showOpenDialog(Chat.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                System.out.println(file.getName());
            }
        } else if (event.getActionCommand() == "Save as") {
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
