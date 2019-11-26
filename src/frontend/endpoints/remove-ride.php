<?php
include("../modules/utilities.php");
session_start();

if(isset($_SESSION['login']) && !empty($_SESSION['login'])) {
    header("Location: ../drive");
    exit();
} else if(!isset($_SESSION['admin']) || empty($_SESSION['admin'])) {
    header("Location: ../");
    exit();
}

if(!isset($_GET['id']) || empty(isset($_GET['id']))) {
    header("Location: ../admin-rides");
}

$url = 'http://localhost:8080/admin/delete-trip';
$req = [
    'rideId' => $_GET['id'],
    'request_login' => $_SESSION['admin']
];

$result = utilities::post($url, $req);
$result = json_decode($result, true);

$success = isset($result['success']) ? $result['success'] : false;
$msg = isset($result['message']) ? $result['message'] : 'Błąd serwera!';

echo '<a href="../admin-rides" class="btn btn-dark mb-3">&larr; Powrót do listy przejazdów</a>';

if($success) {
    echo '<div class="alert alert-info">Przejazd został usunięty!</div>';
} else {
    echo '<div class="alert alert-danger">'.$msg.'</div>';
}

echo <<<EOF
<style>
    @import "../css/bootstrap.css";
    body {
    padding: 20px;
    }
</style>

EOF;
