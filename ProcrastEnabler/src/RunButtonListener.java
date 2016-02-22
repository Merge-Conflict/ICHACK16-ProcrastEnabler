import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by akarus on 21.2.16.
 */
public class RunButtonListener implements ActionListener {
    private SwingWorker<Void, Void> worker;
    public RunButtonListener(SwingWorker<Void, Void> worker) {
        this.worker = worker;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (GUI.calibrated) {
            worker.execute();
        }
        else {
            JOptionPane.showMessageDialog(null, "You must calibrate first.");
        }
    }
}
