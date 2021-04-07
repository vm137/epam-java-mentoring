package com.epam.tickets.controllers.pdf;

import com.epam.tickets.services.pdf.BookedTicketsPdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(produces = MediaType.APPLICATION_PDF_VALUE)
public class PdfTicketsController {

  @Autowired
  BookedTicketsPdfService pdfService;

  @GetMapping("/booked-tickets")
  public ResponseEntity<InputStreamResource> getBookedTicketsPdf() {

    return ResponseEntity
        .ok()
        .contentType(MediaType.APPLICATION_PDF)
        .body(new InputStreamResource(pdfService.getPdfReport()));
  }
}

/*
 * Source:
 * https://zetcode.com/springboot/servepdf/
 */
