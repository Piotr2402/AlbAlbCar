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

if(!isset($_POST['assembly_place']) || !isset($_POST['destination_place']) || !isset($_POST['departure_datetime']) || count($_POST) != 3) {
    header("Location: ../drive");
    exit();
}

$url = 'http://localhost:8080/search-trip';
$req = $_POST;

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
            <div class="ride-reservation btn btn-dark" data-rideid="'.$ride['id'].'">Rezerwuj</div>
        </div>
    </div>';
}

if(empty($html)) $html = '<p class="mb-0">Brak dostępnych przejazdów między tymi miastami w dany dzień!</p>';

echo $html;

