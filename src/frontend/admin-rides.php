<?php require_once('modules/admin-header.php'); ?>
<form action="endpoints/ride-search" class="mb-5">
    <p class="mt-2 mb-0 text-center">Szukaj po loginie kierowcy</p>
    <input type="text" class="form-control" name="login" />
    <input type="submit" class="btn btn-dark mt-2 mx-auto d-flex" value="Szukaj">
</form>
<table>
    <thead>
        <tr>
            <td>#</td>
            <td>Kierowca</td>
            <td>Data wyjazdu</td>
            <td>Trasa</td>
            <td>Pasażerowie</td>
            <td>Akcje</td>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>1</td>
            <td>login123</td>
            <td><?= date("Y-m-d H:i:s") ?></td>
            <td>Wrocław - Warszawa</td>
            <td>login1234</td>
            <td><a href="">Usuń</a></td>
        </tr>
    </tbody>
</table>

<?php require_once('modules/footer.php'); ?>
