package br.utfpr.sockets.conn;

import br.utfpr.sockets.model.Message;

/**
 * Created by bruno on 08/09/15.
 */
public interface Client {
    public String request(String ip, int port, Message message);
    public String response();
}
