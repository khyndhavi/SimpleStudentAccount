<!-- login.html -->
  <!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.0 Transitional//EN">
  <html>
  <head>
    <meta http-equiv="Pragma" content="no-cache">
    <title>Security FVT Login Page </title>
  </head>

  <body>

    <h2>Form Login</h2>

    <form method="post" action="loginSubmit">
      <p>
        <strong>You may have entered an invalid user ID
          or password. To correct the problem, please enter
          your correct user ID and password. If you have
          forgotten your user ID or password, please contact
          the server administrator.</strong>
      </p>
      <p>
        <strong>Please enter user ID and password:</strong>
        <br>
        <strong>User ID</strong>
        <input type="text" size="20" name="j_username">
        <strong>Password</strong>
        <input type="password" size="20" name="j_password">
      </p>

      <p>
        <strong>And then click this button:</strong>
        <input type="submit" name="login" value="Login">
      </p>
    </form>

  </body>
  </html>