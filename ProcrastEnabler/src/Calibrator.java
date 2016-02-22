import eyeTests.Circle;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by pani on 23/02/16.
 */
public class Calibrator {

    protected Mat openEyesMat;
    protected List<Circle> openEyesList;
    protected Mat closedEyesMat;
    protected List<Circle> closedEyesList;
    //private VideoCapture capture;
    //private Videoio io;
    private int width;
    private int height;

    private void initializeEvetything() {
        openEyesMat = new Mat();
        openEyesList = new ArrayList<>();
        closedEyesMat = new Mat();
        closedEyesList = new ArrayList<>();
        //capture = new VideoCapture();
        //io = new Videoio();
        width = 100;
        height = 100;
        //capture.open(0);
    }

    private void pressAnyKeyToContinue(String str)
    {
        JOptionPane.showMessageDialog(null, str);
        /*
        System.out.println("(Press any key to continue)");
        try
        {
            System.in.read();
        }
        catch(Exception e){}
        */
    }

    //needs improvement, not always correct
    public void calibration(VideoCapture capture, EyeDetection e) {

        initializeEvetything();
        /*
        System.out.println("Calibration time!");
        System.out.println("Open both your eyes for 2 seconds");
        */
        pressAnyKeyToContinue("Calibration time!\nOpen both your eyes for 2 seconds");

        boolean first_capture;
        first_capture = true;
        //capture.open(0); not sure may need

        /*
        if(capture.isOpened()) {
            capture.set(io.CV_CAP_PROP_FRAME_WIDTH, width);
            capture.set(io.CV_CAP_PROP_FRAME_HEIGHT, height);
        }
        */

        while(openEyesList.size() < 2) {

            if(!first_capture) {
                //System.out.println("Can you please repeat?");
                pressAnyKeyToContinue("Can you please repeat?");
            } else {
                first_capture = false;
            }

            if(capture.read(openEyesMat)) {
                openEyesList = e.eyeDetect(openEyesMat);
            }
        }
        e.filter(openEyesList);


        //System.out.println();
        pressAnyKeyToContinue("Nice, Now close your eyes for 2 seconds");
        capture.release();
        capture.open(0);
        first_capture = true;

        while(closedEyesList.size() < 2) {
            if(!capture.isOpened())
                capture.open(0);
            if(first_capture) {
                first_capture = false;
            } else {
                //System.out.println("Can you please repeat?");
                pressAnyKeyToContinue("Can you please repeat?");
            }

            if(capture.read(closedEyesMat)) {
                closedEyesList = e.eyeDetect(closedEyesMat);
            }
        }
        e.filter(closedEyesList);
        System.out.println();
        capture.release();
    }

    public void patternCapture(VideoCapture capture, EyeDetection e, int length, String cmd){
        //Scanner scanner = new Scanner(System.in);
        List<EyeStatus> eyeStatusList = new ArrayList<>();
        Calculation calculation = new Calculation(this);
        Mat frame = new Mat();
        List<Circle> circleList = new ArrayList<>();
        //System.out.println("How big will your pattern be?");
        //length = scanner.nextInt(); //excpetion
        for(int i = 0; i < length; i++) {
            //System.out.println("Do step " + (i + 1));
            pressAnyKeyToContinue("Do step " + (i + 1));
            if(capture.read(frame)) {
                circleList = e.eyeDetect(frame);
                e.filter(circleList);
                //Calculate
                eyeStatusList.add(calculation.calculate(frame, circleList));
            }
        }
        for(EyeStatus eye : eyeStatusList) {
            System.out.println(eye.getLeft() + " " + eye.getRight());
        }
        PatternDatabase.writeNewPattern(eyeStatusList, cmd);
        capture.release();
    }


}
