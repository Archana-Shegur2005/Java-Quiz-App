package quiz.applicationmp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Quiz extends JFrame implements ActionListener {
    
    String questions[][] = new String[10][5];
    String answers[][] = new String[10][2];
    String useranswers[][] = new String[10][1];
    JLabel qno, question;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup groupoptions;
    JButton next, submit;
   
    public static int timer = 30;
    public static int ans_given = 0;
    public static int count = 0;
    public static int score = 0;
    
    String name;
    
    Quiz(String name) {
        this.name = name;
        setBounds(10, 0, 1140, 650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
         

        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/Capture.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 850, 150);
        add(image);
        
        qno = new JLabel();
        qno.setBounds(20, 160, 30, 30);
        qno.setFont(new Font("Calibri", Font.PLAIN, 24));
        add(qno);
        
        question = new JLabel();
        question.setBounds(45, 160, 900, 30);
        question.setFont(new Font("Calibri", Font.PLAIN, 24));
        add(question);
        
        questions[0][0] = "Give the Abbreviation of AWT ?";
        questions[0][1] = "Applet windowing Toolkit";
        questions[0][2] = "Abstract windowing toolkit";
        questions[0][3] = "Absolute windowing toolkit";
        questions[0][4] = "None of these";

        questions[1][0] = "Which of the following class is derived from the container class?";
        questions[1][1] = "Component";
        questions[1][2] = "Panel";
        questions[1][3] = "Menu component";
        questions[1][4] = "List";

        questions[2][0] = "Port number 80 is reserved for protocol?";
        questions[2][1] = "FTP";
        questions[2][2] = "HTTP";
        questions[2][3] = "SMTP";
        questions[2][4] = "Telnet";

        questions[3][0] = "By which method we can set or change the text in label in AWT?";
        questions[3][1] = "setText()";
        questions[3][2] = "getText()";
        questions[3][3] = "addText()";
        questions[3][4] = "all of these";

        questions[4][0] = "How many ways can we align the label in a container?";
        questions[4][1] = "1";
        questions[4][2] = "2";
        questions[4][3] = "3";
        questions[4][4] = "4";

        questions[5][0] = "Default layout manager for subclasses of window is?";
        questions[5][1] = "Card layout";
        questions[5][2] = "Gridbag layout";
        questions[5][3] = "Frame";
        questions[5][4] = "Border layout";

        questions[6][0] = "Swing is based on architecture";
        questions[6][1] = "Client server";
        questions[6][2] = "Model view controller";
        questions[6][3] = "layered";
        questions[6][4] = "None of these";

        questions[7][0] = "Jpanel and Applet use as their default layout?";
        questions[7][1] = "Flowlayout";
        questions[7][2] = "Gridlayout";
        questions[7][3] = "Border layout";
        questions[7][4] = "Gridbag layout";

        questions[8][0] = "___represents enterprise data & business rules that gives access to";
        questions[8][1] = "Model";
        questions[8][2] = "View";
        questions[8][3] = "Controller";
        questions[8][4] = "None of these";

        questions[9][0] = "Which class used for accessing attributes of remote resource?";
        questions[9][1] = "URLConnection";
        questions[9][2] = "URL";
        questions[9][3] = "URI";
        questions[9][4] = "None of these";
        
        answers[0][1] = "Abstract windowing toolkit";
        answers[1][1] = "Panel";
        answers[2][1] = "SMTP";
        answers[3][1] = "setText()";
        answers[4][1] = "3";
        answers[5][1] = "Border layout";
        answers[6][1] = "Model view controller";
        answers[7][1] = "Flowlayout";
        answers[8][1] = "Model";
        answers[9][1] = "URLConnection";
        
        opt1 = new JRadioButton();
        opt1.setBounds(70, 240, 400, 20);
        opt1.setBackground(Color.WHITE);
        opt1.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt1);
        
        opt2 = new JRadioButton();
        opt2.setBounds(70, 280, 400, 20);
        opt2.setBackground(Color.WHITE);
        opt2.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt2);
        
        opt3 = new JRadioButton();
        opt3.setBounds(70, 320, 400, 20);
        opt3.setBackground(Color.WHITE);
        opt3.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt3);
        
        opt4 = new JRadioButton();
        opt4.setBounds(70, 360, 400, 20);
        opt4.setBackground(Color.WHITE);
        opt4.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt4);
        
        groupoptions = new ButtonGroup();
        groupoptions.add(opt1);
        groupoptions.add(opt2);
        groupoptions.add(opt3);
        groupoptions.add(opt4);
        
        next = new JButton("Next");
        next.setBounds(600, 200, 200, 50);
        next.setFont(new Font("Calibri", Font.PLAIN, 22));
        next.setBackground(new Color(0, 153, 0));
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);
    
        
        submit = new JButton("Submit");
        submit.setBounds(600, 270, 200, 50);
        submit.setFont(new Font("Calibri", Font.PLAIN, 22));
        submit.setBackground(new Color(0, 153, 0));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setEnabled(false);
        add(submit);
        
        start(count);
        setVisible(true);
        setSize(870, 450);
        setLocation(200, 30);
      
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            repaint();
            opt1.setEnabled(true);
            opt2.setEnabled(true);
            opt3.setEnabled(true);
            opt4.setEnabled(true);
            
            ans_given = 1;
            if (groupoptions.getSelection() == null) {
               useranswers[count][0] = "";
            } else {
                useranswers[count][0] = groupoptions.getSelection().getActionCommand();
            }
            
            if (count == 8) {
                next.setEnabled(false);
                submit.setEnabled(true);
            }
            
            count++;
            start(count);
        } 
  
        else if (ae.getSource() == submit) {
            ans_given = 1;
            if (groupoptions.getSelection() == null) {
                useranswers[count][0] = "";
            } else {
                useranswers[count][0] = groupoptions.getSelection().getActionCommand();
            }

            for (int i = 0; i < useranswers.length; i++) {
                if (useranswers[i][0].equals(answers[i][1])) {
                    score += 10;
                } else {
                    score += 0;
                }
            }
            count=0;
               setVisible(false);
           new Dashboard().setVisible(true);
            //setVisible(false);
            //new Score(name, score);
             if (ae.getSource() == submit) {
         Connection con=null;
        PreparedStatement pst=null;
      
        String username=name;
        try{
           // Class.forName("com.mysql.cj.jdbc.Driver" );
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/logindatabase","root","NPA#29kgrw9");
            String sql="update login set score=? where username=?;";
            pst=con.prepareStatement(sql);
            pst.setInt(1,score );
            pst.setString(2,username);

            pst.executeUpdate();
           
                JOptionPane.showMessageDialog(null,"You have give Quiz Exam Sucessfully !!!");
            
        }
        catch(SQLException e)
        {
             e.printStackTrace();
        } 
        }  
        }
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        
        String time = "Time left - " + timer + " seconds"; // 30
        g.setColor(Color.RED);
        g.setFont(new Font("Calibri", Font.BOLD, 15));
        
        if (timer > 0) { 
            g.drawString(time, 650, 200);
        } else {
            g.drawString("Times up!!", 775, 385);
        }
        
        timer--; // 29
        
        try {
            Thread.sleep(1000);
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (ans_given == 1) {
            ans_given = 0;
            timer = 30;
        } else if (timer < 0) {
            timer = 30;
            opt1.setEnabled(true);
            opt2.setEnabled(true);
            opt3.setEnabled(true);
            opt4.setEnabled(true);
            
            if (count == 8) {
                next.setEnabled(false);
                submit.setEnabled(true);
            }
            if (count == 9) { // submit button
                if (groupoptions.getSelection() == null) {
                   useranswers[count][0] = "";
                } else {
                    useranswers[count][0] = groupoptions.getSelection().getActionCommand();  //value
                }
                
                for (int i = 0; i < useranswers.length; i++) {
                    if (useranswers[i][0].equals(answers[i][1])) {
                        score += 10;
                    } else {
                        score += 0;
                    }
                }
                
                setVisible(false);
              
            } else { // next button
                if (groupoptions.getSelection() == null) {
                   useranswers[count][0] = "";
                } else {
                    useranswers[count][0] = groupoptions.getSelection().getActionCommand();
                }
                count++; // 0 // 1
                start(count);
            }
        }
        
    }
    
    public void start(int count) {
        qno.setText("" + (count + 1) + ". ");
        question.setText(questions[count][0]);
        opt1.setText(questions[count][1]);
        opt1.setActionCommand(questions[count][1]);
        
        opt2.setText(questions[count][2]);
        opt2.setActionCommand(questions[count][2]);
        
        opt3.setText(questions[count][3]);
        opt3.setActionCommand(questions[count][3]);
        
        opt4.setText(questions[count][4]);
        opt4.setActionCommand(questions[count][4]);
        
        groupoptions.clearSelection();
    }
    
    public static void main(String[] args) {
        
        new Quiz("User");
    }
}
