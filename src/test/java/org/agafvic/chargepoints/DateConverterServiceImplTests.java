package org.agafvic.chargepoints;

import org.agafvic.chargepoints.service.DateConverterServiceImpl;
import org.agafvic.chargepoints.utils.FormattedDateMatcherB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.OffsetDateTime;

@SpringBootTest
@AutoConfigureTestDatabase
@RunWith(SpringRunner.class)
@SqlGroup({@Sql(executionPhase =
        Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTest.sql")})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DateConverterServiceImplTests {
    @Autowired DateConverterServiceImpl converter;
    @Autowired
    FormattedDateMatcherB b;
    @Test
    void testDateIsConverted() {
        OffsetDateTime date = converter.convert("2022-12-03");
        Assertions.assertEquals("2022-12-03T00:00+03:00",date.toString());
    }

    @Test
    void testDateInWrongFormat() {
        OffsetDateTime date = converter.convert("2022/12-03");
        Assertions.assertEquals("2022-12-03T00:00+03:00", date.toString());
    }

    @Test
    void testDateWithSlashes() {
        OffsetDateTime date = converter.convert("2022/12/03");
        Assertions.assertEquals("2022-12-03T00:00+03:00",date.toString());
    }

    @Test
    void testDateFullString() {
        OffsetDateTime date = converter.convert("2022-12-03T00:00+03:00");
        Assertions.assertEquals("2022-12-03T00:00+03:00",date.toString());
    }

    @Test
    void testDateFullSlashesString() {
        OffsetDateTime date = converter.convert("2022/12/03T00:00+03:00");
        Assertions.assertEquals("2022-12-03T00:00+03:00",date.toString());
    }

    @Test
    void testMatcher(){
        Assertions.assertTrue(b.matches("^\\d{4}-\\d{2}-\\d{2}$", "2022-12-12"));
    }
}