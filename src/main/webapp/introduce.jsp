<%@ page language="java" contentType="text/html; charset=UTF-8"%>

  <style type="text/css">
  @import url("https://fonts.googleapis.com/css2?family=Lato&display=swap");

button {
  all: unset;
  cursor: pointer;
  -webkit-tap-highlight-color: transparent ;
}
.inner-container {
  height: 100%;
  margin: 0 auto;
  max-width: 85rem;
  width: calc(100% - var(--gutter));
}

/* ---General classes--- */
.flex-row {
  display: flex;
}
.flex-column {
  display: flex;
  flex-direction: column;
}
.flex-center {
  display: flex;
  align-items: center;
  position:relative;
  right:5%;
  justify-content: center;
}
.justify-between {
  display: flex;
  justify-content: space-between;
}
/* ---General classes--- */

.slider {
  height: 100%;
  overflow: hidden;
  background-color: var(--color-secondary);
}
.slider__wrraper {
  height: 100%;
  justify-content: flex-end;
}
.slider__img {
  width: 20rem;
  align-self: center;
  margin-left:1.5rem;
  margin-bottom: 1.7rem;
}
.slider__context {
  font-weight: 900;
  font-size: 1.55rem;
  width: fit-content;
  margin-bottom: 1.5rem;
  text-transform: capitalize;
}
.slider__title {
  font-size: 1.1em;
  margin: 0.1rem 0 0.35rem;
}
.slider__category {
  margin: 0;
  font-size: 1.55rem;
  letter-spacing: 0.05em;
  color: var(--color-secondary);
}
.slider__price {
  font-size: 0.5em;
  font-weight: lighter;
  font-family: sans-serif;
}
.context {
  display: flex;
  font-weight: 900;
  font-size: 1.55rem;
  width: fit-content;
  margin-bottom: 3.05em;
  flex-direction: column;
  text-transform: capitalize;
}
.slider__footer {
  position: relative;
  margin-bottom: 1.5rem;
}
.slider__btns {
  width: 100%;
  font-size: 1.9em;
  align-items: center;
}
.slider__btn-buy {
  font-weight: bold;
  font-size: 0.55rem;
  letter-spacing: 1px;
  border-radius: 15px;
  text-transform: uppercase;
  padding: 0.65rem 2.2rem 0.5rem;
  background-color: rgba(var(--color-secondary-rgb), 0.533);
}
.slider__btn-switch {
  width: 30px;
  height: 30px;
  position: relative;
  border-radius: 50%;
  margin-inline-start: 0.7rem;
}
.slider__btn-switch::before {
  content: "";
  height: 0.3rem;
  width: 0.3rem;
  position: absolute;
  border: 2px solid;
  border-top: unset;
  border-left: unset;
  margin-inline-end: 0.2rem;
  transform: rotate(-45deg);
}
.slider__btn-switch--dark {
  background-color: var(--color-primary);
  transform: rotate(180deg);
}
.slider__btn-switch--dark::before {
  border-color: var(--color-secondary);
}
.slider__btn-switch--light {
  background-color: var(--color-secondary);
}
.slider__btn-switch--light::before {
  border-color: var(--color-primary);
}
.slider__index {
  right: 3%;
  gap: 0 5.7em;
  display: flex;
  font-weight: 900;
  font-weight: 600;
  font-size: 0.85em;
  visibility: hidden;
  position: absolute;
  font-family: system-ui;
  justify-content: center;
  bottom: calc(100% + 0.8em);
}
.slider__index :last-child {
  color: var(--color-secondary);
}
.slider__index::before {
--color-primary: #252525;
  top: 55%;
  height: 1%;
  width: 42%;
  content: "";
  position: absolute;
  transform-origin: center;
  background-color: var(--color-primary);
}

@media (min-width: 490px) {
  .slider__img {
    width: 29rem;
     margin-left:1.5rem;
    margin-bottom: -1rem;
  }
  .slider__context {
    font-size: 1.8rem;
  }
  .slider__index {
    visibility: visible;
  }
  .slider__price {
    font-size: 0.4em;
  }
  .slider__footer {
    font-size: 0.98rem;
    margin-bottom: 2.2em;
  }
}

@media (min-width: 830px) {
  .slider__img {
    width: 37rem;
     margin-left:1.5rem;
    margin-bottom: -11.5rem;
  }
  .slider__context {
    font-size: 2rem;
    margin-bottom: 1.15em;
  }
  .slider__footer {
    margin-bottom: 2.8em;
  }
}
  
  
  
 
 </style>


<!-- partial:index.partial.html -->
<div class="slider">
      <div class="inner-container">
        <div class="slider__wrraper flex-column">
          <div class="flex-column slider__content"></div>
          <div class="slider__footer">
            <div class="slider__btns justify-between">
              <button class="slider__btn-buy"></button>
              <div class="flex-center">
                <button
                  data-type="prev"
                  class="slider__btn-switch slider__btn-switch--dark flex-center"
                ></button>
                <button
                  data-type="next"
                  class="slider__btn-switch slider__btn-switch--light flex-center"
                ></button>
              </div>
            </div>
            <div class="slider__index"></div>
          </div>
        </div>
      </div>
    </div>
<!-- partial -->
  <script src='https://cdnjs.cloudflare.com/ajax/libs/gsap/3.6.1/gsap.min.js'></script>
 <script  src="./script3.js"></script>


