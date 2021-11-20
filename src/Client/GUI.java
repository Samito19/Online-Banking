package Client;

import Server.DatabaseConnector;
import Server.PinTester;
import Server.UserSession;

import javax.swing.*;
import java.util.Objects;

public class GUI {

	JFrame loginFrame = new JFrame("Online Bank");
	JFrame dashboardFrame = new JFrame("Dashboard");
	JFrame createUserFrame = new JFrame("Create a new user");
	JFrame transferFrame = new JFrame("Transfer money");

	UserSession userSession;

	public GUI(){
		 loginWindow();
	}


	public void loginWindow() {
				
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(500,500);
        loginFrame.setResizable(false);  
        loginFrame.setLayout(null);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(114, 330, 272, 57);
		
		JButton createNewUserButton = new JButton("Create new user");
		createNewUserButton.setBounds(167, 419, 166, 22);
		
		JTextField idTextField = new JTextField("Enter your ID here...");
		idTextField.setBounds(114, 173, 272, 39);
		
		JTextField pinTextField = new JTextField("Enter your PIN here...");
		pinTextField.setBounds(114, 228, 272, 39);
		
		JLabel idLabel = new JLabel("ID :");
		idLabel.setBounds(82, 181, 29, 23);
		
		JLabel pinLabel = new JLabel("PIN :");
		pinLabel.setBounds(68, 229, 29, 23);
		
		JLabel imageLogin = new JLabel(new ImageIcon("icons8-online-banking-64.png"));
		imageLogin.setBounds(200, 45, 100, 100);
		
		loginFrame.add(loginButton);
		loginFrame.add(createNewUserButton);
		loginFrame.add(idTextField);
		loginFrame.add(pinTextField);
		loginFrame.add(idLabel);
		loginFrame.add(pinLabel);
		loginFrame.add(imageLogin);
		
		loginFrame.setVisible(true);
		
		loginButton.addActionListener(e -> {
			try {
				if (PinTester.isValid(Integer.parseInt(idTextField.getText()), Integer.parseInt(pinTextField.getText()))){
					userSession = new UserSession(Objects.requireNonNull(DatabaseConnector.returnCustomerInfo(idTextField.getText())));
					loginFrame.getContentPane().removeAll();
					loginFrame.dispose();
					dashboardWindow();
				}
				else {JOptionPane.showMessageDialog(loginFrame ,"ID or PIN is incorrect !", "Error", JOptionPane.ERROR_MESSAGE);}

			} catch (NumberFormatException n) {System.out.println("Please enter a correct ID and PIN !");}
		});
		
		createNewUserButton.addActionListener(e -> {
			loginFrame.getContentPane().removeAll();
			loginFrame.dispose();
			createUserWindow();
		});
	}

	public void dashboardWindow() {
		
		dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dashboardFrame.setSize(500,500);
		dashboardFrame.setResizable(false);  
		dashboardFrame.setLayout(null);
		dashboardFrame.setVisible(true);

		JLabel nameLabel = new JLabel("Hello, " + userSession.getName());
		nameLabel.setBounds(173, 62, 154, 22);

		JLabel accountNumberLabel = new JLabel("Account Number : " + userSession.getAccountNumber());
		accountNumberLabel.setBounds(14, 160, 159, 22);

		JLabel checkingBalanceLabel = new JLabel("Checking Balance : " + userSession.getCBalance());
		checkingBalanceLabel.setBounds(14, 211, 167, 22);

		JLabel savingsBalanceLabel = new JLabel("Savings Balance : " + userSession.getSBalance());
		savingsBalanceLabel.setBounds(14, 262, 160, 22);

		JButton logoutButton = new JButton("Log out");
		logoutButton.setBounds(20, 400, 80, 30);

		JButton transferButton = new JButton("Transfer money");
		transferButton.setBounds(295, 202, 132, 42);

		dashboardFrame.add(logoutButton);
		dashboardFrame.add(transferButton);
		dashboardFrame.add(nameLabel);
		dashboardFrame.add(accountNumberLabel);
		dashboardFrame.add(checkingBalanceLabel);
		dashboardFrame.add(savingsBalanceLabel);

		logoutButton.addActionListener(e -> {
			userSession = null;
			dashboardFrame.getContentPane().removeAll();
			dashboardFrame.dispose();
			loginWindow();
		});
		transferButton.addActionListener(e -> {
			transferWindow();

		});
	}

