package com.company.fs.analytics.restapi.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.company.fs.analytics.restapi.beans.DataAcknowledgement;

/**
 * @GetMapping annotation maps HTTP GET requests onto specific handler methods. It is a composed 
 * annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.GET).
 *
 * The rest end-point is accessible by http://<hostname:port>/transaction?ts=<timestamp>
 * 
 */

@Controller
public class TransactionController {

	private static final Logger log = LoggerFactory.getLogger(TransactionController.class);

	@GetMapping("/transaction")
	@ResponseBody
	public ResponseEntity<List<DataAcknowledgement>> retrieveTransactionData(
			@RequestParam(name = "ts", required = false, defaultValue = "Rest API") String name) {

		log.info("TransactionController: The time-stamp received as paramet " + name);
		DatabaseConnector databaseConnector = new DatabaseConnector();

		List<DataAcknowledgement> dataAcks = databaseConnector.getData(name);
		log.info("DatabaseConnector.getData() invocation result " + dataAcks.size());
		return new ResponseEntity<List<DataAcknowledgement>>(dataAcks, HttpStatus.OK);
	}
}
