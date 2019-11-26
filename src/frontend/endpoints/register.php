<?php
session_start();
include("../modules/utilities.php");

if(isset($_SESSION['admin']) && !empty($_SESSION['admin'])) {
    header("Location: ../admin-rides");
    exit();
} else if(isset($_SESSION['login']) && !empty($_SESSION['login'])) {
    header("Location: ../drive");
    exit();
}

if(!isset($_POST['login']) ||
    !isset($_POST['phone']) ||
    !isset($_POST['password1']) ||
    !isset($_POST['password2'])) {
    header("location: ../index.php");
    exit();
}

$url = 'http://localhost:8080/sign-up';
$req = [
    'login' => $_POST['login'],
    'phoneNumber' => $_POST['phone'],
    'password' => $_POST['password1'],
    'password2' => $_POST['password2']
];

$result = utilities::post($url, $req);
$result = json_decode($result, true);
if(isset($result['result']) && $result['result'] == 'success') {
    $_SESSION['register-info'] = 'Zostałeś zarejestrowany, teraz zaloguj się!';
} else if(isset($result['result'])) {
    $_SESSION['register-info'] = $result['reason'].', spróbuj ponownie!';
} else {
    $_SESSION['register-info'] = "Błąd połączenia z bazą, spróbuj ponownie później!";
}

header("Location: ../");
exit();