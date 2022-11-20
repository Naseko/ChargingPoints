package org.agafvic.chargepoints.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.agafvic.chargepoints.repository.PatternEntity;
import org.agafvic.chargepoints.repository.PatternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Getter
@Setter
public class PatternServiceImpl implements PatternService {
    @Autowired
    private PatternRepository patternRepository;

    public List<PatternEntity> getPatterns() {
        return patternRepository.selectAll();

    }
}