### Create Spring based module, which handles event tickets booking.
Based on attached source code model:

1. Implement three service classes:  
UserService, EventService, TicketService which should contain user, event and booking related functionality accordingly.  
Create implementation of "BookingFacade" interface which should delegate method calls to com.epam.tickets.services 
mentioned above. (0.5 point)

2. Configure spring application context based on xml config file. (0.5 point)

3. Implement DAO objects for each of the domain model entities (User, Event, Ticket). 
   They should store in and retrieve data from a common in-memory storage - java map. 
   Each entity should be stored under separate namespace, so you could list particular entity types. (0.5 point)

Example for a ticket - map entry { "ticket:`<ticketId>`" :  {`<Ticket object>`}}  
E.g. {"ticket:12345" :  { "id" : "12345", "place" : "23" ...} }

4. Storage should be implemented as a separate spring bean. 
   Implement ability to initialize storage with some prepared data from a file during the application start 
   (use spring bean post-processing features). 
   Path to the concrete file should be set using property placeholder and external property file. (1 point)

5. DAO with storage bean should be inserted into com.epam.tickets.services beans using autowiring.
   Services beans should be injected into facade using constructor-based injections. Rest of the
   injections should be done in a setter-based way. (1 point)

6. Cover code with unit tests. (0.5 point)

7. Code should contain proper logging. (0.5 point)

8. Create several integration tests that instantiate the Application Context directly, lookup facade
   bean and perform some real-life scenario (e.g. create user, then create event, then book ticket
   for this event for the user, then cancel it). (0.5 point)

Extra mile (optional, should be done when previous items are complete) (2 points):

9. Use "p" namespace to define properties

10. Use SLF4J API for logging, configure Spring to use SLF4J and add some Spring logging output to
    common application log. 
    