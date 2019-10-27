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

$html = '';
foreach($result as $ride) {
    $html .= '<div class="ride d-flex flex-column flex-md-row">
        <div class="left-col d-flex flex-column">
            <h3>'.$ride['date'].'</h3>
            <p>'.$ride['driver'].'</p>
            <p class="font-weight-bold">'.$ride['road'].'</p>
            <p>Wolnych miejsc: '.$ride['seats'].'</p>
        </div>
        <div class="right-col d-flex flex-column justify-content-around align-items-end">
            <h4>'.$ride['price'].'zł</h4>
            <div class="ride-reservation btn btn-dark" data-rideid="12">Rezerwuj</div>
        </div>
    </div>';
}

if(empty($html)) $html = '<p>Brak dostępnych przejazdów między tymi miastami w dany dzień!</p>';

echo $html;