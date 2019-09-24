package com.ucap.igsp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@SpringBootApplication
public class RibbonServer {

    private static Logger logger = LoggerFactory.getLogger(RibbonServer.class);
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public IRule ribbonRule() {
		return new RandomRule();
	}

	public static void main(String[] args) {
        SpringApplication.run(RibbonServer.class, args);
        logger.info("负载均衡服务启动成功！");
	}
}
