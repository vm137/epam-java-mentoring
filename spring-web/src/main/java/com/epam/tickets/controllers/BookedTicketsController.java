package com.epam.tickets.controllers;

import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;

/**
 * PDF page is not ready yet.
 */

@Controller
@RequestMapping
public class BookedTicketsController {

  @GetMapping("/booked-tickets")
  public Document index(HttpServletResponse response,
      @RequestParam(required = false, defaultValue = "5") String userId) {

    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "attachment:filename=report.pdf");
    return null;
  }
}

// https://zetcode.com/springboot/servepdf/