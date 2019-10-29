<?php
include("../modules/utilities.php");
session_start();

if(isset($_SESSION['admin']) && !empty($_SESSION['admin'])) {
    header("Location: ../admin-rides");
    exit();
} else if(!isset($_SESSION['login']) || empty($_SESSION['login'])) {
    header("Location: ../");
    exit();
}

if(!isset($_POST['rideId'])) {
    header("Location: ../your-rides");
    exit();
}

$url = 'http://localhost:8080/remove-ride';
$req = [
    'login' => $_SESSION['login'],
    'rideId' => $_POST['rideId'].'1'
];

$result = utilities::post($url, $req);
$result = json_decode($result, true);

if(isset($result['result']) && $result['result'] == 'success') {
    echo 'Twoje miejsce zostało zwolnione!';
} else if(isset($result['result'])) {
    echo $result['message'];
} else {
    echo 'Błąd połączenia z serwerem!';
}