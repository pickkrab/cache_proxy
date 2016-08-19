package ru.sbt.proxy_test;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.cacheProxy.CacheProxy;
import ru.sbt.calculate.Calculator;
import ru.sbt.calculate.CalculatorImpl;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class CacheProxyTest {

    private Calculator calculator;

    @Before
    public void setUp() throws Exception {
        CalculatorImpl calc = new CalculatorImpl();
        calculator = CacheProxy.cache(calc);
    }

    @Test(timeout = 9000)
    public void False() {
        calculator.doHardWork(2, 4, "вася");
    }

    @Test
    public void list_() {
        List<Integer> list = calculator.doList1(45, "петя");
        assertEquals(list.size(), 3000);
    }

    @Test(timeout = 6000)
    public void proverka() {
        calculator.doHardWork(5, 5);
    }

}
