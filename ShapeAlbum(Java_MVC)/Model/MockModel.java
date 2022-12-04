package Model;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * A mock model for testing the controller..
 */
public class MockModel implements IShapeModel {
  private StringBuilder log;
  private final int uniqueCode;

  /**
   * Instantiates a new Mock model.
   *
   * @param log        the log
   * @param uniqueCode the unique code
   */
  public MockModel(StringBuilder log, int uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public void addShape(IShape shapeToAdd) throws IllegalArgumentException {
    log.append("Input: ").append(shapeToAdd.toString()).append("\n");
    log.append("Unique Code: ").append(this.uniqueCode).append("\n");

  }

  @Override
  public void moveShape(String name, int x, int y) throws NoSuchElementException {
    log.append("Input: ").append(name).append(" ").append(x).append(" ").append(y).append("\n");
    log.append("Unique Code: ").append(this.uniqueCode).append("\n");
  }

  @Override
  public void resizeShape(String name, int sizeOne, int sizeTwo) throws NoSuchElementException {
    log.append("Input: ").append(name).append(" ")
        .append(sizeOne).append(" ").append(sizeTwo).append("\n");
    log.append("Unique Code: ").append(this.uniqueCode).append("\n");
  }

  @Override
  public void recolorShape(String name, int r, int g, int b) throws NoSuchElementException {
    log.append("Input: ").append(name).append(" ")
        .append(r).append(" ").append(g).append(" ").append(b).append("\n");
    log.append("Unique Code: ").append(this.uniqueCode).append("\n");
  }

  @Override
  public void removeShape(String nameToRemove)
      throws IllegalArgumentException {
    log.append("Input: ").append(nameToRemove).append("\n");
    log.append("Unique Code: ").append(this.uniqueCode).append("\n");
  }

  @Override
  public void takeSnapshot(String description) {
    log.append("Input: ").append(description).append("\n");
    log.append("Unique Code: ").append(this.uniqueCode).append("\n");
  }

  @Override
  public List<Snapshot> getSnapshotList() {
    return null;
  }

  @Override
  public String displaySnapShots() {
    return null;
  }

  @Override
  public String displayShapes() {
    return null;
  }

  @Override
  public void resetAlbum() {

  }

  /**
   * Gets log.
   *
   * @return the log
   */
  public StringBuilder getLog() {
    return this.log;
  }
}
