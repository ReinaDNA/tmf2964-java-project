package screens;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import core.Navigator;

public class LeaderBoardScreen extends JPanel {
    private JTable leaderboardTable;
    private DefaultTableModel tableModel;
    @SuppressWarnings("unused")
    private Navigator navigator;
    
    public LeaderBoardScreen(Navigator navigator) {
        this.navigator = navigator;
        
        // Set layout to BoxLayout (Y_AXIS) like other screens
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(50, 40, 50, 40));
        
        // Create title
        JLabel titleLabel = new JLabel("Leaderboard");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, titleLabel.getPreferredSize().height));
        add(titleLabel);
        add(Box.createVerticalStrut(30));
        
        // Create table with columns
        String[] columns = {"Username", "Score", "Percentage", "Date"};
        tableModel = new DefaultTableModel(columns, 0);
        leaderboardTable = new JTable(tableModel);
        leaderboardTable.setFillsViewportHeight(true);
        
        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(leaderboardTable);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setMaximumSize(new Dimension(600, 400));
        add(scrollPane);
        
        add(Box.createVerticalStrut(30));
        
        // Create the return button
        JButton homeButton = new JButton("Return to Home Screen");
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        homeButton.setMaximumSize(new Dimension(250, 40));
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigator.showScreen("Home Screen");
            }
        });
        add(homeButton);
        
        // Load data from file
        loadLeaderboardData();
        
        // Auto-refresh when screen is shown
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                tableModel.setRowCount(0);
                loadLeaderboardData();
            }
        });
    }
    
    private void loadLeaderboardData() {
        File file = new File("data/scores.txt");
        if (!file.exists()) {
            return;
        }
        
        ArrayList<String[]> scores = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4) {
                    scores.add(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Sort by score (index 2) in descending order
        scores.sort((a, b) -> {
            double scoreA = Double.parseDouble(a[2]);
            double scoreB = Double.parseDouble(b[2]);
            return Double.compare(scoreB, scoreA);
        });
        
        // Add sorted data to table
        for (String[] data : scores) {
            tableModel.addRow(new Object[]{
                data[0],  // username
                data[1],  // score percentage
                data[2],  // raw points
                data[3]   // timestamp
            });
        }
    }
}