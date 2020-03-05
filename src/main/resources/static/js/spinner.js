$(document).ready(function(){
    $("#loginForm").on("submit", function(){
        $("#btnLogin").prop("disabled", true);
        $(".loader").show();
    });//submit
});//document ready