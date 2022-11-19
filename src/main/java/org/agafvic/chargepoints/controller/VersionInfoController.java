package org.agafvic.chargepoints.controller;

import org.agafvic.chargepoints.service.VersionInfoService;
import org.agafvic.chargepoints.model.VersionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionInfoController {

    @Autowired
    VersionInfoService versionInfoService;

    @GetMapping("/version")
    public VersionInfo getInfo() {
        return versionInfoService.getVersionInfo();
    }
}