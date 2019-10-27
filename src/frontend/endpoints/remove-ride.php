<?php
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