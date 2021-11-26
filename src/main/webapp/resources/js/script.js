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

function plusCount(id) {
    var selectedBook = parseInt(document.getElementById("book"+id).value);
    document.getElementById("book"+id).value = selectedBook + 1;
    document.getElementById("changeCountConfirm").hidden = false;
}

function minusCount(id) {
    var selectedBook = parseInt(document.getElementById("book"+id).value);
    document.getElementById("book"+id).value = selectedBook - 1;
    document.getElementById("changeCountConfirm").hidden = false;
}

function changeCount(bookId) {

    var bookCount = parseInt(document.getElementById("book"+bookId).value);
    //console.log(bookCount);
    bookId = parseInt(bookId);
   // console.log(bookId);
    //var path = "/changeBookCount?bookCount=" + bookCount + "&bookId=" + bookId;
    let path = `/changeBookCount/${bookId}/${bookCount}`;
   // alert(path);
    //console.log(path);
    location.replace(path);
}