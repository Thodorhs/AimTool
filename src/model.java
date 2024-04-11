import java.io.*;
import java.util.Scanner;

public class model {
    private int aimcount;
    private long total;
    private int dificulty;
    private long timelapsed;
    private double reactiontime;
    private int wrongclicks;
    private HandleFile file;
    model(int di){
        wrongclicks=0;
        timelapsed=0;
        reactiontime=0;
        aimcount=0;
        total=0;
        dificulty=di;
    }
    public void restart(){
        wrongclicks=0;
        timelapsed=0;
        reactiontime=0;
        aimcount=0;
        total=0;
    }
    public void clock(long start,long end){
       timelapsed = end - start;
        System.out.println(timelapsed);
    }

    public long getTimelapsed() {
        return timelapsed;
    }
    public int getWrongclicks() {
        return wrongclicks;
    }
    public void addwrongclick(){
        wrongclicks++;
    }
    public int getDificulty() {
        return dificulty;
    }
    public int getAimcount(){
        return aimcount;
    }
    public long getTotal() {
        return total;
    }
    public void addaim(){
        aimcount++;
    }
    public void sleeps(){
        try {
            Thread.currentThread().sleep( (long) (dificulty*1000) );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void savehigheststat() throws IOException {
        file=new HandleFile();
        if(file.newfile()){
            file.writedif(dificulty);
            file.writeti(timelapsed);
            file.writereact(reactiontime);
        }else{
            if(dificulty>=file.getdificulty()){
                if(timelapsed<=file.gettime()){
                    file.del();
                    file.writedif(dificulty);
                    file.writeti(timelapsed);
                    file.writereact(reactiontime);
                }
            }
        }
    }
    public double gethigheststat(){
        return reactiontime;
    }
}
class HandleFile {
    File newfile;
    Scanner scan;
    private BufferedWriter myWriter;
    private BufferedReader myReader;

    Boolean newfile() {
        try {
            newfile = new File("save.txt");
            //etHiddenAttrib(newfile);
            if (newfile.createNewFile()) {
                scan=new Scanner(newfile);
                myWriter = new BufferedWriter(new FileWriter(newfile.getName()));
                return true;
            } else {
                scan=new Scanner(newfile);
                myWriter = new BufferedWriter(new FileWriter(newfile.getName()));
                myReader = new BufferedReader(new FileReader(newfile));
                return false;
            }
        } catch ( IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
    }
    void  writedif(double s) throws IOException {
        if(s==2){
            myWriter.write("0");
        }else if(s==1){
            myWriter.write("1");
        }else{
            myWriter.write("2");
        }
    }
    void  writeti(long s) throws IOException {
        myWriter.write(String.valueOf(s)+"\n");
    }
    void  writereact(double s) throws IOException {
        myWriter.write(String.valueOf(s)+"\n");
        myWriter.close();
    }




    int getdificulty() throws IOException {
        String s=myReader.readLine();
        if( s=="2"){
            return 2;
        }else if(s=="1"){
            return 1;
        }else{
            return 0;
        }
    }
    int gettime() throws IOException {

       // return Integer.parseInt(myReader.readLine());
        return myReader.read();
    }
    int getreaction() throws IOException {
        return Integer.parseInt(myReader.readLine());
    }


    void del() throws FileNotFoundException {
       newfile.delete();
    }
    private static void setHiddenAttrib(File file) {
        try {
            // execute attrib command to set hide attribute
            Process p = Runtime.getRuntime().exec("attrib +H " + file.getPath());
            // for removing hide attribute
            //Process p = Runtime.getRuntime().exec("attrib -H " + file.getPath());
            p.waitFor();
            if(file.isHidden()) {
                System.out.println(file.getName() + " hidden attribute is set to true");
            }else {
                System.out.println(file.getName() + " hidden attribute not set to true");
            }
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
