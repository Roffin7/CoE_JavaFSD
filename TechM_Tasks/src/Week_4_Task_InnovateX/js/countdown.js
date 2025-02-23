export class CountdownTimer {
    constructor(endDate) {
      this.endDate = new Date(endDate);
      this.timerInterval = null;
    }
  
    start(elementId) {
      const updateTimer = () => {
        const now = new Date().getTime();
        const distance = this.endDate.getTime() - now;
  
        const days = Math.floor(distance / (1000 * 60 * 60 * 24));
        const hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        const minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        const seconds = Math.floor((distance % (1000 * 60)) / 1000);
  
        const element = document.getElementById(elementId);
        if (element) {
          element.innerHTML = `
            <div class="countdown-item">
              <div class="countdown-value">${days}</div>
              <div class="countdown-label">Days</div>
            </div>
            <div class="countdown-item">
              <div class="countdown-value">${hours}</div>
              <div class="countdown-label">Hours</div>
            </div>
            <div class="countdown-item">
              <div class="countdown-value">${minutes}</div>
              <div class="countdown-label">Minutes</div>
            </div>
            <div class="countdown-item">
              <div class="countdown-value">${seconds}</div>
              <div class="countdown-label">Seconds</div>
            </div>
          `;
        }
  
        if (distance < 0) {
          clearInterval(this.timerInterval);
          if (element) {
            element.innerHTML = "Conference has started!";
          }
        }
      };
  
      updateTimer();
      this.timerInterval = setInterval(updateTimer, 1000);
    }
  
    stop() {
      clearInterval(this.timerInterval);
    }
  }
  