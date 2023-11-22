document
.getElementById("pizza-create")
.addEventListener("submit", async function (event) {
  event.preventDefault();

  try {
    // Ottenere i dati del modulo
    var formData = new FormData(event.target);

    // Effettuare la richiesta API
    const response = await fetch("http://localhost:8080/api/v1/pizzas", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        name: formData.get("name"),
        price: formData.get("price"),
        imageUrl: formData.get("imageUrl"),
        description: formData.get("description"),
      }),
    });

    if (!response.ok) {
      throw new Error("Failed to create pizza");
    }

    const data = await response.json();
    console.log("Pizza created successfully:", data);

    // Ridireziona alla pagina principale
    window.location.href = "/";
  } catch (error) {
    console.error("An unexpected error occurred:", error);
  }
});