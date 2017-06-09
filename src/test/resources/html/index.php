<?php
   session_start()
?>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>"Hello World"</title>
    <style>
      
      li:nth-of-type(2) { border: solid 1px red}

      li[my-data='uno'] { color: red}
      li[my-data='duo'] { color: blue}

      *:valid {border: solid 1px green;}
      *:invalid {border: solid 1px red;}

      li[text="3.3"] {color: green}
      
    </style>
  </head>
  <body>
    <header>
      <h1 class="heading">Header</h1>
    </header>
    <ol>
      <li class="first" my-data="uno">Item 1</li>
      <li class="rest" my-data="duo">Item 2</li>
      <li class="rest">Item 3
	<ul>
	  <li class="first">3.1</li>
	  <li class="rest">3.2</li>
	  <li class="rest">3.3</li>
	  <li class="last">3.4</li>
	</ul>
      </li>
      <li class="rest">Item 4</li>
      <li class="last">Item 5</li>
    </ol>
    <form action="info.html">
      <label for="firstname" required>First name: </label>
      <input type="text" name="firstname"><br>
      <label for="lastname">Last name:</label>
      <input type="text" name="lastname"><br>
      <label for="age">Age:</label>
      <input type="number" name="age" min="18" max="30">
      <input type="submit">
    </form>
    <p><a href="offices.html">Our Offices</a></p>
    <p><a href="profile.php">Your Profile</a></p>
    <p><a href="accounts.php">Your Accounts</a></p>
    <?php
       if (isset($_SESSION['login_user'])) {
          echo '<p>You are logged in as ';
          echo $_SESSION['login_user'];
          echo ' <a id="login" class="LoggedIn" href="logout.php">Logout</a></p>';
       } else {
          echo '<p><a id="login" class="LoggedOut" href="login_page.php">Login</a></p>';
       }
       ?>
  </body>
</html>
