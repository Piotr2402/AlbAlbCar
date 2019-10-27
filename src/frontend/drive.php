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
    <select class="custom-select" name="assembly-place" id="assembly-place"></select>

    <p class="mt-3 mb-0">Dokąd jedziesz?</p>
    <select class="custom-select" name="destination-place" id="destination-place"></select>

    <p class="mt-3 mb-0">Od której szukasz przejazdu?</p>
    <input id="start-time" type="text" name="departure-datetime" class="text-center" readonly value="<?= date("Y-m-d H:i") ?>" />
    <div id="start-time-calendar" data-timepicker="true" data-language='en'></div>
    <input type="submit" class="d-block mx-auto btn btn-dark mt-3" value="Szukaj!">
</form>

<div id="rides" class="mt-5" style="display: none;">
</div>

<?php
$script = '
        $(() => {
            formSubmit("{}", "cities", "#destination-place, #assembly-place", () => {
                $("#destination-place option[value=2]").prop("selected", "selected");
            });
            
            
            $("#albalbcar-form").on("submit", function(e) {
                e.stopPropagation();
                e.preventDefault();
                let data = {
                    assembly_place: $("#assembly-place").val(),
                    destination_place: $("#destination-place").val(),
                    departure_datetime: $("#start-time").val()
                };
                formSubmit(data, "search-trip", "#rides", () => {});
                $("#rides").slideDown();
            });
        });
        
    ';
?>

<?php require_once('modules/footer.php'); ?>
