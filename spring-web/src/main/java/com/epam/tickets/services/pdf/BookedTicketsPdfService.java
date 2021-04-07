package com.epam.tickets.services.pdf;

import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.model.dto.Ticket;
import com.epam.tickets.services.EventService;
import com.epam.tickets.services.TicketService;
import com.epam.tickets.services.UserService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookedTicketsPdfService {

  @Autowired
  TicketService ticketService;

  @Autowired
  EventService eventService;

  @Autowired
  UserService userService;

  private static final Logger logger = LogManager.getLogger(BookedTicketsPdfService.class);


  public ByteArrayInputStream getPdfReport() {
    Document document = new Document();
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    List<Ticket> tickets = ticketService.getAllTickets();

    try {
      PdfPTable table = new PdfPTable(3);
      table.setWidthPercentage(60);
      table.setWidths(new int[]{1, 3, 3});

      Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

      PdfPCell hcell;
      hcell = new PdfPCell(new Phrase("Id", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      hcell = new PdfPCell(new Phrase("Event", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      hcell = new PdfPCell(new Phrase("Person", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      for (Ticket ticket : tickets) {
        PdfPCell cell;

        cell = new PdfPCell(new Phrase(ticket.getId().toString()));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        String title = eventService.getEventById(ticket.getEventId()).getTitle();
        cell = new PdfPCell(new Phrase(title));
        cell.setPaddingLeft(5);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        String name = userService.getUserById(ticket.getUserId()).getName();
        cell = new PdfPCell(new Phrase(String.valueOf(name)));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPaddingRight(5);
        table.addCell(cell);
      }

      PdfWriter.getInstance(document, out);
      document.open();
      document.add(table);

      document.close();
    } catch (DocumentException | InvalidUserException ex) {
      logger.error("PDF creation error");
    }

    return new ByteArrayInputStream(out.toByteArray());
  }
}
