import java.io.*;
import java.net.*;
import java.util.logging.*;

public class Network {
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private boolean isConnected;
    private Logger log;

    public Network(Logger log) {
        this.log = log;
        this.isConnected = false;
    }

    // Connect to the ATM host
    public void connect(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            isConnected = true;
            log.info("Connected to host: " + ip + ":" + port);
        } catch (IOException e) {
            log.severe("Connection failed: " + e.getMessage());
        }
    }

    // Send data to the ATM host
    public void sendMessage(byte[] data) {
        try {
            if (isConnected && out != null) {
                out.write(data);
                out.flush();
                log.info("Message sent: " + new String(data));
            }
        } catch (IOException e) {
            log.severe("Failed to send message: " + e.getMessage());
        }
    }

    // Receive data from the ATM host
    public byte[] receiveMessage() {
        try {
            if (isConnected && in != null) {
                byte[] buffer = new byte[1024];
                int bytesRead = in.read(buffer);
                if (bytesRead > 0) {
                    byte[] message = new byte[bytesRead];
                    System.arraycopy(buffer, 0, message, 0, bytesRead);
                    log.info("Message received: " + new String(message));
                    return message;
                }
            }
        } catch (IOException e) {
            log.severe("Failed to receive message: " + e.getMessage());
        }
        return null;
    }

    // Disconnect from the host
    public void disconnect() {
        try {
            if (socket != null) {
                socket.close();
            }
            isConnected = false;
            log.info("Disconnected from host.");
        } catch (IOException e) {
            log.severe("Failed to disconnect: " + e.getMessage());
        }
    }

    public boolean isConnected() {
        return isConnected;
    }
}
