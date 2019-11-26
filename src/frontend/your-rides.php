<?php require_once('modules/header.php'); ?>

<?php
if(!isset($_SESSION['login']) || empty($_SESSION['login'])) {
    header("Location: ./");
}
?>

<div class="row">
    <div class="col-md-6">
        <h3 class="main-header">Stworzone przejazdy</h3>
        <span id="trips-driver"></span>
    </div>
    <div class="col-md-6">
        <h3 class="main-header mt-4 mt-md-0">Jako pasażer</h3>
        <span id="trips-passenger"></span>
    </div>
</div>

<?php
    $script = '
        $(() => {
            formSubmit("{}", "my-trips-passenger", "#trips-passenger", () => {});
            formSubmit("{}", "my-trips-driver", "#trips-driver", () => {});
        
            $(document).on("click", ".cancel-ride", function(e) {
                let data = {
                    rideId: $(this).attr("data-rideid")
                };
                formSubmit(data, "cancel-trip", $(this).find("span") , () => {
                    alert($(this).find("span").text());
                    if($(this).find("span").text() == "Twoje miejsce zostało zwolnione!") {
                        location.reload();
                    }
                });
            });
        });
    ';
?>

<?php require_once('modules/footer.php'); ?>
