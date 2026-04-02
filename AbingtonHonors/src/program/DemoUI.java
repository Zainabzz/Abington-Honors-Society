package program;

import javax.swing.*;
import java.awt.*;

/**
 * Simple demo UI for Honors Program backend
 */
public class DemoUI extends JFrame
{
    private JTextArea outputArea;

    public DemoUI()
    {
        setTitle("Honors Program Admin Demo");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Honors Program Admin Demo", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        outputArea = new JTextArea();
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(outputArea);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 10, 10));

        JButton initButton = new JButton("Initialize");
        JButton resetButton = new JButton("Reset Demo");
        JButton addStudentsButton = new JButton("Add Students");
        JButton updateStudentButton = new JButton("Update Student");
        JButton addCreditsButton = new JButton("Add Credits");
        JButton reviewCreditsButton = new JButton("Review Credits");
        JButton deleteButton = new JButton("Delete Records");
        JButton fullDemoButton = new JButton("Run Full Demo");

        buttonPanel.add(initButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(addStudentsButton);
        buttonPanel.add(updateStudentButton);
        buttonPanel.add(addCreditsButton);
        buttonPanel.add(reviewCreditsButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(fullDemoButton);

        initButton.addActionListener(e -> {
            try
            {
                Main.initDAOs();
                appendOutput("Initialized DAOs.");
            }
            catch (Exception ex)
            {
                appendOutput("ERROR during initialize: " + ex.toString());
                ex.printStackTrace();
            }
        });

        resetButton.addActionListener(e -> {
            try
            {
                Main.resetDemo();
                appendOutput("Reset demo data.");
                showTables();
            }
            catch (Exception ex)
            {
                appendOutput("ERROR during reset: " + ex.toString());
                ex.printStackTrace();
            }
        });

        addStudentsButton.addActionListener(e -> {
            try
            {
                Main.demoAddStudents();
                appendOutput("Added demo students.");
                showTables();
            }
            catch (Exception ex)
            {
                appendOutput("ERROR during add students: " + ex.toString());
                ex.printStackTrace();
            }
        });

        updateStudentButton.addActionListener(e -> {
            try
            {
                Main.demoUpdateStudent();
                appendOutput("Updated one student.");
                showTables();
            }
            catch (Exception ex)
            {
                appendOutput("ERROR during update student: " + ex.toString());
                ex.printStackTrace();
            }
        });

        addCreditsButton.addActionListener(e -> {
            try
            {
                Main.demoAddCredits();
                appendOutput("Added demo credits.");
                showTables();
            }
            catch (Exception ex)
            {
                appendOutput("ERROR during add credits: " + ex.toString());
                ex.printStackTrace();
            }
        });

        reviewCreditsButton.addActionListener(e -> {
            try
            {
                Main.demoReviewCredits();
                appendOutput("Reviewed credits.");
                showTables();
            }
            catch (Exception ex)
            {
                appendOutput("ERROR during review credits: " + ex.toString());
                ex.printStackTrace();
            }
        });

        deleteButton.addActionListener(e -> {
            try
            {
                Main.demoDelete();
                appendOutput("Deleted selected demo records.");
                showTables();
            }
            catch (Exception ex)
            {
                appendOutput("ERROR during delete records: " + ex.toString());
                ex.printStackTrace();
            }
        });

        fullDemoButton.addActionListener(e -> runFullDemo());

        setLayout(new BorderLayout(10, 10));
        add(title, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void appendOutput(String text)
    {
        outputArea.append(text + "\n\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    private void showTables()
    {
        outputArea.append("===== STUDENTS =====\n");
        outputArea.append(Main.studentsTableString() + "\n");
        outputArea.append("===== CREDITS EARNED =====\n");
        outputArea.append(Main.creditsTableString() + "\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    private void runFullDemo()
    {
        outputArea.setText("");

        try
        {
            Main.initDAOs();
            appendOutput("Initialized system.");

            Main.resetDemo();
            appendOutput("Reset old demo data.");

            Main.demoAddStudents();
            appendOutput("Added two students.");
            showTables();

            Main.demoUpdateStudent();
            appendOutput("Updated one student.");
            showTables();

            Main.demoAddCredits();
            appendOutput("Added three credit records.");
            showTables();

            Main.demoReviewCredits();
            appendOutput("Reviewed credits and updated student totals.");
            showTables();

            Main.demoDelete();
            appendOutput("Deleted selected records and displayed system state.");
            showTables();

            appendOutput("Demo complete.");
        }
        catch (Exception ex)
        {
            appendOutput("ERROR during full demo: " + ex.toString());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new DemoUI().setVisible(true));
    }
}