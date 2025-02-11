// Name:Wu Yadong
// USC NetID:yadongwu
// CS 455 PA1
// Spring 2025
import java.awt.geom.Line2D;
import java.awt.Point;

/**
 class SpiralGenerator

 Generates a "rectangular" spiral, using Java display coordinate system, moving
 outward from the center of the spiral in a counter-clockwise direction.
 That is, it will head rightward on screen, then, upward, then left, then down, etc.

 Length of initial line is unitLength.
 Padding between "layers" of the spiral is also unitLength.
 */
public class SpiralGenerator {

    private static final int RIGHT = 0;
    private static final int UP = 1;
    private static final int LEFT = 2;
    private static final int DOWN = 3;
    private static final int TOTAL_DIRECTIONS = 4;


    private Point currentPosition;
    private int currentSegmentLength;
    private int direction;
    private int unitLength;
    // # steps taken
    private int stepCount;


    /**
     Creates a SpiralGenerator starting at startPosition, with length of first segnment and
     distance between "layers" both given as unitLength.  Both values are assuming the Java
     graphics coordinate system.
     @param startPosition starting point of the first segment in the spiral
     @param unitLength in pixels, must be > 0
     */
    public SpiralGenerator(Point startPosition, int unitLength) {
        this.currentPosition = new Point(startPosition.x, startPosition.y);
        this.unitLength = unitLength;
        this.currentSegmentLength = unitLength;
        this.direction = RIGHT;

        // # steps taken
        this.stepCount = 0;
    }

    /**
     Return the coordinates of the next line segment in the spiral.

     */


    public Line2D nextSegment() {

        // create another new point, and initialize
        Point nextPosition = new Point(currentPosition);

        // the control flow to determine the spiral direction
        if (direction == RIGHT) {
            nextPosition = new Point( currentPosition.x + currentSegmentLength , nextPosition.y); // for right
        } else if(direction == UP) {
            nextPosition = new Point(currentPosition.x, currentPosition.y - currentSegmentLength);  // for up
        }else if (direction == LEFT) {
            nextPosition = new Point(currentPosition.x - currentSegmentLength , currentPosition.y );// for left
        }else if (direction == DOWN) {
            nextPosition = new Point(currentPosition.x, currentPosition.y + currentSegmentLength );// for down
        }

        Point p1 = new Point (currentPosition.x, currentPosition.y);
        Point p2 = new Point (nextPosition.x, nextPosition.y);

        Line2D.Double segment = new Line2D.Double(p1, p2);

        // Update current position
        currentPosition = nextPosition;

        //since distance between "layers" both given as unitLength,we have to make sure the currentSegmentLength will increase unilength for each 2 steps
        stepCount = stepCount + 1;

        if (stepCount % 2 == 0) {
            currentSegmentLength = currentSegmentLength + unitLength;
        }
        // determine current direction to make it is counter-clockwise

        direction = (direction + 1) % TOTAL_DIRECTIONS;

        return segment;

    }
}
