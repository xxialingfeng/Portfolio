package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Snapshot {
  private final String ID;
  private final String timestamp;
  private final String description;
  private final List<IShape> listOfShapes;
  // Source: https://www.javatpoint.com/java-get-current-date
  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  /**
   * Instantiates a new Snapshot.
   *
   * @param description  the description
   * @param listOfShapes the list of shapes
   */
  public Snapshot(String description,
      List<IShape> listOfShapes) {
    LocalDateTime now = LocalDateTime.now();
    this.ID = now.toString();
    this.timestamp = dtf.format(now);
    this.description = description;
    this.listOfShapes = listOfShapes;
  }

  @Override
  public String toString() {
    StringBuilder res = new StringBuilder("Printing Shapes\n");
    for (int i = 0; i < this.listOfShapes.size(); i++) {
      if (i < this.listOfShapes.size() - 1) {
        res.append(this.listOfShapes.get(i).toString()).append("\n\n");
      }
      else {
        res.append(this.listOfShapes.get(i).toString());
      }
    }
    String ans = "Snapshot ID: " + this.ID + "\n"
        + "Timestamp: " + this.timestamp + "\n"
        + "Description: " + this.description + "\n"
        + "Shape Information:\n";
    return ans + res.toString();
  }

  /**
   * Get id.
   * @return id.
   */
  public String getID() {
    return ID;
  }

  /**
   * Get timestamp.
   * @return timestamp.
   */
  public String getTimestamp() {
    return timestamp;
  }

  /**
   * Get description.
   * @return description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Get list of shapes.
   * @return list of shapes.
   */
  public List<IShape> getListOfShapes() {
    return listOfShapes;
  }
}
