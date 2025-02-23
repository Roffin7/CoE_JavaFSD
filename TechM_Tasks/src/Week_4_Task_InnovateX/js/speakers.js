export class SpeakersManager {
    constructor() {
      this.speakers = [];
    }
  
    async loadSpeakers() {
      try {
        const response = await fetch("data/speakers.json");
        const data = await response.json();
        this.speakers = data.speakers;
        this.renderSpeakers();
      } catch (error) {
        console.error("Error loading speakers:", error);
      }
    }
  
    renderSpeakers() {
      const container = document.getElementById("speakers-grid");
      if (!container) return;
  
      container.innerHTML = this.speakers
        .map(
          (speaker) => `
        <div class="speaker-card">
          <img src="${speaker.image}" alt="${speaker.name}" class="speaker-image">
          <div class="speaker-info">
            <h3>${speaker.name}</h3>
            <p class="speaker-title">${speaker.title}</p>
            <p class="speaker-company">${speaker.company}</p>
            <p class="speaker-bio">${speaker.bio}</p>
          </div>
        </div>
      `
        )
        .join("");
    }
  }
  