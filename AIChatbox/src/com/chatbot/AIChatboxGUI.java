package com.chatbot;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class AIChatboxGUI extends JFrame {
	JTextArea chatArea;
	JTextField inputField;
	JButton sendButton;
	
	Chatbot chatbot;
	
	public AIChatboxGUI() {
		chatbot = new Chatbot();
		
		setTitle("Artificial Intelligence Chatbox");
		setSize(600,500);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		  setLocationRelativeTo(null);
		
		  setLayout(new BorderLayout(10,10));
		
		  //Heading
		  JLabel heading = new JLabel(
			      "AI CHATBOX",
			      JLabel.CENTER
				
		  );
		
		  heading.setFont(
				new Font("Arial",Font.BOLD,24)
		);
		
		add(heading,BorderLayout.NORTH);
		
		//Chat Area
		chatArea =new JTextArea();
		
		chatArea.setEditable(false);
		
		chatArea.setFont(
				new Font("Arial",Font.PLAIN, 16)
				
		);
		
		JScrollPane scrollPane = new JScrollPane(chatArea);
		
		add(scrollPane ,BorderLayout.CENTER);
		
		//Input Field
		inputField = new JTextField();
		
		sendButton = new JButton("Send");
		
		JPanel panel = new JPanel(new BorderLayout(10,10));
		
		panel.add(inputField,BorderLayout.CENTER);
		
		panel.add(sendButton,BorderLayout.EAST);
		
		add(panel, BorderLayout.SOUTH);
		
		
		// Welcome Message
        chatArea.append(
                "Bot: Hello! Welcome to AI Chatbox.\n\n"
        );
        
        
       // Button Action
        sendButton.addActionListener(e -> sendMessage());

        // Enter Key Action
        inputField.addActionListener(e -> sendMessage());

        setVisible(true);
    }

    // Send Message
    private void sendMessage() {

        String message = inputField.getText().trim();

        if (message.isEmpty()) {
            return;
        }

        chatArea.append("You: " + message + "\n");

		String response = chatbot.getResponse(message);

        chatArea.append("Bot: " + response + "\n\n");

        inputField.setText("");

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> new AIChatboxGUI()
        );
        
    }		
			
}

