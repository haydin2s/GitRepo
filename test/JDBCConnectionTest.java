import java.util.logging.Level;
import java.util.logging.Logger;
import mygitproject.DatabaseException;
import mygitproject.JDBCConnection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aydins
 */
public class JDBCConnectionTest {
    JDBCConnection con;
    
    public JDBCConnectionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void test() {
        try {
            con = JDBCConnection.getInstance();
        } catch (DatabaseException ex) {
            Logger.getLogger(JDBCConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
