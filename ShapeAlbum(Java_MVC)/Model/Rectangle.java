package Model;

/**
 * This class represents an Oval object. It extends AbstractShape
 * and implements IShape interface.
 */
public class Rectangle extends AbstractShape {

  /**
   * Constructs the Rectangle class.
   * @param name the name of the Rectangle shape
   * @param type the type of the shape
   * @param x the x-axis value of the upper left corner point
   * @param y the y-axis value of the upper left corner point
   * @param width the width of the shape
   * @param height the height
   * @param r the red
   * @param g the green
   * @param b the blue
   */
  public Rectangle(String name, String type, int x, int y, int width, int height,
      int r, int g, int b) {
    super(name, type, x, y, width, height, r, g, b);
  }

  /**
   * print information in the given format.
   * @return String format information
   */
  @Override
  public String toString() {
    return "Name: " + this.getName() + "\n"
        + "Type: " + this.getType() + "\n"
        + "Min corner: (" + this.getX() + "," + this.getY() + ")" + ", "
        + String.format("Width: %d, Height: %d, ", this.getSizeOne(), this.getSizeTwo())
        + "Color: (" + this.getRed() + ","
        + this.getGreen() + ","
        + this.getBlue() + ")\n";
  }

  /**
   * Deep copy.
   * @return Rectangle
   */
  public Rectangle copy() {
    return new Rectangle(this.getName(), this.getType(), this.getX(), this.getY(),
        this.getSizeOne(), this.getSizeTwo(), this.getRed(), this.getGreen(), this.getBlue());
  }
}
