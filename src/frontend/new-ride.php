<?php require_once('modules/header.php'); ?>

<?php
if(!isset($_SESSION['login']) || empty($_SESSION['login'])) {
    header("Location: ./");
}
?>

<form method="post" action="endpoints/new-ride.php" id="new-ride" class="text-center">
    <h4 class="mb-4">Dodaj nowy przejazd</h4>
    <p class="mb-0">Skąd ruszasz?</p>
    <select class="custom-select" name="assembly-place" id="assembly-place"></select>

    <p class="mt-2 mb-0">Ilość miejsc</p>
    <select class="custom-select" id="places-amount" name="places-amount">
        <?php for($i=1; $i<=10; ++$i) { ?>
            <option value="<?=$i?>"><?=$i?></option>
        <?php } ?>
    </select>
    <p class="mt-2 mb-0">Cena za miejsce</p>
    <input type="text" class="form-control" id="place-price" name="price" />
    <p class="mt-2 mb-0">Godzina wyjazdu</p>
    <input id="start-time" type="text" name="departure-datetime" class="form-control" readonly value="<?= date("Y-m-d H:i") ?>" />
    <div id="start-time-calendar" data-timepicker="true" data-language='en'></div>

    <div class="btn btn-outline-dark mt-4 mb-3 add-via-place">Dodaj miejsce pośrednie</div>
    <div class="via-places"></div>

    <p class="mt-3 mb-0">Dokąd jedziesz?</p>
    <select class="custom-select d-block mx-auto" id="destination-place" name="destination-place"> </select>
    <p id="new-ride-form-info" class="mt-3 text-info font-weight-bold"></p>
    <input type="submit" class="btn btn-dark mt-2" value="Dodaj przejazd">
</form>

<div class="via-place-pattern d-none">
    <div class="via-place-remove">⨉</div>
    <p class="mb-0">Miejsce pośrednie</p>
    <select class="custom-select via-place-city"></select>

    <p class="mt-2 mb-0">Czas dotarcia od początku jazdy</p>
    <select class="custom-select via-place-time">
        <?php for($i=10; $i<=720; $i+=10) { ?>
            <option value="<?=$i?>"><?= $i<60 ? '' : ((int)($i/60) . 'h') ?> <?= $i%60 ?>min</option>
        <?php } ?>
    </select>
    <p class="mt-2 mb-0">Cena do miejsca docelowego</p>
    <input type="text" class="form-control via-place-price" />
</div>

<?php
$script = '
        $(() => {
            formSubmit("{}", "cities", "#destination-place, #assembly-place, .via-place-city", () => {
                $("#destination-place option[value=2]").prop("selected", "selected");
            });
        });
        
        
        $("#new-ride").on("submit", function(e) {
            e.stopPropagation();
            e.preventDefault();
            let data = {
                assembly_place: $("#assembly-place").val(),
                seats: $("#places-amount").val(),
                price: $("#place-price").val(),
                destination_place: $("#destination-place").val(),
                departure_datetime: $("#start-time").val(),
                stops: []
            };
            let stopObjects = $(".via-places > .via-place");
            for(let i=0; i<stopObjects.length; ++i) {
                let stop = {
                    cityId: $(stopObjects).eq(i).find(".via-place-city").val(),
                    delay: $(stopObjects).eq(i).find(".via-place-time").val(),
                    price: $(stopObjects).eq(i).find(".via-place-price").val(),
                }
                data.stops.push(stop);
            }
            
            formSubmit(data, "new-ride", "#new-ride-form-info", () => {
                if($("#new-ride-form-info").text() == "Przejazd został dodany!") {
                    $("#new-ride-form-info").addClass("d-none");
                    alert("Przejazd został dodany!");
                    location.reload();
                }
            });
        });
    ';
?>

<?php require_once('modules/footer.php'); ?>
