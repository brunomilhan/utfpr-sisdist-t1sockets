package br.utfpr.sockets.app;

import br.utfpr.sockets.conn.Multicast;
import br.utfpr.sockets.model.Message;

/**
 * Created by bruno on 08/09/15.
 */
public class ClientApp implements Runnable {
    private static final int MULTICAST_PORT = 4000;

    private Multicast multicast;
    public ClientApp(){
        // init and create multicast connection
        multicast = new Multicast(MULTICAST_PORT);

    }

    public void run() {
        sendKeepAlive();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Private Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void sendKeepAlive(){
        multicast.request(new Message("KEEPALIVE"));
    }






}
