<?php
  // Establishing Connection with Server by passing server_name, user_id and password as a parameter
  session_start();// Starting Session
  // Storing Session
  $user_check=$_SESSION['login_user'];
  $login_session =$user_check;
  if(!isset($login_session)){
    header("Location: login_page.php?location=" . urlencode($_SERVER['REQUEST_URI']));
  }
?>