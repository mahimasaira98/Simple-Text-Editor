import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class simpletexteditor extends JFrame implements ActionListener {
   JFrame frame;
   JTextArea textArea;
    simpletexteditor(){
        //Created the frame
        frame = new JFrame("Simple text editor");
        //Created the text area
        textArea = new JTextArea();
        //Adding the text area to our frame
        frame.add(textArea);
        //giving size to our frame
        frame.setSize(800,800);
        frame.setVisible(true);
        //creating menu bar
        JMenuBar menuBar = new JMenuBar();
        //created two menu
        JMenu FileMenu = new JMenu("File Menu");
        JMenu EditMenu = new JMenu("Edit Menu");
        menuBar.add(FileMenu);
        menuBar.add(EditMenu);
        //Created the menu items
        JMenuItem New = new JMenuItem("New File");
        JMenuItem open = new JMenuItem("Open File");
        JMenuItem save = new JMenuItem("Save File");
        JMenuItem print = new JMenuItem("Print File");
        save.addActionListener(this);
        open.addActionListener(this);
        print.addActionListener(this);
        New.addActionListener(this);
        //adding items to menu
        FileMenu.add(New);
        FileMenu.add(open);
        FileMenu.add(save);
        FileMenu.add(print);
        JMenuItem Cut = new JMenuItem("Cut");
        JMenuItem Copy = new JMenuItem("Copy");
        JMenuItem Paste = new JMenuItem("Paste");
        JMenuItem Close = new JMenuItem("Close");
        EditMenu.add(Cut);
        EditMenu.add(Copy);
        EditMenu.add(Paste);
        EditMenu.add(Close);
        //Adding fuctionality
        Cut.addActionListener(this);
        Copy.addActionListener(this);
        Paste.addActionListener( this);
        Close.addActionListener(this);
        //Adding menu bar to frame
        frame.setJMenuBar(menuBar);
        frame.show();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        simpletexteditor Editor = new simpletexteditor();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s=="Cut"){
            //this will cut the selected area
            textArea.cut();
        }
        else if(s=="Copy"){
            //this will copy the selected area
            textArea.copy();
        }
        else if(s=="Paste"){
            //this will paste the text in clipboard
            textArea.paste();
        }
        else if(s=="Close"){
            frame.setVisible(false);
        }
        else if(s=="Save File"){
                JFileChooser jFileChooser = new JFileChooser("C:");
                int ans = jFileChooser.showOpenDialog(null);
                if(ans==JFileChooser.APPROVE_OPTION){
                    File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                    BufferedWriter writer = null;
                    try {
                        writer = new BufferedWriter(new FileWriter(file,false));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        writer.write(textArea.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        writer.flush();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        writer.close();
                    } catch (IOException ex) {

                    }

                }
            }
        else if(s=="Print File"){
            try {
                textArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(s=="Open File"){
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans = jFileChooser.showOpenDialog(null);
            if(ans == JFileChooser.APPROVE_OPTION){
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                try {
                    String s1="",s2="";
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    s2=bufferedReader.readLine();
                    while((s1=bufferedReader.readLine())!=null){
                        s2+=s1+"\n";
                    }
                     textArea.setText(s2);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
          else if(s=="New File") {
              textArea.setText("");
        }
        }
    }

