package Model;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Shape Photo Album Model interface.
 */
public interface IShapeModel {

  /**
   * Creates a shape object. The customer may want to get copies of the same shape photos,
   * therefore, the same shape with different names sharing the same other features can be created
   * and added to the album multiple times. The photo album size is 1000 * 1000. The origin (0,0) is
   * the upper left corner of the canvas.
   *
   * @param name    the unique name of the shape photo (cannot be null or empty or existed)
   * @param shape   the String representation of the shape type (cannot be null or empty)
   * @param x       the x-axis value of the shape location (0 <= x <= 1000)
   * @param y       the y-axis value of the shape location (0 <= y <= 1000)
   * @param sizeOne the first positive value of the shape photo (weight, x radius, etc.)
   * @param sizeTwo the second positive value of the shape photo (height, y radius, etc.)
   * @param r       the red
   * @param g       the green
   * @param b       the blue
   * @throws IllegalArgumentException if the input is invalid
   */
  IShape createShape(String name, String type, int x, int y, int sizeOne, int sizeTwo,
      int r, int g, int b) throws IllegalArgumentException;

  /**
   * Moves the given shape photo to a new location.
   *
   * @param name the name of the shape photo to be moved
   * @param x    the x-axis value of the new location
   * @param y    the y-axis value of the new location
   * @throws NoSuchElementException if the given name does not exist or contains null value
   */
  void moveShape(String name, int x, int y) throws NoSuchElementException;

  /**
   * Resizes the given shape photo.
   *
   * @param name    the name of the shape photo to be resized
   * @param sizeOne the new size of weight, x radius, etc.
   * @param sizeTwo the new size of height, y radius, etc.
   * @throws NoSuchElementException if the given name does not exist or contains null value
   */
  void resizeShape(String name, int sizeOne, int sizeTwo) throws NoSuchElementException;

  /**
   * Recolors the given shape photo.
   *
   * @param name the name of the shape photo
   * @param r    the red
   * @param g    the green
   * @param b    the blue
   * @throws NoSuchElementException if the given name does not exist or contains null value
   */
  void recolorShape(String name, int r, int g, int b) throws NoSuchElementException;

  /**
   * Removes a shape photo.
   *
   * @param name the name of the shape photo to be removed
   * @throws NoSuchElementException if the given name does not exist or contains null value
   */
  void removeShape(String name) throws NoSuchElementException;


  /**
   * Takes and returns a snapshot of this album.
   * @param description the description of the snapshot, It can be empty or null.
   */
  void takeSnapshot(String description);

  /**
   * Returns the list of snapshot ids.
   *
   * @return the list of snapshot ids
   */
  List<Snapshot> getSnapshotList();

  /**
   * Show the snapshot.
   * @return snapshot.
   */
  String displaySnapShots();

  /**
   * show all the shapes.
   * @return shape
   */
  String displayShapes();

  /**
   * Clears and resets the album into an empty collection.
   */
  void resetAlbum();
}