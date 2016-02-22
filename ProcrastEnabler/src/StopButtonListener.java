import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by akarus on 21.2.16.
 */
public class StopButtonListener implements ActionListener {
    private SwingWorker<Void, Void> worker;
    public StopButtonListener(SwingWorker<Void, Void> worker) {
        this.worker = worker;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (!worker.isCancelled() && !worker.isDone())
        {
            worker.cancel(true);
            worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    for(int i = 0; i < 1000; i++) {
                        GUI.cf.getFrames(GUI.e, GUI.calibrator);
                        Thread.sleep(500);
                    }
                    return null;
                }
            };
            JOptionPane.showMessageDialog(null, "Camera stopped.");
        }
    }
}
