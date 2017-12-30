function abreselect(check, id) {
    var id = document.getElementById(id);
    var check = document.getElementById(check);

    if (check.checked) {
        id.className = "aparece";
    } else {
        id.className = "some";
    }


}
