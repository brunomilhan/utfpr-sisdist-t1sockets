import br.utfpr.sockets.conn.Unicast;
import br.utfpr.sockets.model.Message;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bruno on 08/09/15.
 */
public class UnicastTest {

    @Test
    public void testUnicastReqUDPClient() {
        Unicast unicast = new Unicast(true, true);
        String returned = unicast.request("127.0.0.1", 6789, new Message("teste"));
        assertEquals("OK", returned);
    }


}
