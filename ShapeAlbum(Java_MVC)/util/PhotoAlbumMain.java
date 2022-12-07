package util;

import Controller.ShapeAlbumController;
import Model.IShapeModel;
import Model.ShapeModel;
import View.GraphicalView;
import View.IView;
import View.WebView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


/**
 * Main entry point for the Photo Album.
 */
public class PhotoAlbumMain {

  /**
   * start of the program.
   * @param args input
   * @throws IllegalArgumentException " "
   * @throws IOException " "
   */
  public static void main(String[] args) throws IllegalArgumentException, IOException {
    //parse args in hashmap
    HashMap<String, String> parseArgs = CommandLineParser.parseCommandLineArgs(args);
    //create model
    IShapeModel model = new ShapeModel();
    ShapeAlbumController controller = new ShapeAlbumController(model, parseArgs);

    //create view
    if (parseArgs.get("viewType").equalsIgnoreCase("graphical")) {
      if (parseArgs.containsKey("maxViewSize")) {
        List<String> maxSize = List.of(parseArgs.get("maxViewSize").split("\\s+"));
        try {
          IView view = new GraphicalView(controller, Integer.parseInt(maxSize.get(0)), Integer.parseInt(maxSize.get(1)));
        } catch (Exception e) {
          throw new IllegalArgumentException("Bad input!");
        }
      } else {
        IView view = new GraphicalView(controller);
      }
    } else if (parseArgs.get("viewType").equalsIgnoreCase("web")) {
      IView view = new WebView(controller, parseArgs.get("out"));
    } else {
      throw new IllegalArgumentException("unknown");
    }
  }
}
