import { CountdownTimer } from "./countdown.js";
import { ScheduleManager } from "./schedule.js";
import { SpeakersManager } from "./speakers.js";

document.addEventListener("DOMContentLoaded", () => {
  const conferenceDate = new Date("2025-03-01T09:00:00");
  const countdown = new CountdownTimer(conferenceDate);
  countdown.start("countdown");

  const speakersManager = new SpeakersManager();
  speakersManager.loadSpeakers();

  const scheduleManager = new ScheduleManager();
  scheduleManager.loadSchedule();

  document.querySelectorAll('a[href^="#"]').forEach((anchor) => {
    anchor.addEventListener("click", function (e) {
      e.preventDefault();
      const href = this.getAttribute("href");
      if (href) {
        document.querySelector(href)?.scrollIntoView({
          behavior: "smooth",
        });
      }
    });
  });

  const hamburger = document.querySelector(".hamburger");
  const navLinks = document.querySelector(".nav-links");

  hamburger?.addEventListener("click", () => {
    navLinks?.classList.toggle("active");
  });

  const contactForm = document.getElementById("contact-form");
  contactForm?.addEventListener("submit", (e) => {
    e.preventDefault();

    const nameInput = document.getElementById("name");
    const emailInput = document.getElementById("email");
    const messageInput = document.getElementById("message");

    if (validateForm(nameInput, emailInput, messageInput)) {
      console.log("Form submitted:", {
        name: nameInput.value,
        email: emailInput.value,
        message: messageInput.value,
      });
      contactForm.reset();
      alert("Thank you for your message!");
    }
  });
});

function validateForm(nameInput, emailInput, messageInput) {
  let isValid = true;

  if (!nameInput.value.trim()) {
    markInvalid(nameInput, "Please enter your name");
    isValid = false;
  } else {
    markValid(nameInput);
  }

  if (!emailInput.value.trim() || !isValidEmail(emailInput.value)) {
    markInvalid(emailInput, "Please enter a valid email address");
    isValid = false;
  } else {
    markValid(emailInput);
  }

  if (!messageInput.value.trim()) {
    markInvalid(messageInput, "Please enter your message");
    isValid = false;
  } else {
    markValid(messageInput);
  }

  return isValid;
}

function isValidEmail(email) {
  return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}

function markInvalid(element, message) {
  element.classList.add("invalid");
  const errorDiv = document.createElement("div");
  errorDiv.className = "error-message";
  errorDiv.textContent = message;
  element.parentNode?.appendChild(errorDiv);
}

function markValid(element) {
  element.classList.remove("invalid");
  const errorMessage = element.parentNode?.querySelector(".error-message");
  if (errorMessage) {
    errorMessage.remove();
  }
}


