package org.o7planning.sbsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class VATController {
	@RequestMapping(value = "/TaxQuotation", method = RequestMethod.GET)
	public String Vatgeneration() {
		return "VATMainpage";
	}

}
