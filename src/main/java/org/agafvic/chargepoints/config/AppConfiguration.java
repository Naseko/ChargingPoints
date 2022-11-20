package org.agafvic.chargepoints.config;

import org.agafvic.chargepoints.model.VersionInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.agafvic.chargepoints.config.Constants.INFO_VER_APP;
import static org.agafvic.chargepoints.config.Constants.INFO_VER_DB;

@Configuration
public class AppConfiguration {

    @Value(INFO_VER_APP)
    private String appVersion;

    @Value(INFO_VER_DB)
    private String dbVersion;

    public AppConfiguration() {
    }

    @Bean @Scope("singleton")
    public VersionInfo versionInfo() {
        return new VersionInfo(appVersion,dbVersion);
    }

//    @Bean
//    public SpringLiquibase migrations(DataSource dataSource) {
//        final var migrations = new SpringLiquibase();
//        migrations.setChangeLog("classpath:migrations/changelog.xml");
//        migrations.setDataSource(dataSource);
//        return migrations;
//    }
}
