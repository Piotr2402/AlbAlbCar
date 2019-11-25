<?php require_once('modules/admin-header.php'); ?>

<?php
if(!isset($_SESSION['admin']) || empty($_SESSION['admin'])) {
    header("Location: ./");
}
?>
<h3 class="text-center text-uppercase mb-4">Użytkownicy</h3>

<form method="get" action="#" class="mb-5">
    <p class="mt-2 mb-0 text-center">Szukaj po loginie</p>
    <input type="text" id="admin-users-input" class="form-control" name="login" value="<?= isset($_GET['login']) ? $_GET['login'] : '' ?>" />
    <input type="submit" id="admin-users-search" class="btn btn-dark mt-2 mx-auto d-flex" value="Szukaj">
</form>

<table id="admin-users-search-result"></table>

<?php
$script = '
        $("#admin-users-search").on("click", function(e) {
            e.stopPropagation();
            e.preventDefault();

            let data = {};
            if($("#admin-rides-input").val() !== "") {
                data.searched_login = $("#admin-users-input").val();
            }
            formSubmit(data, "user-search", $("#admin-users-search-result"), () => {});
        });
        
        $(document).ready(() => {
            formSubmit({}, "user-search", $("#admin-users-search-result"), () => {});
        });
    ';
?>

<?php require_once('modules/footer.php'); ?>
