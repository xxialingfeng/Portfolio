package util;

import java.util.HashMap;

/**
 * A parser for an array of command line arguments.
 */
public class CommandLineParser {
  /**
   * Parse command line args and return a hashmap.
   *
   * @param args the args
   * @return the hash map
   */
  public static HashMap<String, String> parseCommandLineArgs(String[] args) {
    HashMap<String, String> parsedArgs = new HashMap<String, String>();

    int i;
    // ...less than args.length - 1 because each command flag requires at least
    // one parameter, include the integers
    for (i = 0; i < args.length - 1; i++) {
      // Check if arg is a flag
      if (args[i].startsWith("-")) {
        // Check for in flag, and check that the next element is not a flag
        if (args[i].replace("-", "").equalsIgnoreCase("in")
            && !args[i+1].startsWith("-")) {
          parsedArgs.put("in", args[i+1]);
          i++;
          continue;
        }
        // Get out
        if (args[i].replace("-", "").equalsIgnoreCase("out")
            && !args[i+1].startsWith("-")) {
          parsedArgs.put("out", args[i+1]);
          i++;
          continue;
        }
        // Get view
        if ((args[i].replace("-", "").equalsIgnoreCase("view") ||
            args[i].replace("-", "").equalsIgnoreCase("v"))
            && !args[i+1].startsWith("-")) {
          // Check for valid view type
          if (args[i + 1].equalsIgnoreCase("web")
              || args[i + 1].equalsIgnoreCase("graphical")) {
            parsedArgs.put("viewType", args[i + 1]);
            i++;
            continue;
          }
        }
      }
      // Try getting max dimensions
      try {
        int maxX  = Integer.parseInt(args[i]);
        int maxY = Integer.parseInt(args[i+1]);
        parsedArgs.put("maxViewSize", maxX + " " + maxY);
        i++;
      }
      catch (NumberFormatException nfe) {
        continue;
      }
    }
    // If we have a web view, then we need an out
    if (parsedArgs.containsKey("viewType")) {
      if (parsedArgs.get("viewType").equalsIgnoreCase("web")) {
        if (!parsedArgs.containsKey("out")) {
          System.out.println("View type was set to web, but no output was specified.");
          throw new IllegalArgumentException("View type was set to web, but no output was specified.");
        }
      }
    }
    // Make sure we have a view type
    if (!parsedArgs.containsKey("viewType")) {
      System.out.println("Must specify a view type");
      System.exit(1);
    }
    // Make sure we have an input file
    if (!parsedArgs.containsKey("in")) {
      System.out.printf("Must specify a file in");
      System.exit(1);
    }
    return parsedArgs;
  }
}
