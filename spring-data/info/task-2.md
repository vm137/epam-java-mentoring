## Spring Testing homework

1. Turn booking service controllers into REST endpoints, returning domain objects directly intead of ModelAndView objects (3 points)
2. Implement asynchronous ticket booking. Create JMS consumer which will listen to a particular queue, receive booking messages and process them by adding appropriate database records. (3 points)
3. Configure Spring JMS infrastructure (1 point)
4. Create integration tests that verify asynchronous booking, mock JMS provider using at least 2 of the approaches shown during last Spring Test lecture. (3 points)

Extra mile:
Implement BDD-style unit tests using one of the BDD testing frameworks.
Use dynamic JVM language of your choice to implement BDD tests.
