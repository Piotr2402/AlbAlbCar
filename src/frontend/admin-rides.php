<?php require_once('modules/admin-header.php'); ?>
<form action="#" class="mb-5">
    <p class="mt-2 mb-0 text-center">Szukaj po loginie kierowcy</p>
    <input type="text" id="admin-rides-input" class="form-control" name="login" value="<?= isset($_GET['login']) ? $_GET['login'] : '' ?>"/>
    <input type="submit" id="admin-rides-search" class="btn btn-dark mt-2 mx-auto d-flex" value="Szukaj">
</form>
<!--<table>-->
<!--    <thead>-->
<!--        <tr>-->
<!--            <td>#</td>-->
<!--            <td>Kierowca</td>-->
<!--            <td>Data wyjazdu</td>-->
<!--            <td>Trasa</td>-->
<!--            <td>Pasażerowie</td>-->
<!--            <td>Akcje</td>-->
<!--        </tr>-->
<!--    </thead>-->
<!--    <tbody>-->
<!--        <tr>-->
<!--            <td>1</td>-->
<!--            <td>login123</td>-->
<!--            <td></td>-->
<!--            <td>Wrocław - Warszawa</td>-->
<!--            <td>login1234</td>-->
<!--            <td><a href="">Usuń</a></td>-->
<!--        </tr>-->
<!--    </tbody>-->
<!--</table>-->

<div id="admin-rides-search-result"></div>

<?php require_once('modules/footer.php'); ?>
