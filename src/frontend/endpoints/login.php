<?php
session_start();
include("../modules/utilities.php");

if(isset($_SESSION['admin']) && !empty($_SESSION['admin'])) {
    header("Location: ../admin-rides");
    exit();
} else if(isset($_SESSION['login']) && !empty($_SESSION['login'])) {
    header("Location: ../drive");
    exit();
}

if(!isset($_POST['login']) || !isset($_POST['password'])) {
    header("location: ../");
    exit();
}

$url = 'http://localhost:8080/sign-in';
$req = [
    'login' => $_POST['login'],
    'password' => $_POST['password']
];

$result = utilities::post($url, $req);
$result = json_decode($result, true);
if(isset($result['result']) && $result['result'] == 'user') {
    $_SESSION['login'] = $_POST['login'];
} else if(isset($result['result']) && $result['result'] == 'admin') {
    $_SESSION['admin'] = $_POST['login'];
} else if(isset($result['result'])) {
    $_SESSION['login-info'] = $result['result'];
} else {
    $_SESSION['login-info'] = "Błąd połączenia z bazą, spróbuj ponownie później!";
}

header("Location: ../");
exit();