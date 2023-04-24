package View;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * A JPanel for information about the snapshot.
 */
public class InfoPanel extends JPanel {

  /**
   * Initialize a info panel.
   */
  public InfoPanel() {
    this.setBackground(new Color(148, 172, 209));
    this.setBounds(0, 0, 1275, 50);
    this.setPreferredSize(new Dimension(1375, 50));
    this.setVisible(true);
  }
}
