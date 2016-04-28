import org.junit.Test;
import pack.protdoc.secure.SecurityCheckService;

/**
 * Created by contest on 28.04.2016.
 */
public class SecurityCheckServiceTest {
    @Test
    public void testCalcHash() {
        System.out.println(SecurityCheckService.calcHash("qwertty"));
    }
}
