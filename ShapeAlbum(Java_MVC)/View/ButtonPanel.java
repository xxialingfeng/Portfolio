package View;

import Controller.Features;
import Model.Snapshot;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel implements ActionListener {
  JButton select;
  JButton prev;
  JButton next;
  JButton exit;

  Features features;
  GraphicalView graphicalView;

  /**
   * Initialize a button panel.
   * @param featuresToAdd the features
   * @param graphicalView the graphical view
   * @throws IllegalArgumentException
   */
  public ButtonPanel(Features featuresToAdd, GraphicalView graphicalView)
      throws IllegalArgumentException {
    if (featuresToAdd == null) {
      throw new IllegalArgumentException("Feature is Null!");
    }
    //add features
    this.features = featuresToAdd;

    //add graphical view
    this.graphicalView = graphicalView;

    //set background, color, and size
    this.setBackground(new Color(148, 172, 209));
    this.setBounds(0, 0, 1375, 98);
    this.setPreferredSize(new Dimension(1375, 98));

    //set prev button
    prev = new JButton();
    prev.addActionListener(this);
    prev.setText("Previous Snapshot");
    add(prev);

    //set next button
    next = new JButton();
    next.addActionListener(this);
    next.setText("Next Snapshot");
    add(next);

    //set select button
    select = new JButton();
    select.addActionListener(this);
    select.setText("Select Snapshot");
    add(select);

    //exit button
    exit = new JButton();
    exit.addActionListener(this);
    exit.setText("Exit");
    add(exit);

    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == select) {
      List<String> idList = this.features.getAllAvailableSnapShotIDs();
      if (idList.size() >= 1) {
        String[] id = idList.toArray(new String[0]);

        Object selectedVal = JOptionPane.showInputDialog(null, "Choose a snapshot",
            "Select snapshot", JOptionPane.INFORMATION_MESSAGE, null, id, id[0]);

        if (selectedVal != null) {
          Snapshot chosenSnapshot = features.getSnapshotByID(selectedVal.toString());
          graphicalView.display(chosenSnapshot);
        }
        List<String> idToList = new ArrayList<>();
        Collections.addAll(idToList, id);
        features.setCurrentSnapshotIndex(idToList.indexOf(selectedVal));
      }
    }

    if (e.getSource() == next) {
      int currIndex = features.getCurrentSnapshotIndex();
      if (currIndex + 1 <= features.getAllAvailableSnapShotIDs().size()) {
        String nextToDisplay = features.getAllAvailableSnapShotIDs().get(currIndex + 1);
        features.setCurrentSnapshotIndex(currIndex + 1);
        graphicalView.display(features.getSnapshotByID(nextToDisplay));
      }
    }

    if (e.getSource() == prev) {
      int currIndex = features.getCurrentSnapshotIndex();
      if (currIndex - 1 >= 0) {
        String nextToDisplay = features.getAllAvailableSnapShotIDs().get(currIndex - 1);
        features.setCurrentSnapshotIndex(currIndex - 1);
        graphicalView.display(features.getSnapshotByID(nextToDisplay));
      }
    }

    if (e.getSource() == exit) {
      int res = JOptionPane.showConfirmDialog(null, "Confirm to exit?",
          "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
      if (res == 0) {
        System.exit(0);
      }
    }
  }
}
