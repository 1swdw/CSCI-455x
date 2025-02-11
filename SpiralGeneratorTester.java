// Name:Wu Yadong
// USC NetID:yadongwu
// CS 455 PA1
// Spring 2025
import java.awt.Point;
import java.awt.geom.Line2D;

/**
 /**
 * SpiralGeneratorTester
 *
 * This class tests the functionality of the SpiralGenerator class.The spiral moves
 * outward from a given starting point in a counter-clockwise direction.
 *
 * The first segment extends to the right, followed by an upward movement, then
 * left, then downward, and the pattern continues in this manner.
 *
 * The initial segment length is determined by the "unitLength", which also serves
 * as the padding between successive layers of the spiral.
 *
 * Invariants:
 * - unitLength/ numbers of segments must be > 0.
 * - The spiral always moves in a counterclockwise direction.
 * - Each segment must be either horizontal or vertical.
 * - Each two segments must be connected and perpendicular.
 */

public class SpiralGeneratorTester {

    // Mainly focus on cases testing
    public static void main(String[] args) {

        // Normal Cases
        spiralGeneratorTester(new Point(500, 500), 1000, 5);
        spiralGeneratorTester(new Point(300, 300), 5, 500);
        spiralGeneratorTester(new Point(200, 300), 5, 5);
        spiralGeneratorTester(new Point(300, 400), 10, 7);
        spiralGeneratorTester(new Point(500, 200), 5, 10);
        spiralGeneratorTester(new Point(0, 0), 5, 5);
        spiralGeneratorTester(new Point(100, 100), 1, 4);
        spiralGeneratorTester(new Point(Integer.MAX_VALUE, Integer.MAX_VALUE), 5, 5);

        // Zero segments
        spiralGeneratorTester(new Point(100, 100), 5, 0);

        // Negative unit length
        spiralGeneratorTester(new Point(100, 100), -5, 3);

        // Negative unitLength and  numbers of segments
        spiralGeneratorTester(new Point(100, 100), 5, -3);

    }
    /**
     * This method initializes a SpiralGenerator with a given starting point, segment length,
     * and number of segments. It makes itration to check each step for correctness based on
     * connectivity, orientation, positive values of "unitLength", positive values of "numberOfSegments"
     * and perpendicularity.
     */

    private static void spiralGeneratorTester (Point startPoint, int unitLength, int numberOfSegments) {

        // if the initial segment length and the number of segments must be <= 0, procedure will be stopped
        if (unitLength <= 0 || numberOfSegments <= 0) {
            System.out.println("\nERROR: Both the initial segment length and the number of segments must be > 0");

            // stop execution here
            return;
        }

        System.out.println("\nMaking a spiral starting from " + startPoint);
        System.out.println("with a unit length of " + unitLength + ", and made up of " + numberOfSegments);

        SpiralGenerator spiralSegment = new SpiralGenerator(startPoint, unitLength);
        Line2D prevSegment = null;

        // write a loop to check if each step fit in the failed conditions
        for (int i = 0; i < numberOfSegments; i++) {

            Line2D segment = spiralSegment.nextSegment();
            System.out.println("Segment #" + (i + 1) + ": " + segment.getP1() + " " + segment.getP2());

            // Check connectivity. The first if condition ensures there are two sgment exist
            if (prevSegment != null && (segment.getP1().getX() != prevSegment.getP2().getX() || segment.getP1().getY() != prevSegment.getP2().getY())) {

                System.out.println("FAILED: last two segments are not connected");

            }

            // Check if segment is either horizontal or vertical. Just consider one single segment
            boolean isVertical = segment.getP1().getX() == segment.getP2().getX();
            boolean isHorizontal = segment.getP1().getY() == segment.getP2().getY();

            if (!isVertical && !isHorizontal) {
                System.out.println("FAILED: segment is not horizontal or vertical. Skipping pair tests.");
            }

            checkPerpendicularity(prevSegment, segment, isVertical, isHorizontal);
            prevSegment = segment;

        }

    }

    // Check perpendicularity if there was a previous segment. The first if conditon ensures there are two segments exist,
    // and make sure last two segment horizontal or vertical
    public static void checkPerpendicularity(Line2D prevSegment, Line2D segment, boolean isVertical, boolean isHorizontal){

        if (prevSegment != null && (isVertical || isHorizontal)) {
            boolean prevIsVertical = prevSegment.getP1().getX() == prevSegment.getP2().getX();
            boolean prevIsHorizontal = prevSegment.getP1().getY() == prevSegment.getP2().getY();

            //Checking perpendicularity. For the spiral, the two segments adjacent to each other are always "one must be vertical if another one connected with it is horizontal"
            //or "one must be horizontal if another one connected with it is vertical". If both are not, they are not perpendicular)
            if (!((prevIsVertical && isHorizontal) || (prevIsHorizontal && isVertical))) {

                System.out.println("FAILED: last two segments are not perpendicular");
            }

        }
    }

}




