

function viewGames(){

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/servletsAsync_3C_war/readGames'

    }).done(function(res){

       let game = res.listGames;
       let tableBody = document.querySelector('#body');
       tableBody.innerHTML = '';
       for(let count of game){
           tableBody.innerHTML += ` 
           <td>${count.name}</td>
           <td>${count.datePremiere}</td>
           <td><img width="120" height="120" src="data:image/jpeg;base64,${ count.imgGame }"</td>
           <td>${count.Category_idCategory.name}</td>
           `
       }


    });

}

function create(){
    let datos = new FormData();
    datos.append('action', 'create');
    datos.append('name', document.getElementById("name").value);
    datos.append('image', document.getElementById("image").files[0]);
    datos.append('date', document.getElementById("date").value);
    datos.append('category', document.getElementById("category").value);

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/servletsAsync_3C_war/createGames',
        contentType: false,
        processData: false,
        data: datos

    }).done(function (res){
        alert("Entro aqui");
        document.getElementById("name").value="";
        document.getElementById("image").value="";
        document.getElementById("date").value="";
        document.getElementById("category").value="";
        viewGames();
    });

}