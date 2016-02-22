//import org.opencv.core.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import eyeTests.Circle;
import org.opencv.core.Core;
import eyeTests.Circle;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import java.io.File;


public class CameraFeed {

    private int width = 100;
    private int height = 100;


    public VideoCapture capture = new VideoCapture();
    Videoio io = new Videoio();

    public void initialize()
    {
        if (!capture.isOpened()) {
            capture.open(0);
            System.out.println("Im in");
        }
        // Assuming USB camera is /dev/video0
        if (capture.isOpened()) {
            capture.set(io.CV_CAP_PROP_FRAME_WIDTH, width);
            capture.set(io.CV_CAP_PROP_FRAME_HEIGHT, height);
        }
    }

    public void getFrames(EyeDetection e, Calibrator c) {
        capture.open(0);
        Calculation calculation = new Calculation(c);
        List<Circle> circleList = new ArrayList<>();

        Mat img = new Mat();
        if(capture.read(img)) {
            circleList = e.eyeDetect(img);
            e.filter(circleList);
            try {
                EyeStatus stat = calculation.calculate(img, circleList);
                PatternParser.addEyeStatus(stat);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            try {
                for (Circle circle : circleList) {
                    Imgproc.circle(img, new Point(circle.getX(), circle.getY()),
                            (int)circle.getRadius(), new Scalar(255, 0, 0));
                }
            }
            catch (NullPointerException exception)
            { }
            Imgcodecs.imwrite("thi.png", img);
        }

        //foo(img, circleList);//addPatternParse
    }

    /*
    void foo(Mat img, List<Circle> circleList) {

        System.out.println("Add pattern parser");

    }
    */
}