package org.agafvic.chargepoints.service;

import org.agafvic.chargepoints.dto.VersionInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VersionInfoServiceImpl implements VersionInfoService {
    protected final VersionInfoDto versionInfoDTO;

    @Autowired
    public VersionInfoServiceImpl(VersionInfoDto versionInfoDTO) {
        this.versionInfoDTO = versionInfoDTO;
    }

    @Override
    public VersionInfoDto getVersionInfo() {
        return versionInfoDTO;
    }
}