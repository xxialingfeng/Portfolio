package View;

import Controller.Features;
import Model.IShape;
import Model.Oval;
import Model.Rectangle;
import Model.Snapshot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JFrame;


public class WebView extends JFrame implements IView {
  private Features features;
  private FileWriter html;
  private File textFile;

  /**
   * Instantiates a new Web view.
   *
   * @param features      the features
   * @param OutputFile the name of out file
   * @throws IOException the io exception
   */
  public WebView (Features features, String OutputFile) throws IOException {
    this.features = features;
    List<String> snapshotIDs = features.getAllAvailableSnapShotIDs();
    Path filePath = Paths.get(OutputFile);
    this.textFile = new File(filePath.toString());
    this.html = new FileWriter(textFile);

    //no snapshot
    if (snapshotIDs.size() < 1) {
      html.write("<html>\n" + "<head>Photo Album</head>\n" + "<body>\n"
          + "\t<div>There are no snapshots to display.</div>\n"
          + "</body>\n" + "</html");
      html.close();
    } else {
      html.write("<html>\n" + "<h1>Photo Album</h1>\n" + "<body>");
      for (String snapshotID : snapshotIDs) {
        Snapshot snapshot = features.getSnapshotByID(snapshotID);
        List<IShape> shapeList = snapshot.getListOfShapes();

        //Show id and des
        html.write("<h2>ID: " + snapshot.getID() + "</h2>");
        html.write("<h3>Description: " + snapshot.getDescription() + "</h3>");
        html.write("<svg width=\"15cm\" height=\"15cm\" viewBox=\"0 0 1000 1000\""
            + " xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">");
        html.write("<rect x=\"1\" y=\"1\" width=\"998\" height=\"998\"\n"
            + "         \t\tfill=\"none\" stroke=\"blue\" stroke-width=\"2\"/>");
        if (!shapeList.isEmpty()) {
          for (IShape shape : shapeList) {
            if (shape instanceof Rectangle) {
              html.write("<rect x=\"" + shape.getX() + "\" y=\"" + shape.getY()
                  + "\" width=\"" + shape.getSizeOne() + "\" height=\"" + shape.getSizeTwo()
                  + "\"\n" + "\tfill=\"rgb(" + shape.getRed() + "," + shape.getGreen() + ","
                  + shape.getBlue() + "\" />");
            }

            if (shape instanceof Oval) {
              int xRadius = shape.getSizeOne() / 2;
              int yRadius = shape.getSizeTwo() / 2;

              html.write("<ellipse cx=\"" + (shape.getX() + xRadius)
                  + "\" cy=\"" + (shape.getY() + yRadius)
                  + "\" rx=\"" + xRadius
                  + "\" ry=\"" + yRadius
                  + "\"\n" + "\tfill=\"rgb(" + shape.getRed()
                  + ", " + shape.getGreen()
                  + ", " + shape.getBlue()
                  + ")\" />");
            }
          }
        }
        html.write("</svg>");
        html.write("</div><br>");
      }

      html.write("</body>\n" + "</html");
      html.close();
    }
  }

  @Override
  public void display(Snapshot snapshot) {

  }

  /**
   * Get html.
   * @return html information
   * @throws IOException the io exception
   */
  public String getHtmlLines() throws IOException {
    Stream<String> lines = Files.lines(Paths.get(textFile.toString()));

    StringBuilder res = new StringBuilder();
    List<String> list = lines.collect(Collectors.toList());
    for (String str : list) {
      res.append(str);
    }
    return res.toString();
  }
}
