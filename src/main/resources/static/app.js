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

const radioButtons = document.querySelectorAll('input[name="rating"]');
let selectedRating;

// Buttons
const CREATE_BTN = document.querySelector("#create-btn");

// Get request
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

// Post request
function create() {
  for (const radioButton of radioButtons) {
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
  console.log(data);
  axios
    .post(`${ADDR}/book/create`, data)
    .then((res) => console.log(res))
    .catch((err) => alert(err));
  getAll;
}

const printResult = (result) => {
  const OUTER_DIV = document.createElement("div");
  OUTER_DIV.setAttribute("class", "outer-div");

  const INNER_DIV = document.createElement("div");
  INNER_DIV.setAttribute("class", "inner-div");

  const VALUES = document.createElement("div");
  VALUES.setAttribute("class", "values");
  VALUES.textContent = `Title: ${result.title} | Author: ${result.author} | Year Published: ${result.publishedYear} | Publisher: ${result.publisher} | Rating : ${result.rating}/5`;

  const EDIT = document.createElement("button");
  EDIT.type = "button";
  EDIT.textContent = "Edit";
  EDIT.id = result.id;
  EDIT.setAttribute("class", "btn edit");
  EDIT.setAttribute("onClick", "openEdit(this.id)");

  const DEL = document.createElement("button");
  DEL.type = "button";
  DEL.textContent = "Delete";
  DEL.id = `${result.id}`;
  DEL.setAttribute("class", "btn btn-sm btn-danger del-btn");
  DEL.setAttribute("onClick", "del(this.id)");

  INNER_DIV.appendChild(VALUES);
  OUTER_DIV.appendChild(INNER_DIV);

  VALUES.appendChild(EDIT);
  VALUES.appendChild(DEL);

  RESULTS_DIV.appendChild(OUTER_DIV);
};

// Event Listener
CREATE_BTN.addEventListener("click", create);

getAll();
