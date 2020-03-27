package cs3500.animator.view;

import cs3500.animation.model.SimpleAnimation;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * To represent the visual view of the animation.
 */
public class VisualView extends JFrame implements View {

  private VisualPanel panel;
  private SimpleAnimation model;

  /**
   * The constructor of the visual view which set up for animation to run.
   *
   * @param model      the animation model to be run with.
   * @param tickPerSec tick per second.
   */
  public VisualView(SimpleAnimation model, int tickPerSec) {
    super();
    this.model = model;

    this.setTitle("Animation player");
    this.setBounds(model.getBox());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    panel = new VisualPanel(model, tickPerSec);
    this.add(panel, BorderLayout.CENTER);
    pack();
  }


  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void display() {
    this.setVisible(true);
  }
}


