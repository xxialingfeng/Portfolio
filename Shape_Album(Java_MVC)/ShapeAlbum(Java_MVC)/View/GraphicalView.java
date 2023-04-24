package View;

import Controller.Features;
import Model.IShape;
import Model.Snapshot;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Graphical view class.
 */
public class GraphicalView extends JFrame implements IView {
  private JLabel snapshotID;
  private JLabel snapshotDes;
  private JPanel infoPanel;
  private ShapeZonePanel shapesPanel;
  private JPanel buttonPanel;
  private Features features;

  private final int BUFFER = 200;

  public GraphicalView(Features features) {
    //set feature
    this.features = features;
    //exit
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //size
    this.setSize(1000, 800);
    //set background color
    this.getContentPane().setBackground(new Color(185, 213, 250));
    //the frame should be at the center
    this.setLocationRelativeTo(null);
    //set layout
    this.setLayout(new FlowLayout(FlowLayout.CENTER));
    //no resize
    this.setResizable(false);

    // Create a panel in which to display the snapshot ID and description
    this.infoPanel = new InfoPanel();
    add(infoPanel);
    // Create a panel on which to put shapes
    this.shapesPanel = new ShapeZonePanel(800, 600);
    add(shapesPanel);
    // Create a panel for buttons
    this.buttonPanel = new ButtonPanel(this.features, this);
    add(buttonPanel);

    //Labels
    this.snapshotID = new JLabel("No snapshots yet, start taking some!");
    this.snapshotDes = new JLabel();
    infoPanel.add(snapshotID);
    infoPanel.add(snapshotDes);

    //Display the first snapshot
    List<String> snapshotIDs = features.getAllAvailableSnapShotIDs();
    if (!snapshotIDs.isEmpty()) {
      display(features.getSnapshotByID(
          features.getAllAvailableSnapShotIDs().get(
              features.getCurrentSnapshotIndex()
          )
      ));
    }

    this.setVisible(true);
  }

  public GraphicalView(Features features, int maxX, int maxY) {
    //set features
    this.features = features;
    //exit program
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //set size
    this.setSize(maxX+BUFFER, maxY+BUFFER);
    //set background
    this.getContentPane().setBackground(new Color(185, 213, 350));
    //the frame should be at the center
    this.setLocationRelativeTo(null);
    //set layout
    this.setLayout(new FlowLayout(FlowLayout.CENTER));
    //no resize
    this.setResizable(false);

    // Create a panel in which to display the snapshot ID and description
    this.infoPanel = new InfoPanel();
    add(infoPanel);
    // Create a panel on which to put shapes
    this.shapesPanel = new ShapeZonePanel(800, 600);
    add(shapesPanel);
    // Create a panel for buttons
    this.buttonPanel = new ButtonPanel(this.features, this);
    add(buttonPanel);

    //Labels
    this.snapshotID = new JLabel("No snapshots yet, start taking some!");
    this.snapshotDes = new JLabel();
    infoPanel.add(snapshotID);
    infoPanel.add(snapshotDes);

    //Display the first snapshot
    List<String> snapshotIDs = features.getAllAvailableSnapShotIDs();
    if (!snapshotIDs.isEmpty()) {
      display(features.getSnapshotByID(
          features.getAllAvailableSnapShotIDs().get(
              features.getCurrentSnapshotIndex()
          )
      ));
    }

    //Display the view
    this.setVisible(true);
  }

  @Override
  public void display(Snapshot snapshot) {
    snapshotID.setText("Snapshot ID: " + snapshot.getID());
    if (isBlank(snapshot.getDescription())) {
      snapshotDes.setText("");
    } else {
      snapshotDes.setText("Snapshot Description: " + snapshot.getDescription());
    }
    List<IShape> shapeList = snapshot.getListOfShapes();
    shapesPanel.displayShapes(shapeList);
  }

  /**
   * Check if the string is blank.
   * @param str String
   * @return boolean
   */
  public boolean isBlank(String str) {
    if (str == null || str.length() == 0) {
      return true;
    }
    for (int i = 0; i < str.length(); i++) {
      if (!Character.isWhitespace(str.charAt(i))) {
        return false;
      }
    }
    return true;
  }
}
