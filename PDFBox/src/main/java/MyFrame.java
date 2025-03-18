import org.apache.pdfbox.Loader;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.IOException;
import java.util.List;

public class MyFrame extends JFrame implements ActionListener {
    JButton buttonA, buttonB;
    String path, save;
    int count;
    PDDocument document;

    MyFrame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        buttonB = new JButton(" Select File");
        buttonB.addActionListener(this);
        buttonA = new JButton("Split");
        buttonA.addActionListener(this);
        document = null;
        frame.add(buttonB);
        frame.add(buttonA);
        frame.pack();
        frame.setSize(700,400);
        frame.setVisible(true);
        count=0;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        JFileChooser fileChooser = new JFileChooser();

        if (e.getSource() == buttonB ) {
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File acceptFile = new File(String.valueOf(fileChooser.getSelectedFile()));
                path = acceptFile.getAbsolutePath();
                save = acceptFile.getParent();
                System.out.println(path);
                count++;
                try {
                    document = Loader.loadPDF(acceptFile);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

            if (e.getSource() == buttonA ) {
                if(count==0)
                {

                    JOptionPane.showMessageDialog(this,"Please select a files","ERROR!",JOptionPane.ERROR_MESSAGE);
                }
                if (count == 1) {
                    Splitter splitter = new Splitter();

                    List<PDDocument> splitPages = null;
                    try {
                        splitPages = splitter.split(document);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    File newFile;
                    newFile = new File(save);
                    int num = 1;
                    for (PDDocument mydoc : splitPages) {

                        try {
                            mydoc.save(newFile + "\\split_0" + num + ".pdf");
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        num++;
                        try {
                            mydoc.close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    System.out.print("split done");

                    JOptionPane.showMessageDialog(this,"PDF splited \n check your PDF path","RESULT!",JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(this,"created by Parichoy Nandi \nEnrollment number: AU/2020/0004514 \nRoll number: UG/02/BCA/2020/022 \nBCA,CSE,SOET,ADAMAS UNIVERSITY \n ","Credit",JOptionPane.INFORMATION_MESSAGE);
                }

            }

        }
    }


