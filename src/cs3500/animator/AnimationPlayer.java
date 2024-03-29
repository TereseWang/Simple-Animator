package cs3500.animator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import cs3500.animation.model.Animation;
import cs3500.animation.model.KeyFrameAnimation;
import cs3500.animation.model.SimpleAnimation;
import cs3500.animator.controller.SimpleController;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.View;
import cs3500.animator.view.ViewCreator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Entry point for this program.
 */

public final class AnimationPlayer {

  /**
   * entry point.
   */
  public static void main(String[] args) {

    Animation model;
    FileReader in = null;
    String inFilename = "";
    FileWriter out = null;
    String outFilename = "";
    String viewName = "";
    View view;
    int tps = 0;
    JFrame frame = new JFrame("Warning");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();

    //to spilt the arguments.
    Iterator<String> argsI = Arrays.asList(args).iterator();
    while (argsI.hasNext()) {
      switch (argsI.next()) {
        case "-in":
          inFilename = argsI.next();
          break;
        case "-view":
          viewName = argsI.next();
          break;
        case "-out":
          outFilename = argsI.next();
          break;
        case "-speed":
          tps = Integer.parseInt(argsI.next());
          break;
        default:
          break;
      }
    }
    try {
      in = new FileReader(inFilename);
    } catch (FileNotFoundException e) {
      JOptionPane.showMessageDialog(frame,
          e.getMessage(),
          "Inane warning",
          JOptionPane.WARNING_MESSAGE);
    }
    //use input to model.
    AnimationReader reader = new AnimationReader();
    AnimationBuilder<Animation> builder;
    if (viewName.equalsIgnoreCase("edit")) {
      builder = new KeyFrameAnimation.Builder();
    } else {
      builder = new SimpleAnimation.SimpleBuilder();
    }
    model = reader.parseFile(in, builder);
    // out to create view.
    out = null;
    if (!outFilename.equals("")) {
      try {
        out = new FileWriter(outFilename);
      } catch (IOException e) {
        JOptionPane.showMessageDialog(frame,
            e.getMessage(),
            "Inane warning",
            JOptionPane.WARNING_MESSAGE);
        e.printStackTrace();
      }
    }
    view = null;
    try {
      ViewCreator viewCreator = new ViewCreator();
      view = viewCreator.create(ViewCreator.ViewType.findViewType(viewName),
          model, out, tps);
    } catch (IllegalArgumentException e) {
      JOptionPane.showMessageDialog(frame,
          e.getMessage(),
          "Inane warning",
          JOptionPane.WARNING_MESSAGE);
      frame.setVisible(true);
    }

    SimpleController controller = new SimpleController(view);

    // go to the controller to display the animation.
    controller.execute();
    try {
      if (out != null) {
        out.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
