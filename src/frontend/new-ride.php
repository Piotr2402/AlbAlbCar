<?php include_once('data/cities.php'); ?>

<?php require_once('modules/header.php'); ?>

<?php
if(!isset($_SESSION['login']) || empty($_SESSION['login'])) {
    header("Location: ./");
}
?>

<form method="post" action="endpoints/new-ride.php" id="new-ride" class="text-center">
    <h4 class="mb-4">Dodaj nowy przejazd</h4>
    <p class="mb-0">Skąd ruszasz?</p>
    <select class="custom-select" name="assembly-place">
        <?php foreach($cities as $id => $city) { ?>
            <option value="<?=$id?>"><?=$city?></option>
        <?php } ?>
    </select>
    <p class="mt-2 mb-0">Ilość miejsc</p>
    <select class="custom-select" name="places-amount">
        <?php for($i=1; $i<=10; ++$i) { ?>
            <option value="<?=$i?>"><?=$i?></option>
        <?php } ?>
    </select>
    <p class="mt-2 mb-0">Cena za miejsce</p>
    <input type="text" class="form-control" name="price" />
    <p class="mt-2 mb-0">Godzina wyjazdu</p>
    <div>
        <div class="time"></div>
        <div class="time-calendar"  data-timepicker="true" data-language='en'></div>
    </div>

    <div class="btn btn-outline-dark mt-4 mb-3 add-via-place">Dodaj miejsce pośrednie</div>
    <div class="via-places"></div>

    <p class="mt-3 mb-0">Dokąd jedziesz?</p>
    <select class="custom-select d-block mx-auto" name="destination-place">
        <?php foreach($cities as $id => $city) { ?>
            <option value="<?=$id?>"><?=$city?></option>
        <?php } ?>
    </select>
    <input type="submit" class="btn btn-dark mt-2" value="Dodaj przejazd">
</form>

<div class="via-place-pattern d-none">
    <div class="via-place-remove">⨉</div>
    <p class="mb-0">Miejsce pośrednie</p>
    <select class="custom-select via-place-city">
        <?php foreach($cities as $id => $city) { ?>
            <option value="<?=$id?>"><?=$city?></option>
        <?php } ?>
    </select>
    <p class="mt-2 mb-0">Czas dotarcia od początku jazdy</p>
    <select class="custom-select via-place-time">
        <?php for($i=10; $i<=720; $i+=10) { ?>
            <option value="<?=$i?>"><?= $i<60 ? '' : ((int)($i/60) . 'h') ?> <?= $i%60 ?>min</option>
        <?php } ?>
    </select>
    <p class="mt-2 mb-0">Cena do miejsca docelowego</p>
    <input type="text" class="form-control via-place-price" />
</div>

<?php require_once('modules/footer.php'); ?>
