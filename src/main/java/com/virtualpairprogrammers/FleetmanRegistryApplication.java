package com.virtualpairprogrammers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.beans.factory.annotation.Value;

import com.netflix.appinfo.AmazonInfo;

@SpringBootApplication
@EnableEurekaServer
public class FleetmanRegistryApplication {

    @Value("${server.port}")
    private int port;

	public static void main(String[] args) {
		SpringApplication.run(FleetmanRegistryApplication.class, args);
	}

	@Bean
	public EurekaInstanceConfigBean eurekaInstanceConfigBean(InetUtils utils) {

		final EurekaInstanceConfigBean instance = new EurekaInstanceConfigBean(utils);
		AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
		instance.setHostname(info.get(AmazonInfo.MetaDataKey.publicHostname));
		instance.setIpAddress(info.get(AmazonInfo.MetaDataKey.publicIpv4));
		instance.setDataCenterInfo(info);
		instance.setNonSecurePort(port);

		return instance;
	}


}
