package it.objectway.stage.demospringboot.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DemoController {
	@RequestMapping("/")
	public String hello(){

		return "index";
	}
}
