package com.rameshj.springbootapps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {


	@Autowired
	private KafkaService kafkaService;


	@GetMapping("/getPublish/{message}")
	public String publishMessage(@PathVariable String message) {

		kafkaService.publishMessage(message);
		System.out.println("SENT THE MESSAGE");
		return "Published Message";
	}

	@PostMapping("/publish")
	public String publish(@RequestBody Product product) {
		kafkaService.publishObject(product);
		return "Published Message Successfully";
	}

}
