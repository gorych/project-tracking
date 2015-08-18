$(document).ready(function () {
    var fromIndex = 5;
    $('#more').click(function () {
        $.ajax({
            url: 'user/activity',
            method: 'POST',
            data: {"fromIndex": fromIndex}
        }).done(function (data) {
            var activities = JSON.parse(data);
            if (data.length > 0) {
                $.each(activities, function (index, element) {
                    $(".activity").append("<div class='row'> <div class='date-and-time'>" + element["date"] +
                        "</div> <span class='employee'>" + element["fullName"] + "</span>"
                        + element["comment"] + "</div>");
                });
                fromIndex += 5;
            }
        });
    });
});

function datepicker(divId, inputId) {
    $(function () {
            $(divId).pickmeup({
                flat: true
            });
            var input = $(inputId);
            input.pickmeup({
                position: 'left',
                format: 'Y-m-d',
                before_show: function () {
                    input.pickmeup('set_date', input.val(), true);
                },
                change: function (formated) {
                    input.val(formated);
                }
            });
        }
    );
}