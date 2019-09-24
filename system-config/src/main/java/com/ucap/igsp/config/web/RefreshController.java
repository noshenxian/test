package com.ucap.igsp.config.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/** 手动刷新配置 */
@RestController
public class RefreshController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private DiscoveryClient client;

	/** 刷新全部子服务中配置 */
	@RequestMapping(value = "refreshConfig", method = RequestMethod.GET)
	public String refreshConfig() {
		RestTemplate restTemplate = new RestTemplate();
		List<String> serviceNameList = client.getServices();
		for (String serviceName : serviceNameList) {
			List<ServiceInstance> serviceList = client.getInstances(serviceName);
			for (ServiceInstance instance : serviceList) {
				try {
					restTemplate.postForLocation("http://" + instance.getHost() + ":" + instance.getPort() + "/refresh", null);
					logger.info(instance.getHost() + ":" + instance.getPort() + ":更新配置成功！");
				} catch (Exception e) {
					System.out.println(1111);
				}
			}
		}
		return "更新配置成功！";
	}

	/** 刷新某个子服务中配置 */
	@RequestMapping(value = "refreshConfigByHostAndPort", method = RequestMethod.GET)
	public String refreshConfig(String host, String port) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.postForLocation("http://" + host + ":" + port + "/refresh", null);
			logger.info(host + ":" + port + ":更新配置成功！");
		} catch (Exception e) {
			System.out.println(1111);
		}
		return "更新配置成功！";
	}

}