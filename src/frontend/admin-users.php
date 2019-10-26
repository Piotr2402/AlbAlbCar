<?php require_once('modules/admin-header.php'); ?>

<?php
if(!isset($_SESSION['admin']) || empty($_SESSION['admin'])) {
    header("Location: ./");
}
?>
<h3 class="text-center text-uppercase mb-4">Użytkownicy</h3>

<form action="endpoints/user-search" class="mb-5">
    <p class="mt-2 mb-0 text-center">Szukaj po loginie</p>
    <input type="text" class="form-control" name="login" value="<?= isset($_GET['login']) ? $_GET['login'] : '' ?>" />
    <input type="submit" class="btn btn-dark mt-2 mx-auto d-flex" value="Szukaj">
</form>

<table>
    <thead>
    <tr>
        <td>#</td>
        <td>Login</td>
        <td>Telefon</td>
        <td>Akcje</td>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>1</td>
        <td>login123</td>
        <td>234 234 234</td>
        <td><a href="">Usuń</a></td>
    </tr>
    </tbody>
</table>

<?php require_once('modules/footer.php'); ?>
