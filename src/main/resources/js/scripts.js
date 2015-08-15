$(document).ready(function () {
    var fromIndex = 5;
    $('#more').click(function () {
        $.ajax({
            url: 'activity',
            method: 'POST',
            data: {"fromIndex": fromIndex}
        }).done(function (data) {
            var activities = JSON.parse(data);
            if (data.length > 0) {
                $.each(activities, function (index, element) {
                    $(".activity").append("<div class='row'> <div class='date-and-time'>" + element["date"] +
                        "</div> <span class='employee'>" + element["firstname"] + " " + element["lastname"] + "</span>"
                        + element["comment"] + "</div>");
                });
                fromIndex += 5;
            }
        });
    });
});