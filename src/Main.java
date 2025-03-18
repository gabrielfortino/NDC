import java.util.Scanner;
import java.util.logging.*;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Main.class.getName());
        Scanner scanner = new Scanner(System.in);
        NetworkListener networkListener = new NetworkListener();

        System.out.println("ATM Network Controller");
        System.out.println("1. Connect to ATM Host");
        System.out.println("2. Send Message");
        System.out.println("3. Receive Message");
        System.out.println("4. Disconnect");
        System.out.println("5. Exit");

        while (true) {
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ATM Host IP: ");
                    String ip = scanner.nextLine();
                    System.out.print("Enter ATM Host Port: ");
                    int port = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    networkListener.onNetworkConnect(ip, port);
                    break;

                case 2:
                    System.out.print("Enter message to send: ");
                    String message = scanner.nextLine();
                    networkListener.onNetworkSend(message.getBytes());
                    break;

                case 3:
                    networkListener.onNetworkReceive();
                    break;

                case 4:
                    networkListener.onNetworkDisconnect();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    networkListener.onNetworkDisconnect();
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}
