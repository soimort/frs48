<!DOCTYPE html>
<html>
  <head>
    <title>Flight Reservation System</title>
  </head>
  <body>
    <h1>Flight Reservation System</h1>

    <!-- Forward processing to HandlerServlet -->
    <form action="handler" method="POST">
      Airport Code: <input type="text" name="airportCode"/>
      <select name="select">
        <option value="inbound">Inbound</option>
        <option value="outbound">Outbound</option>
      </select>
      <br/>
      <b>Show All Inbound/Outbound Flights:</b>
      <button type="submit" name="query" value="show">
        Show
      </button>
    </form>
  </body>
</html>
