package com.kiprono.views;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Driver {
	private static final Logger LOG = LogManager.getLogger(Driver.class);
	public static void main(String[] args) {
		LOG.trace("Started Execution, Driver");
		MainMenu.ContextMenu();
	}

}
