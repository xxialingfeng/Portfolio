package Model;

/**
 * The interface for Shape class.
 */
public interface IShape {

  /**
   * Sets the location of the shape photo album on a canvas.
   * @param x the new x-axis value of the shape location
   * @param y the new y-axis value of the shape location
   * @throws IllegalArgumentException if the shape is outside the canvas (origin at top left)
   */
  void setLocation(int x, int y);

  /**
   * Sets the size of the shape photo album.
   * @param one the new first positive value of the shape photo (weight, x radius, etc.)
   * @param two the new second positive value of the shape photo (height, y radius, etc.)
   * @throws IllegalArgumentException if the input is not positive
   */
  void setSize(int one, int two) throws IllegalArgumentException;

  /**
   * Sets the color of the shape photo album.
   * @param r the red
   * @param g the green
   * @param b the blue
   */
  void setColor(int r, int g, int b) throws IllegalArgumentException;

  /**
   * Gets the name of the shape.
   * @return a String representation of the name
   */
  String getName();

  /**
   * Gets the x-axis value of the shape.
   * @return the x-axis value of the shape
   */
  int getX();

  /**
   * Gets the y-axis value of the shape.
   * @return the y-axis value of the shape
   */
  int getY();

  /**
   * Gets the first size value of the shape.
   * @return the first shape size value
   */
  int getSizeOne();

  /**
   * Gets the second size of the shape.
   * @return the second shape size value
   */
  int getSizeTwo();

  /**
   * Gets the red color of the shape photo.
   * @return the shape color red
   */
  int getRed();

  /**
   * Gets the green color of the shape photo.
   * @return the shape color green
   */
  int getGreen();

  /**
   * Gets the blue color of the shape photo.
   * @return the shape color blue
   */
  int getBlue();

  /**
   * Gets the shape type.
   * @return the String representation of the shape type
   */
  String getType();

  /**
   * Deep clone a shape.
   * @return a deep clone
   */
  IShape copy();
}
