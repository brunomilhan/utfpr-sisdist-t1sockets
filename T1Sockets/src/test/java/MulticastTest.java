import br.utfpr.sockets.conn.Multicast;
import br.utfpr.sockets.model.Message;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by bruno on 08/09/15.
 */
public class MulticastTest {

    @Test
    public void testRequestGroup(){
        Multicast multicast = new Multicast(4000);
        assertEquals("OK", multicast.request(new Message("teste")));
    }

    @Test
    public void testLeaveGroup(){
        Multicast multicast = new Multicast(4000);
        assertEquals("OK", multicast.leaveGroup());
    }

}
