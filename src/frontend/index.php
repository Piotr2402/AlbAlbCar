<?php require_once('modules/header.php'); ?>

<?php
    if(isset($_SESSION['admin']) && !empty($_SESSION['admin'])) {
        header("Location: admin-rides");
    } else if(isset($_SESSION['login']) && !empty($_SESSION['login'])) {
        header("Location: drive");
    }
?>

<h2 class="text-center">🚗 AlbAlbCar</h2>
<p class="text-center">I podróż staje się lepsza..</p>

<div class="row">
    <div class="col-md-6 text-center px-5 mt-3 mt-md-0">
        <h3 class="mb-4">Logowanie</h3>
        <form action="./endpoints/login.php" method="post">
            <div class="form-group">
                <label for="login">Login</label>
                <input type="text" class="form-control" name="login" placeholder="Twój login">
            </div>
            <div class="form-group">
                <label for="password">Hasło</label>
                <input type="password" class="form-control" name="password" placeholder="Hasło">
            </div>
            <div class="form-text text-info font-weight-bold"><?= isset($_SESSION['login-info']) ? $_SESSION['login-info'] : '' ?></div>
            <?php unset($_SESSION['login-info']); ?>
            <input type="submit" class="btn btn-dark mx-auto mt-2 px-4" value="Logowanie" />
        </form>
    </div>
    <div class="col-md-6 text-center px-5 mt-5 mt-md-0">
        <h3 class="mb-4">Rejestracja</h3>
        <form action="./endpoints/register.php" method="post">
            <div class="form-group">
                <label for="login">Login</label>
                <input type="text" class="form-control" name="login" placeholder="Twój login">
            </div>
            <div class="form-group">
                <label for="phone">Telefon</label>
                <input type="tel" class="form-control" name="phone" placeholder="Telefon">
            </div>
            <div class="form-group">
                <label for="password">Hasło</label>
                <input type="password" class="form-control" name="password1" placeholder="Hasło">
            </div>
            <div class="form-group">
                <label for="password">Powtórz hasło</label>
                <input type="password" class="form-control" name="password2" placeholder="Powtórz hasło">
            </div>
            <div class="form-text text-info font-weight-bold"><?= isset($_SESSION['register-info']) ? $_SESSION['register-info'] : '' ?></div>
            <?php unset($_SESSION['register-info']); ?>
            <input type="submit" class="btn btn-dark mx-auto mt-2 px-4" value="Rejestracja" />
        </form>
    </div>
</div>
<?php require_once('modules/footer.php'); ?>
