<?php require_once('modules/header.php'); ?>
<div class="row">
    <div class="col-md-6">
        <h3 class="main-header">Stworzone przejazdy</h3>
        <div class="ride">
            <div class="d-flex flex-column">
                <h3>#24</h3>
                <p class="font-weight-bold">Warszawa (2019-10-17 21:00) - Poznań (2019-10-17 23:00) - Katowice</p>
                <p>Wolnych miejsc: 0</p>
                 <p><span class="font-weight-bold">Uczestnicy:</span><br>
                    login123 (Warszawa - Poznań) [<a href="tel:504212222">504212222</a>]: 12zł<br>
                    login122213 (Warszawa - Katowice) [<a href="tel:504212222">501212312</a>]: 25zł<br>
                        login122213 (Poznań - Katowice) [<a href="tel:504212222">504222144</a>]: 13zł
                </p>
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <h3 class="main-header mt-4 mt-md-0">Jako pasażer</h3>

        <div class="ride">
            <div class="d-flex flex-column">
                <h3>2019-11-17 22:00</h3>
                <p class="font-weight-bold">Warszawa - Katowice</p>
                <p>Kierowca: login233 <a href="tel:123123123">[123123123]</a></p>
                <p class="font-weight-bold">Cena: 25zł</p>
                <div class="btn btn-danger mt-2 cancel-ride" data-rideid="24">Anuluj przejazd</div>
            </div>
        </div>
        <div class="ride">
            <div class="d-flex flex-column">
                <h3>2019-10-17 22:00</h3>
                <p class="font-weight-bold">Warszawa - Katowice</p>
                <p>Kierowca: login233 <a href="tel:123123123">[123123123]</a></p>
                <p class="font-weight-bold">Cena: 25zł</p>
            </div>
        </div>
    </div>
</div>

<?php require_once('modules/footer.php'); ?>
