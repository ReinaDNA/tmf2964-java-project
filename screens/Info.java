package screens;
//The GUI panel that displays at least 10 pages of Discrete Math lessons.
//Created by : Tvya 
import javax.swing.*;

import interfaces.InterfaceInfo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Info extends JPanel implements InterfaceInfo {
    
    // UI Components
    private JTextArea lessonTextArea;
    private JLabel imageLabel;
    private JButton nextBtn;
    private JButton prevBtn;
    
    // Tracking the current page number
    private int currentPage = 0;
    
    // Arrays to hold at least 10 pages of content
    private String[] pageTexts = new String[10]; // ***EDIT String[n] to increase/decrease number of pages***
    private String[] imagePaths = new String[10]; // ***EDIT String[n] to increase/decrease number of pages***

    public Info() {
        // Set up the layout
        setLayout(new BorderLayout());
        
        // 1. Load the data
        setupContent();

        // 2. Set up the Text Area (Top)
        lessonTextArea = new JTextArea(10, 30);
        lessonTextArea.setLineWrap(true);
        lessonTextArea.setWrapStyleWord(true);
        lessonTextArea.setEditable(false); // Users shouldn't type here
        lessonTextArea.setMargin(new Insets(10, 10, 10, 10));
        add(new JScrollPane(lessonTextArea), BorderLayout.NORTH);

        // 3. Set up the Image Area (Center)
        imageLabel = new JLabel("Image will appear here", SwingConstants.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        // 4. Set up the Buttons (Bottom)
        JPanel buttonPanel = new JPanel();
        prevBtn = new JButton("Previous");
        nextBtn = new JButton("Next");
        buttonPanel.add(prevBtn);
        buttonPanel.add(nextBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        // 5. Button Actions (The logic to flip pages)
        nextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentPage < pageTexts.length - 1) { 
                    currentPage++;
                    updateDisplay(currentPage);
                }
            }
        });

        prevBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentPage > 0) {
                    currentPage--;
                    updateDisplay(currentPage);
                }
            }
        });

        // 6. Show the first page when it loads
        updateDisplay(currentPage);
    }

    @Override
    public void setupContent() {
// ----------------- Hey Tyva! Edit your TEXT here -----------------
        pageTexts[0] = "Page 1: Welcome to Quality Education. Let's learn Basic Discrete Math!";
        pageTexts[1] = "Page 2: Put your topic 1 text here.";
        pageTexts[2] = "Page 3: Put your topic 2 text here.";
        pageTexts[3] = "Page 4: Put your topic 3 text here.";
        pageTexts[4] = "Page 5: Put your topic 4 text here.";
        pageTexts[5] = "Page 6: Put your topic 5 text here.";
        pageTexts[6] = "Page 7: Put your topic 6 text here.";
        pageTexts[7] = "Page 8: Put your topic 7 text here.";
        pageTexts[8] = "Page 9: Put your topic 8 text here.";
        pageTexts[9] = "Page 10: You finished the lesson! Ready for the quiz?";
// ---- Copy and paste the line above if you decide to add more pages, just remember to also increase the array size above at the initialization. ----
// ----------------- Hey Tyva! Edit your TEXT here -----------------

// ----------- Hey Tyva! Edit your IMAGE here -----------
// ---- Change the word dummy to your actual image names ----
        imagePaths[0] = "img/dm1.jpg"; 
        imagePaths[1] = "img/dummy.jpg";
        imagePaths[2] = "img/dummy.jpg";
        imagePaths[3] = "img/dummy.jpg";
        imagePaths[4] = "img/dummy.jpg";
        imagePaths[5] = "img/dummy.jpg";
        imagePaths[6] = "img/dummy.jpg";
        imagePaths[7] = "img/dummy.jpg";
        imagePaths[8] = "img/dummy.jpg";
        imagePaths[9] = "img/dummy.jpg";
// ---- Copy and paste the line above if more pages needed, just remember to also increase the array size above at the initialization. ----
    }
// ^------------^ Hey Tyva! Edit your IMAGE here ^------------^

    @Override
    public void updateDisplay(int pageIndex) {
        // Update the text
        lessonTextArea.setText(pageTexts[pageIndex]);
        
        // Update the image with scaling!
        try {
            // 1. Load the original, giant image
            ImageIcon originalIcon = new ImageIcon(imagePaths[pageIndex]);
            Image originalImage = originalIcon.getImage();
            
            // 2. Scale it down to fit our app (e.g., 350 width, 350 height)
            // Image.SCALE_SMOOTH makes sure it resizes cleanly without looking too pixelated
            Image scaledImage = originalImage.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
            
            // 3. Put the new, smaller image into the label
            imageLabel.setIcon(new ImageIcon(scaledImage));
            imageLabel.setText(""); // clear the placeholder text
            
        } catch (Exception e) {
            imageLabel.setIcon(null);
            imageLabel.setText("Image missing: " + imagePaths[pageIndex]);
        }

        // Disable 'Previous' on the first page, and 'Next' on the last page
        prevBtn.setEnabled(pageIndex > 0);
        nextBtn.setEnabled(pageIndex < pageTexts.length - 1);
    }
}