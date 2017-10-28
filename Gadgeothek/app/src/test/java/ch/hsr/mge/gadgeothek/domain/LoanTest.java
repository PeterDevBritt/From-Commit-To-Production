package ch.hsr.mge.gadgeothek.domain;

import junit.framework.Assert;
import org.junit.Test;
import java.util.Date;

/**
 * Created by peter on 28.10.17.
 */

public class LoanTest {

    @Test
    public void isOverdueTest() {

        Loan loan = new Loan("123", new Gadget("iPhone"), new Date(), new Date());

        boolean isOverdue = loan.isOverdue();

        Assert.assertEquals(isOverdue, false);
    }
}
