package main;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by genslerj on 4/10/16.
 */
public class MainTest {

    @Test
    public void testSupplyingTooFewArguementsThrowsExceptionContainingError() {
        try {
            Main.main(new String[]{""});
        } catch (Exception e) {
            assert(true);
        }
        assert(false);
    }
}