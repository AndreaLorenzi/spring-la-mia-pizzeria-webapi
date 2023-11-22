const apiUrl = 'http://localhost:8080/api/v1/pizzas';
const root = document.getElementById('root');


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
  </div>`;
};


const renderPizzalist = (data) => {
  let content;
  console.log(data);
  if (data.length > 0) {

    content = '<div class="row">';

    data.forEach((element) => {
      content += '<div class="col-3">';

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

    const response = await axios.get(apiUrl);

    renderPizzalist(response.data);
  } catch (error) {
    console.log(error);
  }
};


getPizzas();