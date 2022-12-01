package Controller;

import Model.IShapeModel;
import Model.Snapshot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import util.FileParser;

/**
 * The controller class for the photo album.
 */
public class ShapeAlbumController implements Features {
  private IShapeModel model;
  private int currentSnapshotIndex;

  /**
   * Instantiates a new Photo album controller.
   *
   * @param model      the model
   * @param parsedArgs the parsed args
   * @throws FileNotFoundException the file not found exception
   */
  public ShapeAlbumController(IShapeModel model, HashMap<String, String> parsedArgs)
      throws FileNotFoundException {
    this.model = model;
    this.currentSnapshotIndex = 0;

    if (!parsedArgs.isEmpty()) {
      InputStream file = null;
      try {
        file = new FileInputStream(parsedArgs.get("in"));
      } catch (FileNotFoundException e) {
        System.out.println("File " + parsedArgs.get("in") + "was not found");
        System.exit(1);
      }

      FileParser.parseFile(file, this.model);
    }
  }

  @Override
  public List<String> getAllAvailableSnapShotIDs() {
    List<String> idList = model.getSnapshotList()
        .stream().map(Snapshot::getID).collect(Collectors.toList());
    return idList;
  }

  @Override
  public Snapshot getSnapshotByID(String ID) {
    List<Snapshot> snapshotToGet = model.getSnapshotList().stream()
        .filter(snapshot -> snapshot.getID().equalsIgnoreCase(ID)).collect(Collectors.toList());
    return snapshotToGet.get(0);
  }

  @Override
  public int getCurrentSnapshotIndex() {
    return this.currentSnapshotIndex;
  }

  @Override
  public void setCurrentSnapshotIndex(int index) {
    this.currentSnapshotIndex = index;
  }
}
