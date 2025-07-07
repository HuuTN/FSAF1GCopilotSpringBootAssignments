public class MediaAdapter implements MediaPlayer {
    AdvancedPlayer advPlayer;
    public MediaAdapter(String type) {
        if (type.equalsIgnoreCase("vlc")) advPlayer = new VlcPlayer();
        else if (type.equalsIgnoreCase("mp4")) advPlayer = new Mp4Player();
    }
    public void play(String type, String file) { advPlayer.playFile(file); }
}
