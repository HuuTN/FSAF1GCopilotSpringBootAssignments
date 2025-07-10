public class AudioPlayer implements MediaPlayer {
    public void play(String type, String file) {
        if (type.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3: " + file);
        } else if (type.equalsIgnoreCase("mp4") || type.equalsIgnoreCase("vlc")) {
            new MediaAdapter(type).play(type, file);
        } else {
            System.out.println("Invalid format: " + type);
        }
    }
}
