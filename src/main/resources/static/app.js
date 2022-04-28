"use strict";

const ADDR = "http://localhost:8080";

// Divs
const RESULTS_DIV = document.querySelector("#results-div");
const FORM_DIV = document.querySelector("#form-div");

// Inputs
const TITLE_EL = document.querySelector(".title-el");
const AUTHOR_EL = document.querySelector(".author-el");
const PUBLISHER_EL = document.querySelector(".publisher-el");
const PUBLISHED_YEAR_EL = document.querySelector(".published-year-el");
const RATING_EL = document.querySelector(".rating-el");

const TITLE_UPDATE = document.querySelector(".title-update");
const AUTHOR_UPDATE = document.querySelector(".author-update");
const PUBLISHER_UPDATE = document.querySelector(".publisher-update");
const PUBLISHED_YEAR_UPDATE = document.querySelector(".published-year-update");
const RATING_UPDATE = document.querySelector(".rating-update");

const RADIO_BUTTONS = document.querySelectorAll('input[name="rating"]');
let selectedRating;

// Buttons
const CREATE_BTN = document.querySelector("#create-btn");
const EDIT_BTN = document.querySelector("#edit-btn");

// GET ALL request --------------------------------------------------------------------------
const getAll = () => {
  axios
    .get(`${ADDR}/book/getAll`)
    .then((res) => {
      RESULTS_DIV.innerHTML = "";
      const RESULTS = res.data;
      for (let result of RESULTS) {
        printResult(result);
      }
    })
    .catch((err) => console.error(err));
};

// POST request -------------------------------------------------------------------------------
function create() {
  for (const radioButton of RADIO_BUTTONS) {
    if (radioButton.checked) {
      selectedRating = parseInt(radioButton.value);
    }
  }
  const data = {
    title: TITLE_EL.value,
    author: AUTHOR_EL.value,
    publishedYear: parseInt(PUBLISHED_YEAR_EL.value),
    publisher: PUBLISHER_EL.value,
    rating: selectedRating,
  };
  axios
    .post(`${ADDR}/book/create`, data)
    .then((res) => console.log(res))
    .catch((err) => alert(err));
  getAll;
}

// PUT request --------------------------------------------------------------------------
function update() {
  for (const radioButton of RADIO_BUTTONS) {
    if (radioButton.checked) {
      selectedRating = parseInt(radioButton.value);
    }
  }
  const data = {
    title: editForm.title.value,
    author: editForm.author.value,
    publishedYear: editForm.publishedYear.value,
    publisher: editForm.publisher.value,
    rating: selectedRating,
  };

  console.log(data);

  axios
    .put(`${ADDR}/book/update/${parseInt(editForm.entryId.value)}`, data)
    .then((res) => console.log(res))
    .catch((err) => alert(err));
}

const printResult = (result) => {
  const OUTER_DIV = document.createElement("div");
  OUTER_DIV.setAttribute("class", "outer-div");

  const INNER_DIV = document.createElement("div");
  INNER_DIV.setAttribute("class", "inner-div");

  const VALUES = document.createElement("div");
  VALUES.setAttribute("class", "values");
  VALUES.textContent = `Title: ${result.title} | Author: ${result.author} | Year Published: ${result.publishedYear} | Publisher: ${result.publisher} | Rating : ${result.rating}/5`;

  const BUTTONS = document.createElement("div");
  BUTTONS.role = "group";
  BUTTONS.class = "btn-group";

  const EDIT = document.createElement("button");
  EDIT.type = "button";
  EDIT.textContent = "Edit";
  EDIT.id = result.id;
  EDIT.setAttribute("class", " btn btn-sm btn-warning edit-btn");
  EDIT.setAttribute("onClick", "openEdit(this.id)");

  const DEL = document.createElement("button");
  DEL.type = "button";
  DEL.textContent = "Delete";
  DEL.id = result.id;
  DEL.setAttribute("class", "btn btn-sm btn-danger del-btn");
  DEL.setAttribute("onClick", "del(this.id)");

  const LINEBREAK = document.createElement("br");

  INNER_DIV.appendChild(VALUES);
  OUTER_DIV.appendChild(INNER_DIV);

  VALUES.appendChild(BUTTONS);
  BUTTONS.appendChild(EDIT);
  BUTTONS.appendChild(DEL);
  VALUES.appendChild(LINEBREAK);

  RESULTS_DIV.appendChild(OUTER_DIV);
};

const openEdit = (id) => {
  // Show modal and configure date field
  $("#edit-modal").modal("show");

  // Get the current values for selected entry
  axios
    .get(`${ADDR}/book/getById/${id}`)
    .then((res) => {
      const ENTRY = res.data;
      // Populate modal form with current values
      const EDIT_FORM = document.forms["editForm"];
      EDIT_FORM["title"].value = ENTRY.title;
      EDIT_FORM["author"].value = ENTRY.author;
      EDIT_FORM["publishedYear"].value = ENTRY.publishedYear;
      EDIT_FORM["publisher"].value = ENTRY.publisher;
      EDIT_FORM["rating"].value = ENTRY.rating;
      EDIT_FORM["entryId"].value = ENTRY.id;
    })
    .catch((err) => console.error(err));
};

// Event Listener
CREATE_BTN.addEventListener("click", create);
EDIT_BTN.addEventListener("click", update);

getAll();
