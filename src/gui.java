import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class gui {
    public sounds sound;
    public JFrame window;
    public JButton blank;
    public JButton start;
    public JButton head;
    public JButton mute;
    JLabel label;
    public JLayeredPane panel;
    public Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    public model model;
    long startime=0;
    long endtime=0;

    gui(){

        window=new JFrame();
        panel=new JLayeredPane();
        blank=new JButton();
        head=new JButton();
        start = new JButton();
        sound=new sounds();
        mute=new JButton();
        label=new JLabel();
        startgame();
    }
    private void initialize(){

        window.setBounds(0,0,dim.width,dim.height);
        panel.setBounds(0,0,dim.width,dim.height);
        window.setUndecorated(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel.setLayout(null);
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("images\\background.jpg").getImage().getScaledInstance(1920, 1080, Image.SCALE_DEFAULT));
      //  label.setIcon(imageIcon);
        //label.setBounds(0,0,dim.width,dim.height);
        //panel.add(label);
      blanklistener blankclick=new blanklistener();
        blank.addActionListener(blankclick);
        blank.setSize(1920,1080);
       // blank.setBackground(Color.white);
        blank.setIcon(imageIcon);
        blank.setFocusPainted(false);
        blank.setFocusable(false);
        blank.setRolloverEnabled(false);
        panel.add(blank);
        headlistener headclick=new headlistener();

        head.addActionListener(headclick);
        ImageIcon icon = new ImageIcon("images\\a.png");
        Image img = icon.getImage() ;
        Image newimg = img.getScaledInstance(50 , 50,  java.awt.Image.SCALE_SMOOTH ) ;
        icon = new ImageIcon( newimg );
        head.setIcon(icon);
        head.setSize(50,50);

        head.setRolloverEnabled(false);
        head.setVisible(false);
        panel.add(head);
        head.setOpaque(true);
        head.setBackground(null);
        startlistener startclick=new startlistener();
        start.addActionListener(startclick);
        start.setRolloverEnabled(false);
        start.setBounds(dim.width/2-80,dim.height/2-20,150,50);
        start.setBackground(Color.GREEN);
        start.setText("Start");

        ImageIcon icon2 = new ImageIcon("images\\mute.png");
        Image img2 = icon2.getImage() ;
        Image newimg2 = img2.getScaledInstance(30 , 30,  java.awt.Image.SCALE_SMOOTH ) ;
        icon2 = new ImageIcon( newimg2 );
        mute.setIcon(icon2);
        mutelistener muteclick=new mutelistener();
        mute.addActionListener(muteclick);
        mute.setBounds(dim.width-30,dim.height-30,30,30);
        panel.add(mute);
        panel.add(start);
        panel.setLayer(blank,0);
        panel.setLayer(start,1);
        panel.setLayer(head,panel.highestLayer());

        window.add(panel);
        window.setVisible(true);
        panel.revalidate();
    }
    JFrame startframe=new JFrame();
    private void startgame(){
        sound.ambience();
        JButton easy=new JButton();
        JButton medium=new JButton();
        JButton hard=new JButton();
        startframe.setBounds(600,200,300,400);
        startframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        startframe.setTitle("Select difficulty");

        easy.setText("easy");
        easylistener easylisten=new easylistener();
        easy.addActionListener(easylisten);
        easy.setBounds(75,50,153,40);

        medium.setText("medium");
        mediumlistener mediumlisten=new mediumlistener();
        medium.addActionListener(mediumlisten);
        medium.setBounds(75,130,153,40);

        hard.setText("hard");
        hardlistener hardlisten=new hardlistener();
        hard.addActionListener(hardlisten);
        hard.setBounds(75,210,153,40);
        panel.setOpaque(true);
        startframe.setLayout(null);
        Font font=new Font("Times New Roman",Font.BOLD,20);
        JLabel l=new JLabel("AIMTOOL BETA",JLabel.CENTER);
        l.setFont(font);
        l.setOpaque(true);
        l.setBackground(Color.LIGHT_GRAY);
        l.setBounds(0,0,300,40);
        startframe.add(l);
        startframe.add(hard);
        startframe.add(medium);
        startframe.add(easy);
        startframe.validate();
        startframe.setVisible(true);
    }
    private void endgame() throws IOException {
        model.savehigheststat();
        panel.removeAll();
        JLabel time=new JLabel();
        time.setBounds(dim.width/2-190,dim.height/2,300,50);
        Font font=new Font("Courier",Font.BOLD,15);
        time.setOpaque(true);
        time.setFont(font);
        time.setText("Time elapsed : "+ String.valueOf(model.getTimelapsed()/1000));
        JLabel speed=new JLabel();
        speed.setFont(font);
        speed.setBounds(dim.width/2-190,dim.height/2+100,300,50);
        speed.setOpaque(true);
        speed.setText("Average reaction time : "+String.valueOf((double)(model.getTimelapsed()/1000)/4));
        JLabel wrong=new JLabel();
        wrong.setFont(font);
        wrong.setBounds(dim.width/2-190,dim.height/2+200,300,50);
        wrong.setOpaque(true);
        wrong.setText("Wrong shots : "+String.valueOf(model.getWrongclicks()));
        JLabel restart=new JLabel();
        restart.setOpaque(false);
        restart.setLayout(new FlowLayout());
        restart.setFont(font);
        restart.setBounds(dim.width/2-190,dim.height/2+300,300,50);
        restart.setText("Restart ?");
        JButton yes=new JButton();
        yes.setBounds(dim.width/2-190,dim.height/2+360,70,25);
        yes ye=new yes();
        yes.addActionListener(ye);
        yes.setText("yes");
        JButton no=new JButton();
        no n=new no();
        no.addActionListener(n);
        no.setBounds(dim.width/2+40,dim.height/2+360,70,25);
        no.setText("no");
        panel.add(no);
        panel.add(yes);
        panel.add(restart);
        panel.add(time);
        panel.add(speed);
        panel.add(wrong);
        panel.revalidate();
    }
    private class easylistener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sound.click();
            sound.start();
            startframe.dispose();
            model=new model(20);
            initialize();
        }
    }
    private class yes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sound.click();
            window.dispose();
            control control=new control();
            control.creategui();
        }
    }
    private class no implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sound.click();
            window.dispose();
            return;
        }
    }
    private class mutelistener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sound.mute();
        }
    }
    private class mediumlistener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sound.click();
            sound.start();
            startframe.dispose();
            model=new model(10);
            initialize();

        }
    }
    private class hardlistener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sound.click();
            sound.start();
            startframe.dispose();
            model=new model(8);
            initialize();
        }
    }
    private class headlistener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sound.shot();
            head.setVisible(false);
            model.addaim();
            panel.revalidate();
        }
    }
    private class startlistener implements ActionListener {
        Timer tm=new Timer((int)model.getDificulty()*100,this);

        @Override
        public void actionPerformed(ActionEvent e) {
            if(model.getAimcount()<4){
                sound.headchange1();
                if(startime==0){
                    startime=System.currentTimeMillis();
                }
                start.setVisible(false);
                Random randx = new Random();
                Random randy = new Random();
                int x = randx.nextInt(dim.width - 50);
                int y = randy.nextInt(dim.height - 50);
                head.setLocation(x,y);
                panel.setLayer(head,2);
                panel.setLayer(head, panel.highestLayer());
                head.setVisible(true);
                panel.revalidate();
                window.revalidate();
                tm.start();
            }else{
                endtime = System.currentTimeMillis();
                model.clock(startime,endtime);
                tm.stop();
                try {
                    endgame();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }
    private class blanklistener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sound.shot();
            model.addwrongclick();
        }
    }
}
