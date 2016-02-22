import org.opencv.core.Core;

/**
 * Created by akarus on 20.2.16.
 */
public class Main {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        EyeDetection e = new EyeDetection();
        CameraFeed cf = new CameraFeed();
        Calibrator calibrator = new Calibrator();
        //calibrator.calibration(e);
        PatternDatabase.initialize();
        cf.getFrames(e, calibrator);
    }
}
