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

$url = 'http://localhost:8080/admin/users';
$req = $_POST;
$req['request_login'] = $_SESSION['admin'];

$result = utilities::post($url, $req);
$result = json_decode($result, true);

if(isset($result['users'])) {
    echo generateTable($result);
} else {
    echo '<div class="alert alert-danger mb-0">Błąd serwera!</div>';
}


function generateTable($result) {
    $thead = '<thead><tr><td>#</td><td>Login</td><td>Telefon</td><td>Akcje</td></tr></thead>';
    $tbody = '';

    if(count($result['users']) == 0) {
        return '<div class="alert alert-warning mb-0">Nie ma żadnego użytkownika!</div>';
    }

    foreach($result['users'] as $ride) {
        $enabledText = $ride['enabled'] ? 'Zablokuj' : 'Odblokuj';
        $classRow = $ride['enabled'] ? '' : 'bg-danger';
        $enableLink = $ride['enabled'] ? 0 : 1;
        $row = "<tr class='$classRow'>
            <td>{$ride['userID']}</td>
            <td>{$ride['login']}</td>
            <td>{$ride['phone_number']}</td>
            <td><a href='endpoints/remove-user?login={$ride['login']}&toEnable={$enableLink}'>{$enabledText}</a></td>
        </tr>";

        $tbody .= $row;
    }
    return $thead.$tbody;
}