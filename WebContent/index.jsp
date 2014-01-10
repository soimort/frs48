<!DOCTYPE html>
<html>
  <head>
    <title>Flight Reservation System</title>
  </head>
  <body>
    <h1>Flight Reservation System</h1>

    <!-- Forward processing to HandlerServlet -->
    <form action="handler" method="POST">
      Travel From: <input type="text" name="from"/>
      To: <input type="text" name="to"/>
      <br/>
      <button type="submit" name="query" value="search">
        Search Flights
      </button>
      <br/>
      <button type="submit" name="query" value="distance">
        Distance
      </button>
      <br/>
      <button type="submit" name="query" value="altitude">
        Altitude
      </button>
      <button type="submit" name="query" value="geography">
        Geography
      </button>
      <br/>
    </form>
  </body>
</html>
