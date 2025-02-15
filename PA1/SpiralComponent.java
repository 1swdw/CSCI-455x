// Name:Wu Yadong
// USC NetID:yadongwu
// CS 455 PA1
// Spring 2025
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JComponent;

/**
 * SpiralComponent draws a rectangular spiral on a Swing component.
 * The spiral starts at the center and expands counterclockwise.
 * Using the for-loop to draw the graph
 * Invariants:
 * - length must be > 0.
 * - numbers must be ≥ 0.
 * - The spiral should always start at the center of the component.
 */
public class SpiralComponent extends JComponent{

    //Private can make these only can be accessed in SpiralComponent
    private int length;
    private int numbers;

    private static final int CENTER_DIVISOR = 2;

    // Constructor initializes any necessary data
    public SpiralComponent (int length, int numbers){
        this.length = length;
        this.numbers = numbers;

    }

    //Paints the spiral onto the component.
    public void paintComponent(Graphics g){

        Graphics2D g2 = (Graphics2D) g;

        // // Ensure the spiral's starting point is at the center of the component.

        int x = getWidth() / CENTER_DIVISOR;
        int y = getHeight() / CENTER_DIVISOR;


        Point startPoint = new Point(x, y);
        // Create a SpiralGenerator to generate spiral segments
        SpiralGenerator spiralGenerator = new SpiralGenerator(startPoint, length);

        //Draw the spiral based the number of the segment
        for(int i = 0; i< numbers; i++){
            g2.draw(spiralGenerator.nextSegment());

        }
    }

}
