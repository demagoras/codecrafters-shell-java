import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        List<String> commandList = Arrays.asList("echo", "exit", "type");

        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();

            String command = input.split(" ")[0];
            String splitInput = input.length() > 5 ? input.substring(5) : "";

            if (input.equals("exit 0")) {
                break;
            }

            switch (command) {
                case "echo":
                    System.out.println(splitInput);
                    break;
                case "type":
                    type(commandList, splitInput);
                    break;
                default:
                    System.out.println(input + ": command not found");
            }
        }
    }

    public static void type(List<String> commandList, String splitInput) {
        if (commandList.contains(splitInput)) {
            System.out.println(splitInput + " is a shell builtin");
        } else {
            String pathDir = System.getenv("PATH");
            String[] dirs = pathDir.split(":");
            boolean found = false;

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
    }
}
