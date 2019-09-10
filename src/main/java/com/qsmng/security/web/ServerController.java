package com.qsmng.security.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qsmng.security.linux.server.ServerService;

@Controller
public class ServerController {

	private static Logger log = LoggerFactory.getLogger(ServerController.class);

	@Autowired
	private ServerService service;

	private static final String MESSAGE_TAG = "message";

	@GetMapping("admin/shutdown")
	public ModelAndView shutdown() {
		ModelAndView model = new ModelAndView();
		try {
			service.shutdown();
			model.addObject(MESSAGE_TAG, "success");

		} catch (InterruptedException | IOException e) {
			model.addObject(MESSAGE_TAG, "error" + e.getMessage());
			log.error("Interrupted:", e);

			// Restore interrupted state...
			Thread.currentThread().interrupt();

		}

		model.setViewName("admin/server/viewPage");
		return model;
	}

	@GetMapping("admin/restart")
	public ModelAndView restart() {
		ModelAndView model = new ModelAndView();
		try {
			service.restart();
			model.addObject(MESSAGE_TAG, "success");

		} catch (InterruptedException | IOException e) {
			model.addObject(MESSAGE_TAG, "error" + e.getMessage());
			log.error("Interrupted:", e);

			// Restore interrupted state...
			Thread.currentThread().interrupt();
		}

		model.setViewName("admin/server/viewPage");
		return model;
	}
}
