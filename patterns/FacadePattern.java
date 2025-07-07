// Facade pattern example
class HomeTheaterFacade {
    private Amplifier amp = new Amplifier();
    private DVDPlayer dvd = new DVDPlayer();
    public void watchMovie() {
        amp.on();
        dvd.on();
        dvd.play();
    }
}
class Amplifier { void on() { System.out.println("Amp on"); } }
class DVDPlayer { void on() { System.out.println("DVD on"); } void play() { System.out.println("Playing DVD"); } }
