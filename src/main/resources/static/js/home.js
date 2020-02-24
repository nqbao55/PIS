function clickCheckbox(){
    var param = [];
    Pnotice = $("#listBakery input:checked").map(function(i,el) {return el.id;}).get();
    param.push(Pnotice);
    var p = param.join('&');
    document.getElementById("strId").value = p;
    document.getElementById("strId").textContent = p;
}

function deliveryPreview() {
    var strId = document.getElementById("strId").value;
    window.location.href = "/deliverypreview/"+strId

}