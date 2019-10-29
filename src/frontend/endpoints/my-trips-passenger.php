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

$url = 'http://localhost:8080/participated-trips';
$req = [
    'login' => $_SESSION['login']
];

$result = utilities::post($url, $req);
$result = json_decode($result, true);

$html = '';

foreach($result as $drive) {
    $cancel = '';
    if($drive['canDelete'] == 1) {
        $cancel = '<div class="btn btn-danger mt-2 cancel-ride" data-rideid="'.$drive['id'].'"><span class="d-none"></span>Anuluj przejazd</div>';
    }

    $html .= '
        <div class="ride">
            <div class="d-flex flex-column">
                <h3>'.$drive['date'].'</h3>
                <p class="font-weight-bold">'.$drive['road'].'</p>
                <p>Kierowca: '.$drive['driverLogin'].' <a href="tel:'.$drive['driverPhone'].'">['.$drive['driverPhone'].']</a></p>
                <p class="font-weight-bold">Cena: '.$drive['price'].'zł</p>'.$cancel.'
            </div>
        </div>
    ';
}

if(empty($html)) $html = '<p class="mb-0">Brak dostępnych przejazdów!</p>';

echo $html;
