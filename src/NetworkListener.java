import java.util.logging.*;

public class NetworkListener {
    private Network network;
    private Logger log;

    public NetworkListener() {
        this.log = Logger.getLogger(NetworkListener.class.getName());
        this.network = new Network(log);
    }

    // Simulate an event-driven connection setup
    public void onNetworkConnect(String ip, int port) {
        log.info("Attempting to connect to: " + ip + ":" + port);
        network.connect(ip, port);
    }

    // Simulate an event-driven message send
    public void onNetworkSend(byte[] message) {
        log.info("Sending message...");
        network.sendMessage(message);
    }

    // Simulate an event-driven message receive
    public void onNetworkReceive() {
        log.info("Waiting for message...");
        byte[] receivedMessage = network.receiveMessage();
        if (receivedMessage != null) {
            log.info("Received: " + new String(receivedMessage));
        }
    }

    // Simulate an event-driven disconnect
    public void onNetworkDisconnect() {
        log.info("Disconnecting...");
        network.disconnect();
    }
}
