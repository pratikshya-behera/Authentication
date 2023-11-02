package views;

import dao.UserDAO;
import model.User;
import service.GenerateOTP;
import service.EmailSender;
import service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Welcome {
    public void welcomeScreen() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the app");
        System.out.println("Press 1 to login");
        System.out.println("Press 2 to signup");
        System.out.println("Press 0 to exit");
        int choice = 0;
        try {
            choice = Integer.parseInt(br.readLine());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        switch (choice) {
            case 1 : login();
            break;
            case 2 : signUp();
            break;
            case 0 : System.exit(0);
        }
    }

    private void login() {
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        System.out.println("Enter email");
        String email = sc.nextLine();
        try {
            if(UserDAO.isExists(email)) {
                String genOTP = GenerateOTP.getOTP();
                EmailSender.sendOTP(email, genOTP);
                System.out.println("Enter the otp");
                String otp = sc.nextLine();
                if(otp.equals(genOTP)) {
                   new UserView(email).home();

                } else {
                    System.out.println("Wrong OTP");
                }
            } else {
                System.out.println("User not found");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    private void signUp() {
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        System.out.println("Enter name");
        String name = sc.nextLine();
        System.out.println("Enter email");
        String email = sc.nextLine();
        String genOTP = GenerateOTP.getOTP();
        EmailSender.sendOTP(email, genOTP);
        System.out.println("Enter the otp");
        String otp = sc.nextLine();
        if(otp.equals(genOTP)) {
            User user = new User(name, email);
            int response = UserService.saveUser(user);
            switch (response) {
                case 0 : System.out.println("User registered");
                break;
                case 1 : System.out.println("User already exists");
                break;
            }
        } else {
            System.out.println("Wrong OTP");
        }

    }
}

//package views;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.sql.SQLException;
//import java.util.Scanner;
//
//import dao.UserDAO;
//import model.User;
//import service.EmailSender;
//import service.GenerateOTP;
//import service.UserService;
//
//public class Welcome {
//	
//	public void welcomeScreen() throws Exception {
//		int choice=0;
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		
//		System.out.println("Welcome!");
//		System.out.println("1: Login");
//		System.out.println("2: SignUp");
//		System.out.println("0: Exit");
//		System.out.println("Enter choice: ");
//		
//		try {
//			choice = Integer.parseInt(br.readLine());
//		}
//		catch(IOException e) {
//			e.printStackTrace();
//		}
//		
//		switch(choice) {
//		case 1:
//			login();
//			break;
//		case 2:
//			try {
//				signUp();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			break;
//		case 0:
//			System.exit(0);
//		}
//		
//	}
//
//	private void signUp() throws Exception{
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter name: ");
//		String name = sc.nextLine();
//		System.out.println("Enter email: ");
//		String email = sc.nextLine();
//		String genOTP = GenerateOTP.getOTP();
//		EmailSender.sendOTP(email, genOTP);
//		System.out.println("Enter otp: ");
//		String otp = sc.nextLine();
//		if(otp.equals(genOTP)) {
//			//System.out.println("Welcome!");
//			User user = new User(name, email);
//			int response = UserService.saveUser(user);
//			switch(response) {
//			case 0 :
//				System.out.println("User registered.");
//				break;
//			case 1 :
//				System.out.println("User already exists.");
//			}
//		}
//		else {
//			System.out.println("Wrong OTP");
//		}
//		sc.close();
//	}
//
//	private void login() {
//		System.out.println("Enter email: ");
//		Scanner sc = new Scanner(System.in);
//		String email = sc.nextLine();
//		try {
//			if(UserDAO.isExists(email)) {
//				String genOTP = GenerateOTP.getOTP();
//				EmailSender.sendOTP(email, genOTP);
//				System.out.println("Enter OTP: ");
//				String otp = sc.nextLine();
//				if(otp.equals(genOTP)) {
//					//System.out.println("Welcome!");
//					new UserView(email).home();
//				}
//				else {
//					System.out.println("Wrong OTP");
//				}
//			}
//			else {
//				System.out.println("User not found!");
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//		sc.close();
//	}
//
//}
