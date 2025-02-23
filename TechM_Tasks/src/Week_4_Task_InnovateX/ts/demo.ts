// Conference Speaker Management

interface Speaker {
    name: string;
    topic: string;
    time: string;
}

const speakers: Speaker[] = [
    { name: "John Doe", topic: "Future of AI", time: "10:00 AM" },
    { name: "Jane Smith", topic: "Cybersecurity Trends", time: "11:30 AM" },
    { name: "Alice Johnson", topic: "Web 3.0 & Blockchain", time: "2:00 PM" }
];

function showSpeakers(speakers: Speaker[]): void {
    speakers.forEach((speaker, index) => 
        console.log(`${index + 1}. ${speaker.name} - "${speaker.topic}" at ${speaker.time}`)
    );
}

function getSpeaker(index: number): Speaker | string {
    return speakers[index] ?? "Speaker not found!";
}

showSpeakers(speakers);
console.log(getSpeaker(1));
console.log(getSpeaker(10));
