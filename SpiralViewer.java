// Name:Wu Yadong
// USC NetID:yadongwu
// CS 455 PA1
// Spring 2025
import java.awt.Point;
import java.util.Scanner;
import javax.swing.JFrame;
/**
 * SpiralViewer creates a window to display a rectangular spiral.
 * It prompts the user for the initial segment length and number of segments,
 * ensuring both values are greater than zero before rendering the spiral.
 * Invariants:
 * - FRAME_WIDTH and FRAME_HEIGHT must be > 0.
 * - length must be > 0.
 * - number must be > 0.
 */

public class SpiralViewer{

    /**
     * Main method that runs the spiral viewer application,
     * which means the spiral graph can be showed via revoking this main method
     */

    private static final int WIDTH = 800;
    private static final int HEIGHT = 500;


    public static void main (String[] args){

        // Creating a frame for the spiral
        Scanner in = new Scanner(System.in);
        JFrame frame = new JFrame();
        frame.setSize(WIDTH, HEIGHT);
        frame.setTitle("spiral");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Making sure the length of initial segment is greater than 0
        System.out.print("Enter length of initial segment: ");
        int length = in.nextInt();

        while (length <= 0 ) {
            System.out.println("ERROR: value must be > 0");
            System.out.print("Enter length of initial segment: ");
            length = in.nextInt();
        }

        System.out.println();

        //Making sure the number of segments is greater than 0
        System.out.print("Enter number of segments: ");
        int number = in.nextInt();

        while(number <=0){
            System.out.println("ERROR: value must be > 0");
            System.out.print("Enter number of segments: ");
            number = in.nextInt();
        }

        in.close();

        SpiralComponent component = new SpiralComponent(length, number);
        frame.add(component);

        //Showing the grapgh
        frame.setVisible(true);
    }
}