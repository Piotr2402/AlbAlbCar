<?php
session_start();
include("../modules/utilities.php");

if(!isset($_POST['login'])) {
    header("location: ../index.php");
    exit();
}

$url = 'http://localhost:8080/admin-users';
$req = [
    'login' => $_POST['login']
];

$result = utilities::post($url, $req);

echo $result;