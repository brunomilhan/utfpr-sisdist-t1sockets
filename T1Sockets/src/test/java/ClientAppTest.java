import br.utfpr.sockets.app.ClientApp;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by bruno on 08/09/15.
 */
public class ClientAppTest {


    @Test
    public void keepAliveTest(){
        ClientApp app = new ClientApp();
        Thread appThread = new Thread(app);
        appThread.run();


    }
}
