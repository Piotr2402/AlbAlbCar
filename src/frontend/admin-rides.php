<?php require_once('modules/admin-header.php'); ?>

<?php
if(!isset($_SESSION['admin']) || empty($_SESSION['admin'])) {
    header("Location: ./");
}
?>

<h3 class="text-center text-uppercase mb-4">Przejazdy</h3>
<form method="get" action="#" class="mb-5">
    <p class="mt-2 mb-0 text-center">Szukaj po loginie kierowcy</p>
    <input type="text" id="admin-rides-input" class="form-control" name="login" value="<?= isset($_GET['login']) ? $_GET['login'] : '' ?>"/>
    <input type="submit" id="admin-rides-search" class="btn btn-dark mt-2 mx-auto d-flex" value="Szukaj">
</form>

<table id="admin-rides-search-result">
</table>




<?php
$script = '
        $("#admin-rides-search").on("click", function(e) {
            e.stopPropagation();
            e.preventDefault();

            let data = {};
            if($("#admin-rides-input").val() !== "") {
                data.searched_login = $("#admin-rides-input").val();
            }
            formSubmit(data, "ride-search", $("#admin-rides-search-result"), () => {});
        });
        
        $(document).ready(() => {
            formSubmit({}, "ride-search", $("#admin-rides-search-result"), () => {});
        });
    ';
?>

<?php require_once('modules/footer.php'); ?>
