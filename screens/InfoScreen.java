package screens;
//The GUI panel that displays at least 10 pages of Discrete Math lessons.
// Created by: Thiveya Shree a/p Baskaran (106564)
// Tested by: Arif Amirul Aiman Bin Marzuki (83282)

import javax.swing.*;

import core.Navigator;
import interfaces.InterfaceInfo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoScreen extends JPanel implements InterfaceInfo {
    
    // UI Components
    private JTextArea lessonTextArea;
    private JLabel imageLabel;
    private JButton nextBtn;
    private JButton prevBtn;
    private JButton backBtn;
    @SuppressWarnings("unused")
    private Navigator navigator;
    
    
    // Tracking the current page number
    private int currentPage = 0;
    
    // Arrays to hold at least 10 pages of content
    private String[] pageTexts = new String[10]; // ***EDIT String[n] to increase/decrease number of pages***
    private String[] imagePaths = new String[10]; // ***EDIT String[n] to increase/decrease number of pages***

    public InfoScreen(Navigator navigator) {
        // Set up the layout
        setLayout(new BorderLayout());

        Color blondeColor = new Color(245, 245, 220);
        setBackground(blondeColor);
        
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
        backBtn = new JButton("Back to Menu");
        prevBtn = new JButton("Previous");
        nextBtn = new JButton("Next");
        buttonPanel.add(backBtn);
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

        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                navigator.showScreen("HOME");
            }
        });

        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // This string must exactly match what you named it in Main.java
                navigator.showScreen("Home Screen"); 
            }
        });

        // 6. Show the first page when it loads
        updateDisplay(currentPage);
    }

    @Override
    public void setupContent() {
        pageTexts[0] = "Page 1: Relations \nThis is a matching rule where every starting number on the left is paired with exactly one specific number on the right.";
        pageTexts[1] = "Page 2: Conditional Statement \nThe logical conditional p -> q evaluate to TRUE in all scenario unless the premise of p is TRUE and conclusion q is FALSE.";
        pageTexts[2] = """
                        Page 3: Set Theory \nAn intersection creates a new group containing only the specific items that are present in both of the original sets.
                                            \nA union merges all items from the original sets into one large group without counting any recurring items twice.
                                            \nA proper subset is a smaller group whose items are all found inside a larger group, but the two groups are not exactly identical.
                                            \nA subset is a group whose items are all found inside another group, even if both groups happen to contain the exact same items.
                                            \nNot a proper subset means that a group is either not contained within another group at all, or it is entirely identical to it.
                                            \nA superset is the larger overarching group that entirely contains all the items of a smaller group.
                                            \nBeing a member means a specific element exists inside that particular group.
                                            \nNot being a member means that specific element cannot be found anywhere inside the group.
                        """;
        pageTexts[3] = "Page 4: Probability \nMutually Exclusive \nTwo separate outcomes could never occur at the same time. In probability, this event occurring at either the sum of their individual chances.";
        pageTexts[4] = "Page 5: Probability \nNon-mutually Exclusive \nTwo separate outcomes can occur at the exact same time. In probability, the chance of either event occurring is the sum of their individual chances minus the overlapping probability of them both happening together.";
        pageTexts[5] = """
                        Page 6: Predicates and Quantifiers \nThe upside down \"A\" stands for \"All\" which means a universal condition.
                                                            \nThe flipped "E" symbol is for "Exist", at least it is applied to one element but not all.
                                                            \nThe hook symbol is a negation symbol. It's simply logically flipped of the element that follows it.
                        """;
        pageTexts[6] = """
                        Page 7: Propositional Logic \nAtomic Propositions: A simple statement with no logical connectives that cannot be broken down further.
                                                    \nCompound Propositions: A statement formed by combining atomic propositions using logical connectives like AND, OR, or NOT.
                                                    \nNegation (NOT): A statement formed by completely reversing the truth value of a single proposition.
                                                    \nConjunction (AND): A compound proposition formed by connecting two statements, which evaluates to true strictly when both individual statements are true.
                                                    \nDisjunction (OR): A compound proposition formed by connecting two statements, which evaluates to true when at least one of the individual statements is true.
                                                    \nConditional (If...Then): A compound proposition where the first statement acts as a strict condition that, if true, guarantees the truth of the second statement.
                                                    \nBiconditional (If and only if): A compound proposition formed by connecting two statements, which evaluates to true strictly when both statements share the exact same truth value.
                        """;
        pageTexts[7] = "Page 8: Set Identities \nSet identities act as the fundamental operational laws for simplifying complex mathematical groups and logical statements. It establish how universal and empty sets interact to preserve or override data, how to safely rearrange or distribute independent sets without compromising the final mathematical outcome and how to utilize rules like De Morgan's and Absorption to handle logical opposites and immediately eliminate redundant information.";
        pageTexts[8] = """
                        Page 9: Relations and Functions \nReflexive: Every single point within the system contains an arrow that loops directly back onto itself.
                                                        \nSymmetric: Any arrow pointing from one point to another is immediately mirrored by a return arrow pointing in the exact opposite direction.
                                                        \nTransitive: A path traveling from a first point to a second, and then continuing to a third, is always accompanied by a shortcut arrow pointing directly from the first point to the third.
                        """;
        pageTexts[9] = "Page 10: Cartesian Product \nAn operation that creates a new set containing all possible ordered pairs, where the first element in every pair is drawn strictly from Set A, and the second element is drawn strictly from Set B (A X B)";

        // Image path
        imagePaths[0] = "img/dm1.jpg"; 
        imagePaths[1] = "img/dm2.png";
        imagePaths[2] = "img/dm3.png";
        imagePaths[3] = "img/dm4.png";
        imagePaths[4] = "img/dm5.png";
        imagePaths[5] = "img/dm6.png";
        imagePaths[6] = "img/dm7.png";
        imagePaths[7] = "img/dm8.jpeg";
        imagePaths[8] = "img/dm9.png";
        imagePaths[9] = "img/dm10.png";
    }
// ^------------^ Edit Image ^------------^

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