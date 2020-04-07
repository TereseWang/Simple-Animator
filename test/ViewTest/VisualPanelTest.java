package ViewTest;

import static org.junit.Assert.assertEquals;

import cs3500.animation.model.Motion;
import cs3500.animation.model.SimpleAnimation;
import cs3500.animator.shape.Color;
import cs3500.animator.shape.Oval;
import cs3500.animator.shape.Posn;
import cs3500.animator.shape.Rectangle;
import cs3500.animator.shape.Shape;
import cs3500.animator.view.VisualPanel;
import org.junit.Before;

/**
 * To test functions in VisualPanel.
 */
public class VisualPanelTest {

  private SimpleAnimation animation;
  private VisualPanel panel;

  @Before
  public void init() {
    animation = new SimpleAnimation();
    animation.declareShape("a", "Rectangle");
    Shape s = new Rectangle(new Posn(10, 10), new Color(100, 100, 100), 3, 3);
    Shape s1 = new Rectangle(new Posn(100, 100), new Color(100, 100, 100), 3, 3);
    animation.addMotion("a", new Motion(4, 5, s, s1));
    Shape s2 = new Rectangle(new Posn(100, 100), new Color(100, 100, 100), 3, 3);
    Shape s3 = new Rectangle(new Posn(100, 200), new Color(0, 0, 255), 5, 5);
    animation.addMotion("a", new Motion(5, 10, s2, s3));
    Shape s4 = new Rectangle(new Posn(100, 200), new Color(0, 0, 255), 5, 5);
    Shape s5 = new Rectangle(new Posn(100, 200), new Color(100, 100, 100), 4, 10);
    animation.addMotion("a", new Motion(10, 24, s4, s5));
    Shape s6 = new Rectangle(new Posn(100, 200), new Color(100, 100, 100), 4, 10);
    Shape s7 = new Rectangle(new Posn(10, 10), new Color(100, 100, 100), 3, 3);
    animation.addMotion("a", new Motion(1, 4, s6, s7));
    animation.addMotion("a", new Motion(1, 1, s6, s6));

    animation.declareShape("b", "oval");
    Shape s8 = new Oval(new Posn(100, 100), new Color(0, 255, 255), 4, 10);
    animation.addMotion("b", new Motion(4, 4, s8, s8));
    animation.addMotion("b", new Motion(4, 5, s8, s8));
    panel = new VisualPanel(animation, 1000);
  }
}