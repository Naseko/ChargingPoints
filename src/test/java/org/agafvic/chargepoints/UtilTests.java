package org.agafvic.chargepoints;

import org.agafvic.chargepoints.utils.MeterUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilTests {

    @Test
    void testCalcOne() {
        MeterUtils utils = new MeterUtils();
        Assertions.assertNotNull(utils.mockMeterValue());
    }
}
