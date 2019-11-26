<?php
/**
 * Created by PhpStorm.
 * User: kubag_000
 * Date: 24.10.2019
 * Time: 19:53
 */

class utilities {
    public static function post($url, array $jsonData) {
        $ch = curl_init($url);
        $jsonDataEncoded = json_encode($jsonData);
        curl_setopt($ch, CURLOPT_POST, 1);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonDataEncoded);
        curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));
        curl_setopt($ch,CURLOPT_RETURNTRANSFER, true);
        return curl_exec($ch);
    }

}