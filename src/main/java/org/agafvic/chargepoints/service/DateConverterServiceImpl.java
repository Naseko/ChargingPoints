package org.agafvic.chargepoints.service;

import lombok.SneakyThrows;
import org.agafvic.chargepoints.dto.PatternDto;
import org.agafvic.chargepoints.mapper.PatternMapper;
import org.agafvic.chargepoints.repository.PatternEntity;
import org.agafvic.chargepoints.utils.FormattedDateMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static org.agafvic.chargepoints.config.Constants.SYS_ZONE_OFFSET;

@Service
public class DateConverterServiceImpl implements DateConverter {
    @Autowired
    FormattedDateMatcher matcher;
    @Value(SYS_ZONE_OFFSET)
    String zoneOffset;
    @Autowired
    private PatternServiceImpl patternServiceImpl;
    private OffsetDateTime datetime;

    public OffsetDateTime convert(String date) {
        patternServiceImpl.getPatterns().forEach(p -> check(p, date));
        return datetime;
    }

    @SneakyThrows
    private void check(PatternEntity patternEntity, String date) {
        date = date.replace("/", "-");
        PatternDto patterDto = PatternMapper.INSTANCE.mapTo(patternEntity);
        if (matcher.matches(patterDto.getPattern(), date)) {
            if (isEndsWithOffSet(date)) {
                datetime = OffsetDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            } else {
                SimpleDateFormat formatter = new SimpleDateFormat(patterDto.getFormat());
                datetime = formatter.parse(date).toInstant().atOffset(ZoneOffset.of(zoneOffset));
            }
        }
    }

    private boolean isEndsWithOffSet(String date) {
        return matcher.matches("^.*\\+\\d{2}:\\d{2}$", date);
    }
}