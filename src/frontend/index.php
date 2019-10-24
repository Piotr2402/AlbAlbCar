<?php include_once('data/cities.php'); ?>

<?php require_once('modules/header.php'); ?>
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
            <div id="login-info" class="form-text text-danger"></div>
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
                <input type="password" class="form-control" name="password" placeholder="Hasło">
            </div>
            <div class="form-group">
                <label for="password">Powtórz hasło</label>
                <input type="password" class="form-control" id="password" placeholder="Powtórz hasło">
            </div>
            <div id="login-info" class="form-text text-danger">We'll never share your email with anyone else.</div>
            <input type="submit" class="btn btn-dark mx-auto mt-2 px-4" value="Rejestracja" />
        </form>
    </div>
</div>
<?php require_once('modules/footer.php'); ?>
