import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        List<String> commandList = Arrays.asList("echo", "exit", "type"); // List of possible commands

        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Please enter a non-empty command.");
                continue;
            }

            // Splitting command and arguments
            String command = input.split(" ")[0];
            String splitInput = input.length() > 5 ? input.substring(5) : "";

            if (input.equals("exit 0")) { // Exit with code 0
                break;
            }

            // Check for type of command
            switch (command) {
                case "echo":
                    System.out.println(splitInput);
                    break;
                case "type":
                    type(commandList, splitInput);
                    break;
                default:
                    executeExternalCommand(input); // Execute external programs with their own command and argument
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

    public static void executeExternalCommand(String input) {
        String[] externalProgram = input.split(" ");
        try {

            ProcessBuilder processBuilder = new ProcessBuilder(externalProgram); // Execute program
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(process.getInputStream())); // Process output

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(input + ": command not found");
        }
    }
}
