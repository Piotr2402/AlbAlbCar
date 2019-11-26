<?php

include("../modules/utilities.php");

$url = 'http://localhost:8080/cities';
$req = [];

$result = utilities::post($url, $req);
$result = json_decode($result, true);

$values = '';

foreach($result as $city) {
    $values = $values . '<option value="' . $city['id'] . '">' . $city['cityName'] . '</option>';
}

echo $values;