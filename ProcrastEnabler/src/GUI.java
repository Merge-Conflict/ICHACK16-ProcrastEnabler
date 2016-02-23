import org.opencv.core.Core;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;


/**
 * Created by akarus on 21.2.16.
 */

public class GUI {

    public static CameraFeed cf;
    public static EyeDetection e;
    public static Calibrator calibrator;
    public static boolean calibrated;

    public static void main(String[] args) {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    PatternDatabase.initialize();

    cf = new CameraFeed();
    cf.initialize();
    e = new EyeDetection();
    calibrator = new Calibrator();

    JFrame frame = new JFrame("Blink");

    frame.setSize(600, 800);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setBackground(new Color(120, 120, 120));
    frame.add(panel);
    placeComponents(panel);

    frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        final JLabel userLabel = new JLabel(new ImageIcon("thi.png"));
        userLabel.setBounds(60, 60, 480, 480);
        panel.add(userLabel);

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                for(int i = 0; i < 1000; i++) {
                    cf.getFrames(GUI.e, GUI.calibrator);
                    userLabel.setIcon( new ImageIcon(ImageIO.read( new File("thi.png") ) ) );
                    Thread.sleep(200);
                }
                return null;
            }
        };

        JButton runButton = new JButton("Run");
        runButton.setBounds(20, 580, 120, 25);
        runButton.addActionListener(new RunButtonListener(worker));
        panel.add(runButton);

        JButton stopButton = new JButton("Stop");
        stopButton.setBounds(160, 580, 120, 25);
        stopButton.addActionListener(new StopButtonListener(worker));
        panel.add(stopButton);

        JTextArea textArea = new JTextArea("");
        textArea.setBounds(20, 620, 540, 200);
        panel.add(textArea);

        JButton addScriptButton = new JButton("Add script");
        addScriptButton.setBounds(440, 580, 120, 25);
        addScriptButton.addActionListener(new AddButtonListener(textArea));
        panel.add(addScriptButton);

        JButton calibrateButton = new JButton("Calibrate");
        calibrateButton.setBounds(300, 580, 120, 25);
        calibrateButton.addActionListener(new CalibrateButtonListener());
        panel.add(calibrateButton);
    }
}



