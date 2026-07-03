package aquasmart;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SmartMeterTest {

    @Test
    void loadingTokenReopensClosedValve() {
        SmartMeter meter = new SmartMeter("MTR-001", 100); // enough for 2 litres
        meter.recordConsumption(3); // costs 150 > 100 -> closes valve
        assertFalse(meter.isValveOpen());

        meter.loadToken(5000);
        assertTrue(meter.isValveOpen());
    }

    @Test
    void consumptionBeyondCreditClosesValve() {
        SmartMeter meter = new SmartMeter("MTR-002", 100);
        boolean dispensed = meter.recordConsumption(5); // costs 250 > 100
        assertTrue(dispensed);
        assertEquals(0, meter.getCreditBalance());
        assertFalse(meter.isValveOpen());
    }
}