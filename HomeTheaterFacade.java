class TV {
    public void on() { System.out.println("TV is ON"); }
    public void off() { System.out.println("TV is OFF"); }
}

class SoundSystem {
    public void on() { System.out.println("Sound System is ON"); }
    public void off() { System.out.println("Sound System is OFF"); }
}

class DVDPlayer {
    public void on() { System.out.println("DVD Player is ON"); }
    public void off() { System.out.println("DVD Player is OFF"); }
    public void play() { System.out.println("DVD is playing"); }
}

class HomeTheaterFacade {
    private TV tv;
    private SoundSystem sound;
    private DVDPlayer dvd;

    public HomeTheaterFacade(TV tv, SoundSystem sound, DVDPlayer dvd) {
        this.tv = tv;
        this.sound = sound;
        this.dvd = dvd;
    }

    public void watchMovie() {
        tv.on();
        sound.on();
        dvd.on();
        dvd.play();
    }

    public void endMovie() {
        dvd.off();
        sound.off();
        tv.off();
    }
}
