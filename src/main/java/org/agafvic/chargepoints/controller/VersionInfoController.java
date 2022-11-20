package org.agafvic.chargepoints.controller;

import org.agafvic.chargepoints.dto.VersionInfoDto;
import org.agafvic.chargepoints.service.VersionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionInfoController {

    @Autowired
    VersionInfoService versionInfoService;

    @GetMapping("/version")
    public VersionInfoDto getInfo() {
        return versionInfoService.getVersionInfo();
    }
}