<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <title>Flight Reservation System</title>
  </head>
  <body>
    <h1>Flight Reservation System</h1>

    <!-- Forward processing to MainServlet -->
    <form action="handler" method="POST">
      Travel from: <input type="text" name="from"/>
      Travel to: <input type="text" name="to"/>
      <br/>
      <button type="submit" name="search" value="search">
        Search Flights
      </button>
    </form>
  </body>
</html>
