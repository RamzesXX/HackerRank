import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SolutionTest {
    private Solution solution;


    @Test
    public void test_calcChecksum_1() {
        solution = new Solution(2, "", "");
        assertEquals(19761398, solution.calcChecksum("LULUR"));
    }

    @Test
    public void test_solve_1() {
        solution = new Solution(2, "WRBB", "RBBW");
        assertEquals(18553, solution.solve());
    }
    @Test
    public void test_solve_2() {
        solution = new Solution(3, "BBBBWRRRR", "RBRBWBRBR");
        assertEquals(86665639, solution.solve());
    }
}