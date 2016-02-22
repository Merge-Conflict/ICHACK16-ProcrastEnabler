/**
 * Created by pani on 20/02/16.
 */
import eyeTests.Circle;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.*;
import org.opencv.core.*;

import java.util.ArrayList;
import java.util.List;

public class EyeDetection {

    private CascadeClassifier eyeDetector = new CascadeClassifier(getClass().getResource("/haarcascade_eye.xml").getPath());

    public List<Circle> eyeDetect(Mat frame) {

        List<Circle> circleList = new ArrayList<>();
        //Mat frame = Imgcodecs.imread(getClass().getResource("/eyeTests/cat2.jpg").getPath());
        MatOfRect eyeDetections = new MatOfRect();
        eyeDetector.detectMultiScale(frame, eyeDetections);//   , 1.1, 2, 0 |CV_HAAR_SCALE_IMAGE, Size(30, 30) );

        //System.out.println(eyeDetections.toArray().length);

        for (Rect rect : eyeDetections.toArray()) {
            //Imgproc.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
            Point center = new Point(rect.x + rect.width * 0.5, rect.y + rect.height * 0.5);
            int radius = (int) Math.round((rect.width + rect.height) * 0.25);
            System.out.print(radius + " ");
            circleList.add(new Circle(center, radius));
            //Imgproc.circle(frame, center, radius, new Scalar( 255, 0, 0 ), 4, 8, 0 );
        }

        /*
        String filename = "cat2.jpg";
        Imgcodecs.imwrite(filename, frame);
        */

        System.out.println("Their size is " + circleList.size());
        //filter(circleList);
        System.out.println();
        //System.out.println(circleList.size());
        return circleList;
    }

    //TODO: find better? value
    private final double THRESHOLD = 6;
    public void filter(List<Circle> circleList) {

        if(circleList.size() == 0) {
            circleList.add(null);
            circleList.add(null);
        } else if (circleList.size() == 1) {
            //TODO circleList.add(null);
            circleList.add(null);
        } else {
            Circle BiggerCircle = new Circle(0, 0, 0);
            List<Circle> resultCircles = new ArrayList<>();
            for(Circle circle : circleList) {
                if(circle.getRadius() > BiggerCircle.getRadius() + THRESHOLD) {
                    BiggerCircle = circle;
                    resultCircles = new ArrayList<>();
                    resultCircles.add(BiggerCircle);
                } else if(circle.getRadius() > BiggerCircle.getRadius() - THRESHOLD) {
                    resultCircles.add(circle);
                }
            }
        }

    }
}
        /*
        //save
        String filename = "eye8.jpg";
        Imgcodecs.imwrite(filename, frame);
        System.out.println("DONE!");

    }*/
