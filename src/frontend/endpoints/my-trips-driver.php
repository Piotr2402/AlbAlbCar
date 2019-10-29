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

$url = 'http://localhost:8080/created-trips';
$req = [
    'login' => $_SESSION['login']
];

$result = utilities::post($url, $req);
$result = json_decode($result, true);

$html = '';

foreach($result as $drive) {
    $clientString = '';
    foreach($drive['clients'] as $client) {
        $clientString .= $client['login'].' ('.$client['from'].' - '.$client['to'].') [<a href="tel:'.$client['phone'].'">'.$client['phone'].'</a>]: '.$client['price'].'zł<br>';
    }
    if(!empty($clientString)) {
        $clientString = '<p><span class="font-weight-bold">Uczestnicy:</span><br>'.$clientString.'</p>';
    }

    $html .= '
       <div class="ride">
            <div class="d-flex flex-column">
                <h3>#'.$drive['id'].'</h3>
                <p class="font-weight-bold">'.$drive['road'].'</p>
                <p>Wolnych miejsc: '.$drive['seats'].'</p>'.
                 $clientString.'
            </div>
        </div>
    ';
}

if(empty($html)) $html = '<p class="mb-0">Brak dostępnych przejazdów!</p>';
echo $html;
