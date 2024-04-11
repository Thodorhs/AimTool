import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class sounds {
    static Clip clip;
    static Path currentPath = Paths.get("");;
    static Clip clipp;
    static int musicplaying;
    public static void headshot() {
        try {
            File musicpath = new File(currentPath.toAbsolutePath()+"\\sounds\\headshot.wav");
            AudioInputStream audioin = AudioSystem.getAudioInputStream(musicpath);
            clip = AudioSystem.getClip();
            clip.open(audioin);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public static void start() {
        try {
            File musicpath = new File(currentPath.toAbsolutePath()+"\\sounds\\start.wav");
            AudioInputStream audioin = AudioSystem.getAudioInputStream(musicpath);
            clip = AudioSystem.getClip();
            clip.open(audioin);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void shot() {
        try {
            File musicpath1 = new File(currentPath.toAbsolutePath()+"\\sounds\\Vandal.wav");
            AudioInputStream audioin = AudioSystem.getAudioInputStream(musicpath1);
            clip = AudioSystem.getClip();
            clip.open(audioin);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public static void headchange1() {
        try {
            File musicpath = new File(currentPath.toAbsolutePath()+"\\sounds\\headchange1.wav");
            AudioInputStream audioin = AudioSystem.getAudioInputStream(musicpath);
            clip = AudioSystem.getClip();
            clip.open(audioin);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public static void ambience() {
        try {
            musicplaying=1;
            File musicpath = new File(currentPath.toAbsolutePath()+"\\sounds\\sound.wav");
            AudioInputStream audioin = AudioSystem.getAudioInputStream(musicpath);
            clipp = AudioSystem.getClip();
            clipp.open(audioin);
            FloatControl gainControl = (FloatControl) clipp.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            clipp.start();

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public static void click() {
        try {
            File musicpath = new File(currentPath.toAbsolutePath()+"\\sounds\\button.wav");
            AudioInputStream audioin = AudioSystem.getAudioInputStream(musicpath);
            clip = AudioSystem.getClip();
            clip.open(audioin);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public static void mute(){
       if(musicplaying==1){
           clipp.stop();
           musicplaying=0;
       }else{
           clipp.start();
           musicplaying=1;
       }

    }
}
