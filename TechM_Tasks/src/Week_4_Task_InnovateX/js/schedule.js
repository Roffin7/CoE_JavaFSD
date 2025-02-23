export class ScheduleManager {
    constructor() {
      this.schedule = [];
      this.currentTrack = "all";
    }
  
    async loadSchedule() {
      try {
        const response = await fetch("data/schedule.json");
        const data = await response.json();
        this.schedule = data.schedule;
        this.renderSchedule();
        this.setupFilters();
      } catch (error) {
        console.error("Error loading schedule:", error);
      }
    }
  
    setupFilters() {
      const filterButtons = document.querySelectorAll(".filter-btn");
      filterButtons.forEach((button) => {
        button.addEventListener("click", (e) => {
          const track = e.target.dataset.track || "all";
          this.filterSchedule(track);
  
          filterButtons.forEach((btn) => btn.classList.remove("active"));
          e.target.classList.add("active");
        });
      });
    }
  
    filterSchedule(track) {
      this.currentTrack = track;
      this.renderSchedule();
    }
  
    renderSchedule() {
      const container = document.getElementById("schedule-container");
      if (!container) return;
  
      const filteredSchedule =
        this.currentTrack === "all"
          ? this.schedule
          : this.schedule.filter(
              (item) => item.track === this.currentTrack || item.track === "all"
            );
  
      container.innerHTML = filteredSchedule
        .map(
          (item) => `
          <div class="schedule-item">
            <div class="schedule-time">${item.time}</div>
            <div class="schedule-content">
              <h3>${item.title}</h3>
              <p>Speaker: ${item.speaker}</p>
              <p>Location: ${item.location}</p>
            </div>
          </div>
        `
        )
        .join("");
    }
  }
  