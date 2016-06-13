import com.sun.org.apache.xml.internal.serializer.utils.SystemIDResolver;
import com.sun.org.apache.xpath.internal.SourceTree;
import eyeTests.Circle;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//I contributed to this class--
public class Calculation {

    private Mat openEyesMat;
    private List<Circle> openEyesList;
    private Mat closedEyesMat;
    private List<Circle> closedEyesList;


    private double mean(double[] arr) {
        double sum = 0.0;
        for(double a : arr) {
            sum += a;
        }
        sum /= arr.length;
        return sum;
    }

    private boolean isWhite(double[] rgb) {

        double r = rgb[0];
        double g = rgb[1];
        double b = rgb[2];

        if(r > 200 && g  > 200 && b > 200)
            return true;

        if(Math.abs(r - g) < 15 && Math.abs(g - b) < 15 && Math.abs(b - r) < 15)
            return true;

        return false;

    }

    //bad practice - hackathon oh well , aint got time for that
    public Calculation(Calibrator c) {
        openEyesList = new ArrayList<>(c.openEyesList);
        //openEyesMat = new Mat(c.closedEyesMat);
        openEyesMat = c.openEyesMat.clone();
        closedEyesList = new ArrayList<>(c.closedEyesList);
        //closedEyesMat = c.closedEyesMat;
        closedEyesMat = c.closedEyesMat.clone();
    }

    private int minimumRadius(Circle e1, Circle e2, Circle e3) {

        int radius;
        radius = (int)(e1.getRadius() < e2.getRadius() ? e1.getRadius() : e2.getRadius());
        radius = radius < (int)e3.getRadius() ? radius : (int)e3.getRadius();
        return radius;

    }

    public EyeStatus calculate(Mat image, List<Circle> circleList) {

        //Circle c = circleList.get(0);
        //Imgproc.circle(image, new Point(c.getX(), c.getY()), (int)c.getRadius() + 1, new Scalar(255, 0 ,0 ));
        //c = circleList.get(1);
        //Imgproc.circle(image, new Point(c.getX(), c.getY()), (int)c.getRadius() + 1, new Scalar(255, 0 ,0 ));
        //Imgcodecs.imwrite("HERE.jpg", image);

        //Imgcodecs.imwrite("1.jpg", image);
        //Imgcodecs.imwrite("2.jpg", openEyesMat);
        //Imgcodecs.imwrite("3.jpg", closedEyesMat);

        boolean left = false;
        boolean right = false;

        if(circleList.get(0) == null) {
            left = false;
        } else {
            Circle leftEye = circleList.get(0);
            Circle leftClosed = closedEyesList.get(0);
            Circle leftOpen = openEyesList.get(0);

            //System.out.println(leftEye.getX() + " " + leftEye.getY());
            //System.out.println(leftClosed.getX() + " " + leftClosed.getY());
            //System.out.println(leftOpen.getX() + " " + leftOpen.getY());
            //System.exit(0);

            int minRadius = minimumRadius(leftEye, leftOpen, leftClosed);

            int openWhiteCells = 0;
            int closedWhiteCells = 0;
            int testWhiteCells = 0;


            int x = (int)leftEye.getX() - minRadius;
            int lx = (int)leftEye.getX() + minRadius;
            int xO = (int)leftOpen.getX() - minRadius;
            int lxO = (int)leftOpen.getX() + minRadius;
            int xC = (int)leftClosed.getX() - minRadius;
            int lxC = (int)leftClosed.getX() + minRadius;

            for(; x < lx && xO < lxO && xC < lxC;){
                int y = (int)leftEye.getY() - minRadius;
                int ly = (int)leftEye.getY() + minRadius;
                int yO = (int)leftOpen.getY() - minRadius;
                int lyO = (int)leftOpen.getY() + minRadius;
                int yC = (int)leftClosed.getY() - minRadius;
                int lyC = (int)leftClosed.getY() + minRadius;
                for(; y < ly && yO < lyO && yC < lyC;) {

                    if(isWhite(openEyesMat.get(yO, xO)))
                        openWhiteCells++;
                    if(isWhite(closedEyesMat.get(yC, xC)))
                        closedWhiteCells++;
                    if(isWhite(image.get(y, x)))
                        testWhiteCells++;

                    //Imgproc.rectangle(image, new Point(x, y), new Point(x, y), new Scalar(255, 0 ,0));
                    // correct System.out.println(Arrays.toString(image.get(y, x)));
                    //System.out.println(Arrays.toString(image.get(x, y)));
                    //Imgcodecs.imwrite("new.jpg", image);
                    //System.exit(0);
                    y++; yO++; yC++;

                }
                x++; xO++; xC++;

            }
            int meanVal = (int) ((openWhiteCells + closedWhiteCells + 1) / 2);
            if(meanVal < testWhiteCells) {
                left = true;
            }
            System.out.println("LEFT " + openWhiteCells + " " + closedWhiteCells + " " + testWhiteCells);
        }

        if(circleList.get(1) == null) {
            right = false;
        } else {

            Circle rightEye = circleList.get(1);
            Circle rightClosed = closedEyesList.get(1);
            Circle rightOpen = openEyesList.get(1);
            int minRadius = minimumRadius(rightEye, rightOpen, rightClosed);

            int openWhiteCells = 0;
            int closedWhiteCells = 0;
            int testWhiteCells = 0;

            int x = (int)rightEye.getX() - minRadius;
            int lx = (int)rightEye.getX() + minRadius;
            int xO = (int)rightOpen.getX() - minRadius;
            int lxO = (int)rightOpen.getX() + minRadius;
            int xC = (int)rightClosed.getX() - minRadius;
            int lxC = (int)rightClosed.getX() + minRadius;

            for(; x < lx && xO < lxO && xC < lxC; x++, xO++, xC++){
                int y = (int)rightEye.getY() - minRadius;
                int ly = (int)rightEye.getY() + minRadius;
                int yO = (int)rightOpen.getY() - minRadius;
                int lyO = (int)rightOpen.getY() + minRadius;
                int yC = (int)rightClosed.getY() - minRadius;
                int lyC = (int)rightClosed.getY() + minRadius;
                for(; y < ly && yO < lyO && yC < lyC; y++, yO++, yC++) {

                    if(isWhite(openEyesMat.get(yO, xO)))
                        openWhiteCells++;
                    if(isWhite(closedEyesMat.get(yC, xC)))
                        closedWhiteCells++;
                    if(isWhite(image.get(y, x)))
                        testWhiteCells++;

                }

            }

            int meanVal = (int) ((openWhiteCells + closedWhiteCells) / 2) + 1;
            if(meanVal < testWhiteCells) {
                right = true;
            }
            System.out.println("RIGHT: " + openWhiteCells + " " + closedWhiteCells + " " + testWhiteCells);
        }
        //System.out.println("");
        //System.exit(0);
        EyeStatus e = new EyeStatus(left, right, new Date());
        return e;
    }






}
