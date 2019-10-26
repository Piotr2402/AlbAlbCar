<?php include_once('data/cities.php'); ?>

<?php require_once('modules/header.php'); ?>

<?php
if(!isset($_SESSION['login']) || empty($_SESSION['login'])) {
    header("Location: ./");
}
?>

<h2 class="text-center">🚗 AlbAlbCar</h2>
<p class="text-center">I podróż staje się lepsza..</p>

<form action="./endpoints/search-trip.php" method="post" id="albalbcar-form" class="text-center mt-5 py-4 px-2">

    <h4 class="mb-4">Znajdź swój przejazd!</h4>
    <p class="mb-0">Skąd ruszasz?</p>
    <select class="custom-select" name="assembly-place">
        <?php foreach($cities as $id => $city) { ?>
            <option value="<?=$id?>"><?=$city?></option>
        <?php } ?>
    </select>
    <p class="mt-3 mb-0">Dokąd jedziesz?</p>
    <select class="custom-select" name="destination-place">
        <?php foreach($cities as $id => $city) { ?>
            <option value="<?=$id?>"><?=$city?></option>
        <?php } ?>
    </select>
    <p class="mt-3 mb-0">Od której szukasz przejazdu?</p>
    <input id="start-time" type="text" name="departure-datetime" class="text-center" readonly value="<?= date("Y-m-d H:i") ?>" />
    <div id="start-time-calendar" data-timepicker="true" data-language='en'></div>
    <input type="submit" class="d-block mx-auto btn btn-dark mt-3" value="Szukaj!">
</form>

<div id="rides" class="mt-5">
    <div class="ride d-flex flex-column flex-md-row">
        <div class="left-col d-flex flex-column">
            <h3>2019-10-17 21:00</h3>
            <p>login123</p>
            <p class="font-weight-bold">Warszawa - Poznań - Katowice</p>
            <p>Wolnych miejsc: 4</p>
        </div>
        <div class="right-col d-flex flex-column justify-content-around align-items-end">
            <h4>23zł</h4>
            <div class="ride-reservation btn btn-dark" data-rideid="11">Rezerwuj</div>
        </div>
    </div>
    <div class="ride d-flex flex-column flex-md-row">
        <div class="left-col d-flex flex-column">
            <h3>2019-10-17 22:00</h3>
            <p>login123</p>
            <p class="font-weight-bold">Warszawa - Poznań - Katowice</p>
            <p>Wolnych miejsc: 1</p>
        </div>
        <div class="right-col d-flex flex-column justify-content-around align-items-end">
            <h4>23zł</h4>
            <div class="ride-reservation btn btn-dark" data-rideid="12">Rezerwuj</div>
        </div>
    </div>
</div>
<?php require_once('modules/footer.php'); ?>
