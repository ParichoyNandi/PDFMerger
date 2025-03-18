import org.apache.pdfbox.multipdf.PDFMergerUtility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MyFrame2 extends JFrame implements ActionListener {
    JButton button1, button2, button3, button4, button5, button6, mergeButton;
    JLabel label1, label2, label3, label4, label5;
    int count = 0;
    String path1, path2, path3, path4, path5, save;
    PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();

    MyFrame2() {
        this.setTitle("PDF Merger Tool");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Create buttons and labels
        button1 = new JButton("Select 1st File");
        button1.addActionListener(this);
        label1 = new JLabel("No file selected");

        button2 = new JButton("Select 2nd File");
        button2.addActionListener(this);
        label2 = new JLabel("No file selected");

        button4 = new JButton("Select 3rd File");
        button4.addActionListener(this);
        label3 = new JLabel("No file selected");

        button5 = new JButton("Select 4th File");
        button5.addActionListener(this);
        label4 = new JLabel("No file selected");

        button6 = new JButton("Select 5th File");
        button6.addActionListener(this);
        label5 = new JLabel("No file selected");

        mergeButton = new JButton("Merge PDFs");
        mergeButton.addActionListener(this);
        mergeButton.setBackground(Color.GREEN);
        mergeButton.setForeground(Color.WHITE);

        // Add components to layout
        gbc.gridx = 0; gbc.gridy = 0;
        this.add(button1, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        this.add(label1, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        this.add(button2, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        this.add(label2, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        this.add(button4, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        this.add(label3, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        this.add(button5, gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        this.add(label4, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        this.add(button6, gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        this.add(label5, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        this.add(mergeButton, gbc);

        this.pack();
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        if (e.getSource() == button1) {
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File acceptFile = fileChooser.getSelectedFile();
                path1 = acceptFile.getAbsolutePath();
                save = acceptFile.getParent();
                label1.setText(acceptFile.getName());
                count++;
            }
        } else if (e.getSource() == button2) {
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File acceptFile = fileChooser.getSelectedFile();
                path2 = acceptFile.getAbsolutePath();
                label2.setText(acceptFile.getName());
                count++;
            }
        } else if (e.getSource() == button4) {
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File acceptFile = fileChooser.getSelectedFile();
                path3 = acceptFile.getAbsolutePath();
                label3.setText(acceptFile.getName());
                count++;
            }
        } else if (e.getSource() == button5) {
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File acceptFile = fileChooser.getSelectedFile();
                path4 = acceptFile.getAbsolutePath();
                label4.setText(acceptFile.getName());
                count++;
            }
        } else if (e.getSource() == button6) {
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File acceptFile = fileChooser.getSelectedFile();
                path5 = acceptFile.getAbsolutePath();
                label5.setText(acceptFile.getName());
                count++;
            }
        } else if (e.getSource() == mergeButton) {
            if (count < 2) {
                JOptionPane.showMessageDialog(this, "Please select at least 2 files", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            pdfMergerUtility.setDestinationFileName(save + "\\MERGED.pdf");
            try {
                if (path1 != null) pdfMergerUtility.addSource(path1);
                if (path2 != null) pdfMergerUtility.addSource(path2);
                if (path3 != null) pdfMergerUtility.addSource(path3);
                if (path4 != null) pdfMergerUtility.addSource(path4);
                if (path5 != null) pdfMergerUtility.addSource(path5);
                pdfMergerUtility.mergeDocuments(null);

                JOptionPane.showMessageDialog(this, "PDF merged successfully!\nSaved in: " + save, "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error during merging: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new MyFrame2();
    }
}
