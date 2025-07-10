// Facade Pattern for Home Theater
class Amplifier { void on(){} }
class DVDPlayer { void play(){} }
class HomeTheaterFacade {
    private Amplifier amp; private DVDPlayer dvd;
    public HomeTheaterFacade(Amplifier a, DVDPlayer d) { amp = a; dvd = d; }
    public void watchMovie() { amp.on(); dvd.play(); }
}
public class FacadePattern {
    public static void main(String[] args) {
        HomeTheaterFacade home = new HomeTheaterFacade(new Amplifier(), new DVDPlayer());
        home.watchMovie();
    }
}
