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

$url = 'http://localhost:8080/book-a-ride';
$req = $_POST;
$req['login'] = $_SESSION['login'];

$result = utilities::post($url, $req);
$result = json_decode($result, true);

if(isset($result['result']) && $result['result'] == 'success') {
    echo 'Zostałeś zapisany na przejazd!';
} else if(isset($result['result'])) {
    echo $result['result'];
} else {
    echo 'Błąd serwera!';
}