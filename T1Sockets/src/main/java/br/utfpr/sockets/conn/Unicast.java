package br.utfpr.sockets.conn;

import br.utfpr.sockets.model.Message;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Unicast implements Client {
    private boolean isUDP;
    private boolean isClient;
    private int port;
    private static final String LOCAL_IP = "localhost";

    private Client client;

    public Unicast(boolean isUDP, boolean isClient) {
        this.isUDP = isUDP;
        this.isClient = isClient;

        initConnection();
    }

    private void initConnection() {
        if (isUDP) {
            if (isClient)
                client = new UDPClient();
            /*else
                client = new UDPServer();*/
        } else {
            if (isClient)
                client = new TCPClient();
           /* else
                client = new TCPServer();*/
        }

    }

    public String request(String ip, int port, Message message) {
        return client.request(LOCAL_IP, port, message);
    }

    public String response() {
        return client.response();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Inner Class TCP / UDP - Client and Server
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /***
     * @version 1.0
     */
    private class UDPClient implements Client {
        private DatagramSocket datagramSocket;

        public UDPClient() {
            try {
                datagramSocket = new DatagramSocket();
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }

        public String request(String destIp, int destPort, Message message) {
            try {
                InetAddress ip = InetAddress.getByName(destIp);
                DatagramPacket request = new DatagramPacket(message.getBody(), message.getLength(), ip, destPort);
                datagramSocket.send(request);
                return "OK";
            } catch (SocketException e) {
                System.out.println("Socket: " + e.getMessage());
                return "SocketException";
            } catch (IOException e) {
                System.out.println("IO: " + e.getMessage());
                return "IOException";
            } finally {
                if (datagramSocket != null)
                    datagramSocket.close();
            }
        }

        public String response() {
            try {
                byte[] buffer = new byte[1000];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(reply);
                System.out.println("Reply: " + new String(reply.getData()));
                return "OK";
            } catch (SocketException e) {
                System.out.println("Socket: " + e.getMessage());
                return "SocketException";
            } catch (IOException e) {
                System.out.println("IO: " + e.getMessage());
                return "IOException";
            } finally {
                if (datagramSocket != null)
                    datagramSocket.close();
            }
        }
    }

    /***
     * @version 1.0
     */
    private class UDPServer {

    }

    /***
     * @version 1.0
     */
    private class TCPClient implements Client {

        public String request(String ip, int port, Message message) {
            return null;
        }

        public String response() {
            return null;
        }
    }

    /***
     * @version 1.0
     */
    private class TCPServer{

        public String request(String ip, int port, Message message) {
            return null;
        }

        public String response() {
            return null;
        }
    }
}
