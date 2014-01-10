<!DOCTYPE html>
<html>
  <head>
    <title>Flight Reservation System - Admin</title>
  </head>
  <body>
    <h1>Flight Reservation System</h1>
    <h2>Administration</h2>

    <!-- Forward processing to AdminServlet -->
    <form action="admin" method="POST">
      <b>Update the database:</b>
      <button type="submit" name="operation" value="update">
        Update
      </button>
      <br/>
    </form>
  </body>
</html>
