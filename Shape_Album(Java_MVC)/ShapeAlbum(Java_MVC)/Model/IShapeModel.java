package Model;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Shape Photo Album Model interface.
 */
public interface IShapeModel {

  /**
   * Add a shape to list.
   * @param shapeToAdd
   * @return
   */
  void addShape(IShape shapeToAdd);

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