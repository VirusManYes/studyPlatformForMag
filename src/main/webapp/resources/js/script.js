function enter(){
    location.replace("/login");
}
function register(){
    location.replace("/register");
}

function allUsers(){
    location.replace("/allUsers");
}

function backToMainPage(){
    location.replace("/");
}
function refreshWithSelect() {
    var selected = document.getElementById("goodsCount").value;
    location.replace("/select/" + selected);
}