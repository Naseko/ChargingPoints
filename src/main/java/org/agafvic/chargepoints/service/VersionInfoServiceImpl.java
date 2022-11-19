package org.agafvic.chargepoints.service;

import org.agafvic.chargepoints.model.VersionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VersionInfoServiceImpl implements VersionInfoService {
    protected final VersionInfo versionInfo;

    @Autowired
    public VersionInfoServiceImpl(VersionInfo versionInfo) {
        this.versionInfo = versionInfo;
    }

    @Override
    public VersionInfo getVersionInfo() {
        return versionInfo;
    }
}