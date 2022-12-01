package View;

import Model.Snapshot;

/**
 * An interface for view.
 */
public interface IView {

  /**
   * Display an individual snapshot.
   */
  void display(Snapshot snapshot);
}
