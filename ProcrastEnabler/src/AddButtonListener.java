import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by akarus on 21.2.16.
 */
public class AddButtonListener implements ActionListener {

    private JTextArea textArea;
    public AddButtonListener(JTextArea textArea) {
        this.textArea=textArea;
    }

    private void addScript(Calibrator calibrator, EyeDetection e, int length)
    {
        calibrator.patternCapture(GUI.cf.capture, e, length, textArea.getText());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (GUI.calibrated) {
            int length = Integer.parseInt(JOptionPane.showInputDialog("How long is your pattern?")); //exceptions
            addScript(GUI.calibrator, GUI.e, length);
            JOptionPane.showMessageDialog(null, "Script added.");
            PatternDatabase.initialize();
            textArea.setText("");
        }
        else {
            JOptionPane.showMessageDialog(null, "You must calibrate first.");
        }
    }
}
