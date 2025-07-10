// Adapter Pattern for MediaPlayer
interface MediaPlayer { void play(String file); }
class AudioPlayer implements MediaPlayer { public void play(String file) { System.out.println("Playing " + file); } }
class OldMediaPlayer { public void playFile(String filename) { System.out.println("Old: " + filename); } }
class MediaPlayerAdapter implements MediaPlayer {
    private OldMediaPlayer old;
    public MediaPlayerAdapter(OldMediaPlayer o) { old = o; }
    public void play(String file) { old.playFile(file); }
}
public class AdapterPattern {
    public static void main(String[] args) {
        MediaPlayer player = new MediaPlayerAdapter(new OldMediaPlayer());
        player.play("song.mp3");
    }
}
