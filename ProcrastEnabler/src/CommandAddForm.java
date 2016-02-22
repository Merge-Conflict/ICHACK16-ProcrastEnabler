import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by akarus on 20.2.16.
 */
public class CommandAddForm {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Command editor");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {

        panel.setLayout(null);

        JLabel scriptLabel = new JLabel("Enter new command:");
        scriptLabel.setBounds(10, 10, 200, 25);
        panel.add(scriptLabel);
        JTextArea userText = new JTextArea("");
        userText.setBounds(10, 40, 200, 150);
        panel.add(userText);
        JButton newScriptButton = new JButton("Add new command");
        newScriptButton.setBounds(10, 200, 200, 25);
        panel.add(newScriptButton);

        /*newScriptButton.addActionListener(actionEvent -> {
            if (userText.getText().equals("")) { return; }

            List<EyeStatus> eyes = new ArrayList<>();
            eyes.add(new EyeStatus(true, true, new Date()));
            eyes.add(new EyeStatus(true, false, new Date(new Date().getTime() + 500)));
            eyes.add(new EyeStatus(true, true, new Date(new Date().getTime() + 900)));

            PatternDatabase.writeNewPattern(eyes, userText.getText());
            JOptionPane.showMessageDialog(null, "Command added");
            userText.setText("");
        });*/
    }

}