	public void createUserWindow() {
		
		createUserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createUserFrame.setSize(500,500);
		createUserFrame.setResizable(false);  
		createUserFrame.setLayout(null);
		
		JButton createUserButton = new JButton("Create a new user !");
		createUserButton.setBounds(114, 330, 272, 57);
		
		JButton goBackButton = new JButton("Back");
		goBackButton.setBounds(20, 400, 60, 30);

		JTextField newNameTextField = new JTextField("First and last name here...");
		newNameTextField.setBounds(138, 117, 224, 39);
		
		JTextField newIdTextField = new JTextField("Enter your ID here...");
		newIdTextField.setBounds(138, 189, 224, 39);
		
		JTextField newPinTextField = new JTextField("Enter your PIN here...");
		newPinTextField.setBounds(138, 261, 224, 39);

		JLabel newNameLabel = new JLabel("Name :");
		newNameLabel.setBounds(74, 121, 50, 15);
		
		JLabel newIdLabel = new JLabel("ID :");
		newIdLabel.setBounds(85, 193, 50, 15);
		
		JLabel newPinLabel = new JLabel("PIN :");
		newPinLabel.setBounds(81, 261, 50, 15);
		
		createUserFrame.add(createUserButton);
		createUserFrame.add(goBackButton);
		createUserFrame.add(newNameTextField);
		createUserFrame.add(newIdTextField);
		createUserFrame.add(newPinTextField);
		createUserFrame.add(newIdLabel);
		createUserFrame.add(newPinLabel);
		createUserFrame.add(newNameLabel);

		
		createUserFrame.setVisible(true);
		
		createUserButton.addActionListener(e -> {
			try {
				if (PinTester.createNewUser(Integer.parseInt(newIdTextField.getText()), Integer.parseInt(newPinTextField.getText()), newNameTextField.getText())){
					JOptionPane.showMessageDialog(createUserFrame, "New user created !", "User creation", JOptionPane.INFORMATION_MESSAGE);
				}
				else{JOptionPane.showMessageDialog(createUserFrame, "This ID already exists !", "Error", JOptionPane.ERROR_MESSAGE);}


			} catch (NumberFormatException n) {JOptionPane.showMessageDialog(createUserFrame, "Please enter a valid ID or PIN !", "Error", JOptionPane.ERROR_MESSAGE);}
		});
		
		goBackButton.addActionListener(e -> {
			createUserFrame.getContentPane().removeAll();
			createUserFrame.dispose();
			loginWindow();
		});
		
		
	}

	public void transferWindow(){

		transferFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		transferFrame.setSize(250,250);
		transferFrame.setResizable(false);
		transferFrame.setLayout(null);
		transferFrame.setVisible(true);

		JButton quitButton = new JButton("Quit");
		quitButton.setBounds(12, 200, 34, 13);

		JButton transferButton = new JButton("Transfer");
		transferButton.setBounds(74, 150, 101, 26);

		JLabel accountNumberLabel = new JLabel("Account No. :");
		accountNumberLabel.setBounds(12, 50, 90, 15);

		JLabel amountLabel = new JLabel("Amount : ");
		amountLabel.setBounds(12, 100, 106, 20);

		JTextField accountNumberTextField = new JTextField();
		accountNumberTextField.setBounds(100, 50, 106, 20);

		JTextField amountTextField = new JTextField();
		amountTextField.setBounds(80, 100, 106, 20);

		transferFrame.add(quitButton);
		transferFrame.add(transferButton);
		transferFrame.add(accountNumberLabel);
		transferFrame.add(amountLabel);
		transferFrame.add(accountNumberTextField);
		transferFrame.add(amountTextField);

		transferButton.addActionListener(e -> {
			if (Integer.parseInt(amountTextField.getText()) <= userSession.getCBalance()){
				DatabaseConnector.transferMoney(Integer.parseInt(accountNumberTextField.getText()), userSession.getAccountNumber(), Integer.parseInt(amountTextField.getText()));
				JOptionPane.showMessageDialog(createUserFrame, "Transfer complete !", "Transfer", JOptionPane.INFORMATION_MESSAGE);
				dashboardFrame.getContentPane().removeAll();
				dashboardFrame.dispose();
				dashboardWindow();
			}
			else {JOptionPane.showMessageDialog(loginFrame ,"Insufficient balance !", "Error", JOptionPane.ERROR_MESSAGE);}
		});

		quitButton.addActionListener(e -> {
			transferFrame.getContentPane().removeAll();
			transferFrame.dispose();
		});
	}
}

