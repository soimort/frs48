# Flight Reservation System in 48 Hours

Flight Reservation System in 48 Hours, implemented in Java EE -- I gotta get shit done (as a course assignment).

## Log

- **Hour 1**: Kickoff; Get my web app running on Tomcat, showing a perfect useless hello world page:)
- **Hour 2**: Get first Servlet working
- **Hour 3**: Let JSPs & Servlets interact
- **Hour 4**: More UI
- **Hour 5~12**: Have a good day's sleep and dream
- **Hour 13~17**: Go to the supermarket; Have breakdinner; Take a shower
- **Hour 18**: More Servlets
- **Hour 19**: Learn everything about EJB
- **Hour 20**: Struggle with Apache TomEE
- **Hour 21**: Try the TomEE standalone server `apache-tomee-1.6.0-plus.tar.gz`
- **Hour 22**: Stick with the TomEE standalone server
- **Hour 23**: Try a hello world EJB
- **Hour 24**: Let Servlets call EJBs
- **Hour 25**: Just play
- **Hour 26~34**: Get more-than-enough sleep
- **Hour 35~39**: Have dinner and take a shower
- **Hour 40-41**: Just play
- **Hour 42**: Design the 3-tier structure of the web app
- **Hour 43~48**: Struggle with the Hibernate shit. Never get a luck.

OK I think now it's the time to give up on Hibernate. No matter what I do, it keeps throwing me a lot of shit like:

```
java.lang.NullPointerException
at org.hibernate.engine.transaction.internal.jta.JtaStatusHelper.getStatus(JtaStatusHelper.java:76)
```

OpenJPA bundled with TomEE works pretty well for me. Will stick with that one. Hibernate is listed in the requirements but who cares. 48 hours have passed and I really want to get shit done.

- **Hour 49**: Get OpenJPA hello world working
- **Hour 50**: Implement Airline, Airport & Route Entities
- **Hour 51**: More JPA
- **Hour 52**: Struggle with `javax.naming.NameNotFoundException`
- **Hour 53**: Struggle with CSV parsing
- **Hour 54**: CSV parsing basically works
- **Hour 55**: Continue with JPA
- **Hour 56~60**: Get enough sleep
- **Hour 61~64**: JNDI name lookup and Tomcat auto-deployment stop working and recover for no reason. Fuck off
- **Hour 65~68**: Finish all 3-tier components of Part I
