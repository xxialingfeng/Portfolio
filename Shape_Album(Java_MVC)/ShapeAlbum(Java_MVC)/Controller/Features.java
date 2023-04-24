package Controller;

import Model.Snapshot;

import java.util.List;

/**
 * The interface Features.
 */
public interface Features {

  /**
   * Gets all available snapshot IDs.
   *
   * @return a list of all available snapshot IDs
   */
  List<String> getAllAvailableSnapShotIDs();

  /**
   * Gets snapshot by id.
   *
   * @param ID the id of the snapshot to get
   * @return a snapshot
   */
  Snapshot getSnapshotByID(String ID);

  /**
   * Gets current snapshot index.
   *
   * @return the current snapshot index
   */
  int getCurrentSnapshotIndex();

  /**
   * Set current snapshot index.
   *
   * @param index the new index
   */
  void setCurrentSnapshotIndex(int index);
}
