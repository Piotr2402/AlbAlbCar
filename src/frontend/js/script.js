$(document).on("click", ".ride-reservation", function(e) {
    alert($(this).attr("data-rideid"));
});

$("#start-time").on('click', function() {
    $('#start-time-calendar').slideDown();
});
$('#start-time-calendar').datepicker({
    minDate: new Date(),
    onSelect: function (fd, d, picker) {
        var date = (d.getDate() < 10) ? ("0" + d.getDate()) : (d.getDate());
        var month = ((d.getMonth()+1) < 10) ? ("0" + (d.getMonth()+1)) : ((d.getMonth()+1));
        var hours = (d.getHours() < 10) ? ("0" + d.getHours()) : (d.getHours());
        var minutes = (d.getMinutes() < 10) ? ("0" + d.getMinutes()) : (d.getMinutes());

        $('#start-time').val((d.getYear()+1900) + '-' + month + '-' + date + ' ' + hours + ':' + minutes );
    }
});
$('#start-time-calendar').data('datepicker');


$(".time").on('click', function() {
    $(this).parent().find('.time-calendar').slideDown();
});
$('.time-calendar').datepicker({
    minDate: new Date(),
    onSelect: function (fd, d, picker) {
        var date = (d.getDate() < 10) ? ("0" + d.getDate()) : (d.getDate());
        var month = ((d.getMonth()+1) < 10) ? ("0" + (d.getMonth()+1)) : ((d.getMonth()+1));
        var hours = (d.getHours() < 10) ? ("0" + d.getHours()) : (d.getHours());
        var minutes = (d.getMinutes() < 10) ? ("0" + d.getMinutes()) : (d.getMinutes());

        $(this).parent().find('.time').val((d.getYear()+1900) + '-' + month + '-' + date + ' ' + hours + ':' + minutes );
    }
});
$('.time-calendar').data('datepicker');

$(document).on("click", ".via-place-remove", function(e) {
    $(this).parent().remove();
});

$(".add-via-place").on('click', function() {
    $('.via-places').append($('<div>').addClass("via-place").append($(".via-place-pattern").html()));
});

function formSubmit(data, endpoint, destination) {
    $.post({
        type: "POST",
        url: "endpoints/" + endpoint + ".php",
        data: {
            login: data
        },
    }).always(function(text) {
        $(destination).text(text);
    });
}