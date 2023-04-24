package util;

import Model.IShapeModel;
import Model.Rectangle;
import Model.Oval;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileParser {

  /**
   * Check if the string is blank.
   * @param str String
   * @return boolean
   */
  public static boolean isBlank(String str) {
    if (str == null || str.length() == 0) {
      return true;
    }
    for (int i = 0; i < str.length(); i++) {
      if (!Character.isWhitespace(str.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  public static void parseFile(InputStream file, IShapeModel model)
      throws IllegalArgumentException {
    Scanner scanner = new Scanner(file);

    while (scanner.hasNext()) {
      String text = scanner.nextLine();
      if (text.startsWith("#") || isBlank(text)) {
        continue;
      } else {
        List<String> parameters = Arrays.asList(text.trim().split("\\s+"));

        if (parameters.get(0).equalsIgnoreCase("shape")) {
          if (parameters.size() != 10) {
            throw new IllegalArgumentException();
          }
          String name = parameters.get(1);
          int x = Integer.parseInt(parameters.get(3));
          int y = Integer.parseInt(parameters.get(4));
          int r = Integer.parseInt(parameters.get(7));
          int g = Integer.parseInt(parameters.get(8));
          int b = Integer.parseInt(parameters.get(9));
          int sizeOne = Integer.parseInt(parameters.get(5));
          int sizeTwo = Integer.parseInt(parameters.get(6));

          if (parameters.get(2).equalsIgnoreCase("rectangle")) {
            try {
              model.addShape(new Rectangle(name, "rectangle", x, y, sizeOne, sizeTwo, r, g, b));
            } catch (Exception e) {
              throw new IllegalArgumentException();
            }
          } else if (parameters.get(2).equalsIgnoreCase("oval")) {
            try {
              model.addShape(new Oval(name, "oval", x, y, sizeOne, sizeTwo, r, g, b));
            } catch (Exception e) {
              throw new IllegalArgumentException();
            }
          }
        }

        //take snapshot
        if (parameters.get(0).equalsIgnoreCase("snapshot")) {
          //if not description
          if (parameters.size() == 1) {
            model.takeSnapshot("");
          } else {
            String description = text.replaceAll("(?i)snapshot", "").trim();
            model.takeSnapshot(description);
          }
        }

        //resize
        if (parameters.get(0).equalsIgnoreCase("resize")) {
          if (parameters.size() != 4) {
            throw new IllegalArgumentException();
          }
          try {
            model.resizeShape(parameters.get(1), Integer.parseInt(parameters.get(2)), Integer.parseInt(parameters.get(3)));
          } catch (Exception e) {
            throw new IllegalArgumentException();
          }
        }

        //move
        if (parameters.get(0).equalsIgnoreCase("move")) {
          if (parameters.size() != 4) {
            throw new IllegalArgumentException("wrong command");
          }
          try {
            model.moveShape(parameters.get(1), Integer.parseInt(parameters.get(2)), Integer.parseInt(parameters.get(3)));
          } catch (Exception e) {
            throw new IllegalArgumentException("bad command");
          }
        }

        //recolor
        if (parameters.get(0).equalsIgnoreCase("color")) {
          if (parameters.size() != 5) {
            throw new IllegalArgumentException("wrong command");
          }
          try {
            model.recolorShape(parameters.get(1), Integer.parseInt(parameters.get(2)), Integer.parseInt(parameters.get(3)),
                Integer.parseInt(parameters.get(4)));
          } catch (Exception e) {
            throw new IllegalArgumentException("bad command");
          }
        }

        //remove
        if (parameters.get(0).equalsIgnoreCase("remove")) {
          if (parameters.size() != 2) {
            throw new IllegalArgumentException();
          }
          try {
            model.removeShape(parameters.get(1));
          } catch (Exception e) {
            throw new IllegalArgumentException();
          }
        }
      }
    }
  }
}
