function imageLoad() {
    var imgSrc = prompt("Copy image url")
    if(imgSrc != null){
        var a = document.createElement("a");
        var img = document.createElement("img");
        a.href = imgSrc;
        img.src = imgSrc;
        img.className = "add-image";
        document.getElementById("imgSrc").setAttribute("value", imgSrc);
        document.getElementById("image").innerHTML = "";
        a.appendChild(img);
        document.getElementById("image").appendChild(a);
    }
}

function userAutocomplete(startPath, id) {
    $("#" + id).autocomplete({
        minLength: 1,
        source: function (request, response) {
            var usernameValue = $("#" + id).val();
            $.ajax({
                url: startPath + "/user/getUsernames",
                data: {username: usernameValue},
                success: function (data) {
                    response(data);
                }
            });
        }
    })
}