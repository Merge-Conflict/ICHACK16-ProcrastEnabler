import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by akarus on 21.2.16.
 */
public class CalibrateButtonListener implements ActionListener {

    protected void calibrate(Calibrator calibrator, EyeDetection e)
    {
        calibrator.calibration(GUI.cf.capture, e);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        calibrate(GUI.calibrator, GUI.e);
        GUI.calibrated = true;
        JOptionPane.showMessageDialog(null, "Calibrated successfully.");
    }
}
