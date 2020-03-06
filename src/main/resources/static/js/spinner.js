$(document).ready(function(){
    $("#spinner").on("submit", function(){
        $("#btnLogin").prop("disabled", true);
        $(".loader").show();
    });//submit
});//document ready