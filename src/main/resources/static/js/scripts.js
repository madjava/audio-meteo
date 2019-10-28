$(document).ready(function (){

    // Switch background
    $('#theme').on('click', function(e) {
        e.preventDefault();
        e.stopPropagation();
        $('body').toggleClass('theme2');
    });

    // Show mobile dialog
    $('#fetch').on('click', function() {
        var location = $('#location').val();
        if(location){
            $('#mobileModal').modal('show');
        }else {
            $('.alert').alert()
        }
    });

    // Respond to dialog event
    $('#mobileModal').on('hide.bs.modal', function (e) {
        var location = $('#location').val();
        $('#mobileNumber').val('');
        fetchAndDisplayWeather(location, null);
    });

    // get data
    $('#call_ok').on('click', function () {
        var location = $('#location').val();
        var mobile = $('#mobileNumber').val();
        $('#mobileModal').modal('hide');
        fetchAndDisplayWeather(location, mobile);
    });
});

// get data from server
function fetchAndDisplayWeather(location, mobile) {
    console.log(location, mobile);
}
