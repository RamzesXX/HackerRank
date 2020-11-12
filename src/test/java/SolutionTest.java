import static org.junit.Assert.*;

import org.junit.Test;

public class SolutionTest {

    @Test
    public void biggerIsGreater_ab() {
        assertEquals("ba", Solution.biggerIsGreater("ab"));

    }

    @Test
    public void biggerIsGreater_bb() {
        assertEquals("no answer", Solution.biggerIsGreater("bb"));

    }

    @Test
    public void biggerIsGreater_hefg() {
        assertEquals("hegf", Solution.biggerIsGreater("hefg"));

    }

    @Test
    public void biggerIsGreater_dhck() {
        assertEquals("dhkc", Solution.biggerIsGreater("dhck"));

    }

    @Test
    public void biggerIsGreater_dkhc() {
        assertEquals("hcdk", Solution.biggerIsGreater("dkhc"));

    }

    @Test
    public void biggerIsGreater_lmno() {
        assertEquals("lmon", Solution.biggerIsGreater("lmno"));

    }

    @Test
    public void biggerIsGreater_dcba() {
        assertEquals("gkaa", Solution.biggerIsGreater("gaka"));

    }
}