package com.chatbot;

import java.util.HashMap;

public class Chatbot {
	
	private HashMap<String, String> faq;
	
	public Chatbot() {
		
		faq = new HashMap<>();
		
		faq.put("what is java",
				"Java is high-level,Object-oriented programming language.");
		faq.put("what is ai",
				"AI Stands for Artificial Intelligence.");
		faq.put("what is your name",
				"I am Java AI Chatbox.");
		faq.put("what is nlp",
				"NLP means Natural Large Processing.");
		faq.put("what is spring boot",
				"spring boot is a Java framework.");
		faq.put("what is python",
				"Python is a Scripting language.");
		faq.put("who created java",
				"Java was created by James Gosling.");
	}
	 public String getResponse(String message) {

	    message = message.toLowerCase().trim();

	    message = message.replaceAll("[^a-z0-9\\s]", "");

	        if (message.isEmpty()) {
	            return "Please enter a message.";
	        }
	        
	        if (message.matches(".*\\b(hi|hello|hey)\\b.*")) {
	            return "Hello! Welcome to Java AI Chatbot.";
	        }
	        
	        if (message.contains("good morning")) {
	            return "Good morning! How can I help you?";
	        }
	        
	        if (message.contains("how are you")) {
	            return "I am fine. Thank you!";
	        }
	        
	        if (message.matches(".*\\b(bye|goodbye)\\b.*")) {
	            return "Goodbye! Have a nice day.";
	        }
	        
	        for (String question : faq.keySet()) {

	            if (message.contains(question)) {
	                return faq.get(question);
	            }
	        }

	        if (message.contains("thank you")) {
	            return "You're welcome!";
	        }
	        
	        //Default Response
	        return "Sorry, I don't understand.Please ask another question.";
	        }
	    }


