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
                    System.out.println(splitInput + ": command not found");
                }
            } else {
                System.out.println(input + ": command not found");
            }
        }
    }
}
