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

$url = 'http://localhost:8080/admin/all-trips';
$req = $_POST;
$req['request_login'] = $_SESSION['admin'];

$result = utilities::post($url, $req);
$result = json_decode($result, true);

if(isset($result['result']) && !$result['result']) {
    echo '<div class="alert alert-danger mb-0">' . $result['message'] . '</div>';
} else if(isset($result['result']) && $result['result']) {
    echo generateTable($result);
} else {
    echo '<div class="alert alert-danger mb-0">Błąd serwera!</div>';
}


function generateTable($result) {
    $thead = '<thead><tr><td>#</td><td>Kierowca</td><td>Telefon kierowcy</td><td class="track">Trasa</td><td>Pasażerowie</td><td>Akcje</td></tr></thead>';
    $tbody = '';

    if(count($result['rides']) == 0) {
        return '<div class="alert alert-warning mb-0">Nie ma żadnych przejazdów!</div>';
    }

    foreach($result['rides'] as $ride) {
        $clients = '';
        foreach($ride['clients'] as $client) {
            $clients .= $client['login'] . '('. $client['from'] . '-' . $client['to'] . ' : ' . $client['price'] . 'zł)<br>';
        }
        $row = "<tr>
            <td>{$ride['id']}</td>
            <td>{$ride['driver']['login']}</td>
            <td>{$ride['driver']['phone']}</td>
            <td class='track'>{$ride['road']}</td>
            <td>{$clients}</td>
            <td><a href='endpoints/remove-ride?id={$ride['id']}'>Usuń</a></td>
        </tr>";

        $tbody .= $row;
    }
    return $thead.$tbody;
}