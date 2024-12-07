import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        List<String> commands = Arrays.asList("echo", "exit", "type");

        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();
            String splitInput = input.substring(5);

            if (input.equals("exit 0")) {
                break;
            }
            if (input.startsWith("echo")) {
                System.out.println(splitInput);
            }
            else if (input.startsWith("type")) {
                if (commands.contains(splitInput)) {
                    System.out.println(splitInput + " is a shell builtin");
                } else {
                    boolean found = false;
                    String pathDir = System.getenv("PATH");
                    String[] dirs = pathDir.split(":");

                    for (String dir : dirs) {
                        File file = new File(dir, splitInput);

                        if (file.exists()) {
                            System.out.println(splitInput + " is " + file.getAbsolutePath());
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println(splitInput + ": not found");
                    }
                }
            } else {
                System.out.println(input + ": command not found");
            }
        }
    }
}
