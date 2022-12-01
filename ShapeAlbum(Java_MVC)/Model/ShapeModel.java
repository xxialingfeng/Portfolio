package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Model for shape album.
 */
public class ShapeModel implements IShapeModel {
  private List<IShape> listOfShapes;
  private List<Snapshot> snapshotList;
  private static final int MIN_SIZE = 0;
  private static final int MIN_COLOR = 0;
  private static final int MAX_COLOR = 255;

  /**
   * Constructs the PhotoAlbumModel object.
   */
  public ShapeModel() {
    this.listOfShapes = new ArrayList<>();
    this.snapshotList = new ArrayList<>();
  }

  /**
   * Create a new Shape.
   * @param name    the unique name of the shape photo (cannot be null or empty or existed)
   * @param type    type of the shape.
   * @param x       the x-axis value of the shape location (0 <= x <= 1000)
   * @param y       the y-axis value of the shape location (0 <= y <= 1000)
   * @param sizeOne the first positive value of the shape photo (weight, x radius, etc.)
   * @param sizeTwo the second positive value of the shape photo (height, y radius, etc.)
   * @param r       the red
   * @param g       the green
   * @param b       the blue
   * @return IShape
   * @throws IllegalArgumentException " "
   */
  @Override
  public IShape createShape(String name, String type, int x, int y, int sizeOne, int sizeTwo,
      int r, int g, int b) throws IllegalArgumentException {
    // Check if the variables are valid input
    if (name == null || name.equals("")
        || sizeOne <= MIN_SIZE || sizeTwo <= MIN_SIZE
        || outOfBound(r, g, b)) {
      throw new IllegalArgumentException("Invalid input.");
    }
    // CheckRepeatName
    if (isRepeat(name)) {
      throw new IllegalArgumentException();
    }
    IShape shapePhoto = null;
    switch (type.toLowerCase()) {
      case "oval":
        shapePhoto = new Oval(name, type, x, y, sizeOne, sizeTwo, r, g, b);
        break;
      case "rectangle":
        shapePhoto = new Rectangle(name, type, x, y, sizeOne, sizeTwo, r, g, b);
        break;
    }
    listOfShapes.add(shapePhoto);
    return shapePhoto;
  }

  /**
   * Check if the parameters are valid.
   * @param r int
   * @param g int
   * @param b int
   * @return true if not valid
   */
  private boolean outOfBound(int r, int g, int b) {
    return r < MIN_COLOR || g < MIN_COLOR || b < MIN_COLOR || r > MAX_COLOR
        || g > MAX_COLOR || b > MAX_COLOR;
  }

  /**
   * Check if the shape list contains the name.
   * @param name String
   * @return if or not
   */
  private boolean isRepeat(String name) {
    for (IShape shape: this.listOfShapes) {
      if (shape.getName().equalsIgnoreCase(name)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Gets shape by name.
   *
   * @param nameToGet the name to get
   * @return the shape by name
   * @throws IllegalArgumentException the illegal argument exception
   */
  public IShape getShapeByName(String nameToGet) throws IllegalArgumentException {
    for (IShape shape: this.listOfShapes) {
      if (shape.getName().equalsIgnoreCase(nameToGet)) {
        return shape;
      }
    }
    throw new IllegalArgumentException("Model does not currently have a shape with this name");
  }

  /**
   * Move the shape to another position.
   * @param name the name of the shape photo to be moved
   * @param x    the x-axis value of the new location
   * @param y    the y-axis value of the new location
   * @throws NoSuchElementException " "
   */
  @Override
  public void moveShape(String name, int x, int y) throws NoSuchElementException {
    if (!isRepeat(name)) {
      throw new NoSuchElementException("The given name does not exist or has no shape photo.");
    }
    IShape shape = getShapeByName(name);
    shape.setLocation(x, y);
  }

  /**
   * Change the size of the shape.
   * @param name    the name of the shape photo to be resized
   * @param sizeOne the new size of weight, x radius, etc.
   * @param sizeTwo the new size of height, y radius, etc.
   * @throws NoSuchElementException " "
   */
  @Override
  public void resizeShape(String name, int sizeOne, int sizeTwo) throws NoSuchElementException {
    if (!isRepeat(name)) {
      throw new NoSuchElementException("The given name does not exist or has no shape photo.");
    }
    IShape shape = getShapeByName(name);
    shape.setSize(sizeOne, sizeTwo);
  }

  /**
   * Change the color of the shape.
   * @param name the name of the shape photo
   * @param r    the red
   * @param g    the green
   * @param b    the blue
   * @throws NoSuchElementException " "
   */
  @Override
  public void recolorShape(String name, int r, int g, int b) throws NoSuchElementException {
    if (!isRepeat(name)) {
      throw new NoSuchElementException("The given name does not exist or has no shape photo.");
    }
    IShape shape = getShapeByName(name);
    shape.setColor(r, g, b);
  }

  @Override
  public void removeShape(String name) throws NoSuchElementException {
    if (this.listOfShapes.stream().anyMatch(iShape -> iShape.getName().equals(name))) {
      this.listOfShapes.removeIf(iShape -> iShape.getName().equalsIgnoreCase(name));
    }
    else {
      throw new IllegalArgumentException("Name given not in list");
    }
  }

  /**
   * Take snapshot.
   *
   * @param description the description
   */
  // SOURCE: https://howtodoinjava.com/java/collections/arraylist/arraylist-clone-deep-copy/
  public void takeSnapshot(String description) {
    List<IShape> snapshotListClone = new ArrayList<>();

    for (IShape shape : this.listOfShapes) {
      if (shape.getType().equalsIgnoreCase("rectangle")) {
        snapshotListClone.add(shape.copy());
      } else if (shape.getType().equalsIgnoreCase("oval")) {
        snapshotListClone.add(shape.copy());
      }
    }
    Snapshot newSnapshot = new Snapshot(description, snapshotListClone);
    this.snapshotList.add(newSnapshot);
  }

  /**
   * Display snapshots as a string.
   * Used for testing.
   *
   * @return the string
   */
  public String displaySnapShots() {
    if (this.snapshotList.isEmpty()) {
      return "Empty!";
    }
    StringBuilder ans = new StringBuilder();
    ans.append("Printing Snapshots\n");
    for (int i = 0; i < this.snapshotList.size(); i++) {
      if (i < this.snapshotList.size() - 1) {
        ans.append(this.snapshotList.get(i).toString()).append("\n\n");
      }
      else {
        ans.append(this.snapshotList.get(i).toString());
      }
    }
    return ans.toString();
  }

  /**
   * Display shapes as a string.
   * Used for testing.
   *
   * @return the string
   */
  public String displayShapes() {
    if (this.listOfShapes.isEmpty()) {
      return "Empty!";
    }
    StringBuilder ans = new StringBuilder("Printing Shapes\n");

    for (int i = 0; i < this.listOfShapes.size(); i++) {
      if (i < this.listOfShapes.size() - 1) {
        ans.append(this.listOfShapes.get(i).toString()).append("\n\n");
      }
      else {
        ans.append(this.listOfShapes.get(i).toString());
      }
    }
    return ans.toString();
  }

  /**
   * Gets snapshot list.
   *
   * @return the snapshot list
   */
  public List<Snapshot> getSnapshotList () {
    return this.snapshotList;
  }

  /**
   * Reset album.
   */
  @Override
  public void resetAlbum() {
    this.listOfShapes = new ArrayList<>();
    this.snapshotList = new ArrayList<>();
  }
}
