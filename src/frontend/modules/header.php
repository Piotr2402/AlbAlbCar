<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>AlbAlbCar</title>
    <link rel="stylesheet" href="./css/bootstrap.css">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link href="https://fonts.googleapis.com/css?family=Raleway&display=swap" rel="stylesheet">
</head>

<body>

<nav class="navbar navbar-expand-lg bg-dark navbar-dark py-3">
    <a class="navbar-brand ml-3 ml-md-5" href="./"><div class="car">🚗</div>AlbAlbCar</a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse justify-content-center" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link text-center" href="./new-ride.php">Nowa trasa</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-center" href="./drive.php">Szukaj trasy</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-center" href="./your-rides.php">Twoje trasy</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-center" href="./endpoints/logout.php">Wyloguj (login123)</a>
            </li>
        </ul>
    </div>
</nav>

<main class="container py-5">


<?php
