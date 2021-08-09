

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
           <td><img width="120" height="120" src="data:image/jpeg;base64,${ count.imgGame }"></td>
           <td>${count.Category_idCategory.name}</td>
           `
       }
    });
}