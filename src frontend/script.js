const apiUrl = 'http://localhost:8080/api/v1/pizzas';
const root = document.getElementById('root');

/* FUNZIONI */
// funzione che renderizza le categorie del pizza
const renderIngredients = (ingredients) => {
  console.log(ingredients);
  let content;
  if (ingredients.length === 0) {
    content = 'No ingredients';
  } else {
    content = '<ul class="list-unstyled">';
    ingredients.forEach((ing) => {
      content += `<li>${ing.name}</li>`;
    });
    content += '</ul>';
  }
  return content;
};

// funzione che renderizza la card della pizza
const renderPizza = (element) => {
  console.log(element);
  return `<div class="card shadow h-100 ">
            <div class="card-body">
                <h5 class="card-title">${element.name}</h5>
                <h6 class="card-subtitle mb-2 text-body-secondary">${
                 element.description
                }</h6>
                <p> price: ${element.price}€</p>
                <div><img class="h-75 w-50"src="${element.imageUrl}"></div>

            </div>
            <div class="card-footer" style="margin-top: 25px;!important">${renderIngredients(element.ingredients)}</div>
            <button class="btn btn-danger" onclick="deletePizza(${element.id})">Delete Pizza</button>
  </div>`;
};

// funzione che renderizza la gallery di card
const renderPizzalist = (data) => {
  let content;
  console.log(data);
  if (data.length > 0) {
    // creo la gallery
    content = '<div class="row">';
    // itero sull'array di libri
    data.forEach((element) => {
      content += '<div class="col-3">';
      // chiamo il metodo che restituisce la card della pizza
      content += renderPizza(element);
      content += '</div>';
    });
    content += '</div>';
  } else {
    content = '<div class="alert alert-info">The list is empty</div>';
  }

  root.innerHTML = content;
};


const getPizzas = async () => {
  try {
    // ottengo la response dell'api
    const response = await axios.get(apiUrl);
    // passo i dati alla funzione che li renderizza
    renderPizzalist(response.data);
  } catch (error) {
    console.log(error);
  }
};
// Funzione per eliminare la pizza
const deletePizza = async (pizzaId) => {
    try {
        const response = await fetch(`${apiUrl}/${pizzaId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                // Aggiungi eventuali altri header necessari
            },
        });

        if (!response.ok) {
            throw new Error(`Errore durante la cancellazione della pizza con ID ${pizzaId}`);
        }

        console.log(`Pizza con ID ${pizzaId} eliminata con successo`);
        // Puoi aggiornare la visualizzazione o eseguire altre azioni dopo la cancellazione
        location.reload();
    } catch (error) {
        console.error(error.message);
        // Gestisci l'errore in base alle tue esigenze
    }
};
/* codice globale che viene eseguito al load dello script */
getPizzas();