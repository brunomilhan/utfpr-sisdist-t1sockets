package br.utfpr.sockets.conn;

import br.utfpr.sockets.model.Message;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class Multicast {
    private static final String GROUP_ADDR = "239.0.0.1";
    private MulticastSocket multicastSocket = null;
    private InetAddress groupAddr = null;
    private int clientPort;

    public Multicast(int clientPort) {
        this.clientPort = clientPort;
        initMulticast();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Private Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void initMulticast() {
        try {
            groupAddr = InetAddress.getByName(GROUP_ADDR);
            multicastSocket = new MulticastSocket(clientPort);
            multicastSocket.joinGroup(groupAddr);
            System.out.println("Socket init with success!");
        } catch (SocketException e) {
            System.out.println("Socket init: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Public Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String request(Message message) {
        DatagramPacket request = new DatagramPacket(message.getBody(), message.getLength(), groupAddr, clientPort);
        try {
            multicastSocket.send(request);
            return "OK";
        } catch (SocketException e) {
            System.out.println("Socket request: " + e.getMessage());
            return "SocketException";
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
            return "IOException";
        } finally {
            if (multicastSocket != null)
                multicastSocket.close();
        }
    }

    public String response() {
        Message message = new Message();
        try {
            while (true) {
                DatagramPacket response = new DatagramPacket(message.getBody(), message.getLength());
                multicastSocket.receive(response);
                System.out.println("Received:" + new String(response.getData()));
            }

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
            return "SocketException";
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
            return "IOException";
        } finally {
            if (multicastSocket != null)
                multicastSocket.close();
            return "OK";
        }
    }

    public String leaveGroup() {
        try {
            multicastSocket.leaveGroup(groupAddr);
            return "OK";
        } catch (SocketException e) {
            System.out.println("leaveGroup Socket: " + e.getMessage());
            return "SocketException";
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
            return "IOException";
        } finally {
            if (multicastSocket != null)
                multicastSocket.close();
        }
    }
}
