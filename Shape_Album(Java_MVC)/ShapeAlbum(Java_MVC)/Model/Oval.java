package Model;

/**
 * This class represents an Oval object. It extends AbstractShape
 * and implements IShape interface.
 */
public class Oval extends AbstractShape {

  /**
   * Constructs the Oval class.
   * @param name the name of the Oval shape
   * @param type the type of the shape
   * @param x the x-axis value of the center point
   * @param y the y-axis value of the center point
   * @param xRadius the x radius of the shape
   * @param yRadius the y radius
   * @param r the red
   * @param g the green
   * @param b the blue
   */
  public Oval(String name, String type, int x, int y, int xRadius, int yRadius, int r, int g, int b)
      throws IllegalArgumentException {
    super(name, type, x, y, xRadius, yRadius, r, g, b);
  }

  @Override
  public String toString() {
    return "Name: " + this.getName() + "\n"
        + "Type: " + this.getType() + "\n"
        + "Center: (" + this.getX() + "," + this.getY() + ")" + ", "
        + String.format("X radius: %d, Y radius: %d, ", this.getSizeOne(), this.getSizeTwo())
        + "Color: (" + this.getRed() + ","
        + this.getGreen() + ","
        + this.getBlue() + ")\n";
  }

  /**
   * Deep copy.
   * @return Rectangle
   */
  public Oval copy() {
    return new Oval(this.getName(), this.getType(), this.getX(), this.getY(),
        this.getSizeOne(), this.getSizeTwo(), this.getRed(), this.getGreen(), this.getBlue());
  }
}
