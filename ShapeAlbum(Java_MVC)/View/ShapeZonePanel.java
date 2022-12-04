package View;

import Model.IShape;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * A JPanel for displaying shapes.
 */
public class ShapeZonePanel extends JPanel {
  List<Shape> panelShapeList;
  List<IShape> IShapeList;

  /**
   * Instantiates a new Shape zone panel.
   *
   * @param maxX the max x
   * @param maxY the max y
   */
  public ShapeZonePanel(int maxX, int maxY) {
    panelShapeList = new ArrayList<>();
    setBackground(Color.WHITE);
    this.setBounds(0, 0, maxX, maxY);
    this.setPreferredSize(new Dimension(maxX, maxY));
    this.setVisible(true);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    int i = 0;
    for (Shape shapeToDraw : panelShapeList) {
      g2d.setColor(new Color(IShapeList.get(i).getRed(),
          IShapeList.get(i).getGreen(), IShapeList.get(i).getBlue()));
      g2d.fill(shapeToDraw);
      i++;
    }
  }

  /**
   * Show the shape.
   * @param shapeList list of shapes
   */
  public void displayShapes(List<IShape> shapeList) {
    panelShapeList = new ArrayList<>();
    this.IShapeList = shapeList;
    for (IShape shape : shapeList) {
      if (shape.getType().equalsIgnoreCase("rectangle")) {
        panelShapeList.add(new Rectangle2D.Double(shape.getX(),
            shape.getY(),
            shape.getSizeOne(),
            shape.getSizeTwo()));
      } else if (shape.getType().equalsIgnoreCase("oval")) {
        panelShapeList.add(new Ellipse2D.Double(shape.getX(),
            shape.getY(),
            shape.getSizeOne(),
            shape.getSizeTwo()));
      }
    }
    repaint();
  }
}
