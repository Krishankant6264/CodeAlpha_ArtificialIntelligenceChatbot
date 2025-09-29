import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Chatbot {
    private Map<String, String> knowledgeBase;

    public Chatbot() {
        knowledgeBase = new HashMap<>();

        // FAQ training (rule-based knowledge)
        knowledgeBase.put("hi", "Hello! How can I help you?");
        knowledgeBase.put("hello", "Hi there! Ask me anything.");
        knowledgeBase.put("how are you", "I’m doing great! How about you?");
        knowledgeBase.put("what is ai", "AI stands for Artificial Intelligence. It makes machines smart.");
        knowledgeBase.put("what is your name", "I’m your Java Chatbot 🤖.");
        knowledgeBase.put("bye", "Goodbye! Have a wonderful day.");
    }

    // Simple NLP Preprocessing + Rule-based matching
    public String getResponse(String input) {
        input = input.toLowerCase().trim();
        input = input.replaceAll("[^a-zA-Z ]", ""); // remove punctuation

        for (String key : knowledgeBase.keySet()) {
            if (input.contains(key)) {
                return knowledgeBase.get(key);
            }
        }

        return "Sorry, I don’t understand that. Can you rephrase?";
    }
}

public class ChatbotGUI extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private Chatbot bot;

    public ChatbotGUI() {
        bot = new Chatbot();

        setTitle("AI Chatbot");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Chat Display
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        // Input area
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        sendButton = new JButton("Send");

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        // Event Handling
        ActionListener sendAction = e -> {
            String userInput = inputField.getText();
            if (!userInput.isEmpty()) {
                chatArea.append("You: " + userInput + "\n");
                String response = bot.getResponse(userInput);
                chatArea.append("Bot: " + response + "\n\n");
                inputField.setText("");
            }
        };

        sendButton.addActionListener(sendAction);
        inputField.addActionListener(sendAction);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatbotGUI::new);
    }
}
