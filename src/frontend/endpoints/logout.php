<?php
session_start();

unset($_SESSION['login']);
unset($_SESSION['admin']);

header("Location: ../");
exit();