package module6;

/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/


import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;


public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if (this.x == that.x && this.y == that.y) {
            return Double.NEGATIVE_INFINITY;
        } else if (this.x == that.x) {
            return Double.POSITIVE_INFINITY;
        } else if (this.y == that.y) {
            return +0.0;
        } else {
            return 1.0 * (that.y - this.y) / (that.x - this.x);
        }
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        if (that == null) throw new NullPointerException();
        if (this.y < that.y) {
            return -1;
        } else if (this.y > that.y) {
            return 1;
        } else if (this.x < that.x) {
                return -1;
        } else if (this.x > that.x) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if (p1 == null || p2 == null) throw new NullPointerException();
                double slopeDiff = slopeTo(p1) - slopeTo(p2);
                if (slopeDiff > 0) {
                    return 1;
                } else if (slopeDiff < 0) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
    }

    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        Point p0 = new Point(0, 0);
        Point p1 = new Point(0, 1);
        Point p2 = new Point(1, 0);
        Point p3 = new Point(1, 1);
        Point p4 = new Point(0, -1);
        Point p5 = new Point(-1, 0);
        Point p6 = new Point(-1, 1);
        Point p7 = new Point(0, 0);

        assert(p0.slopeTo(p3) == 1.0);
        assert(p0.slopeTo(p6) == -1.0);
        assert(p0.slopeTo(p0) == Double.NEGATIVE_INFINITY);
        assert(p0.slopeTo(p1) == Double.POSITIVE_INFINITY);
        assert(p0.slopeTo(p2) == +0.0);

        assert(p0.compareTo(p1) < 0);
        assert(p0.compareTo(p4) > 0);
        assert(p0.compareTo(p2) < 0);
        assert(p0.compareTo(p5) > 0);
        assert(p0.compareTo(p7) == 0);

        Comparator<Point> comparator = p0.slopeOrder();
        assert(comparator.compare(p3, p6) > 0);
        assert(comparator.compare(p6, p3) < 0);
        assert(comparator.compare(p1, p3) > 0);
        assert(comparator.compare(p2, p3) < 0);

        // drawing example
        StdDraw.setXscale(0, 30);
        StdDraw.setYscale(0, 30);
        StdDraw.setPenRadius(0.010);

        Point dp0 = new Point(1, 1);
        Point dp1 = new Point(15, 15);
        Point dp2 = new Point(10, 5);

        StdDraw.setPenColor(StdDraw. MAGENTA);
        dp0.drawTo(dp1);
        dp0.drawTo(dp2);

        StdDraw.setPenColor(StdDraw. BLUE);
        dp0.draw();
        dp1.draw();
        dp2.draw();
    }
}