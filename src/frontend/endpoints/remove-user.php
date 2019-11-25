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

if(!isset($_GET['login']) || empty($_GET['login']) || !isset($_GET['toEnable'])) {
    header("Location: ../admin-users");
}

if($_GET['toEnable'] > 0) {
    $url = 'http://localhost:8080/admin/unblock-user';
    $req = [
        'unblock_login' => $_GET['login'],
        'request_login' => $_SESSION['admin']
    ];
} else {
    $url = 'http://localhost:8080/admin/block-user';
    $req = [
        'block_login' => $_GET['login'],
        'request_login' => $_SESSION['admin']
    ];
}

$result = utilities::post($url, $req);
$result = json_decode($result, true);

$success = isset($result['success']) ? $result['success'] : false;
$msg = isset($result['message']) ? $result['message'] : 'Błąd serwera!';

echo '<a href="../admin-users" class="btn btn-dark mb-3">&larr; Powrót do listy użytkowników</a>';

if($success) {
    echo '<div class="alert alert-info">Zmieniono status użytkownika '.$_GET['login'].'!</div>';
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
