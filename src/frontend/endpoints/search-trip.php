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

$url = 'http://localhost:8080/search-trip';
$req = [
    'assembly_place' => $_POST['assembly_place'],
    'destination_place' => $_POST['destination_place'],
    'departure_datetime' => $_POST['departure_datetime']
];

$result = utilities::post($url, $req);
$result = json_decode($result, true);

echo print_r($result);