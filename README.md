Name: Wu Yadong
USC NetId:yadongwu
CSCI 455 PA1
Spring 2025

----------------------------------------------
CERTIFY IT'S YOUR WORK

"I certify that the work submitted for this assignment does not
violate USC's student conduct code.  In particular, the work is my
own, not a collaboration, and does not involve code created by other
people or AI software, except for the resources explicitly
mentioned in the CS 455 Course Syllabus.  And I did not share my
solution or parts of it with other students in the course."

Initial below to "sign" the above statement:

W.Y.
----------------------------------------------
ACKNOWLEDGE ANY OUTSIDE SOURCES

List here any code you submitted for this assignment that was written
with significant help of a course staff member, or code used from the
textbook.  Be specific about what methods or algorithms are involved,
and what sections of the textbook are involved (if applicable): [you do
not need to list any of the code that we wrote for the assignment,
i.e., the contents of the starter files for the assignment]

Professor gave me some hints for the algorithm about Spiral diraction and unitlength increment.

----------------------------------------------
KNOWN BUGS or LIMITATIONS:

spiralGeneratorTester(new Point(300, 400), 10, 7);For this case, the output shows "FAILED: last two segments are not perpendicular." The reason is I mix directions "up" and "down" in file "SpiralGenerator.java"

spiralGeneratorTester(new Point(100, 100), 1, 1); For this case, the output shows it cannot identify "right". Becasue I change it into upper-case "RIGHT"


The first submission:

SpiralViewer.java:25: error: cannot find symbol                                                                                                                                           
          JFrame frame = new JFrame();                                                                                                                                                        
          ^                                                                                                                                                                                   
      symbol:   class JFrame                                                                                                                                                                  
      location: class SpiralViewer                                                                                                                                                            
    SpiralViewer.java:25: error: cannot find symbol                                                                                                                                           
          JFrame frame = new JFrame();                                                                                                                                                        
                             ^                                                                                                                                                                
      symbol:   class JFrame                                                                                                                                                                  
      location: class SpiralViewer                                                                                                                                                            
    SpiralViewer.java:31: error: cannot find symbol                                                                                                                                           
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                                                                                                                               
                                         ^                                                                                                                                                    
      symbol:   variable JFrame                                                                                                                                                               
      location: class SpiralViewer                                                                                                                                                            
    3 errors       

I forgot to write "import javax.swing.JFrame;"


The second submission:

Running basic unit test with the following parameters:
  Starting point:      (200,300)
  Unit length:         5
  Number of segments:  5
--- Error in segment #2 ---
    expected: {Point2D.Double[205.0, 300.0],Point2D.Double[205.0, 295.0]}
    result: {Point2D.Double[205.0, 300.0],Point2D.Double[205.0, 290.0]}
(Failed)
The reason is I mix "up" and "left"

----------------------------------------------
ANY OTHER NOTES FOR THE GRADER:
None
