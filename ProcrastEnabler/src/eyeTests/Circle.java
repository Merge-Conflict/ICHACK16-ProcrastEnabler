package eyeTests;

import org.opencv.core.Point;

/**
 * Created by pani on 20/02/16.
 */
public class Circle {

    private double x;
    private double y;
    private double radius;

    public Circle(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public Circle(Point p, double radius) {
        this.x = p.x;
        this.y = p.y;
        this.radius = radius;
    }

    public Point getPoint() {
        return new Point(x, y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
