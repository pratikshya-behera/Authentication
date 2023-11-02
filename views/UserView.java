package views;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.DataDAO;
import model.Data;

public class UserView {
	
	private String email;
	
	UserView(String email){
		this.email = email;
	}
	
	public void home() {
		do {
			System.out.println("Welcome "+ this.email);
			
			System.out.println("Press 1 to show hidden files");
			System.out.println("Press 2 to hide a new file");
			System.out.println("Press 3 to unhide a file");
			System.out.println("Press 0 to exit");
			
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			int ch = Integer.parseInt(sc.nextLine());//Taking string input to avoid input glitch further
			
			switch(ch) {
			case 1:
				try {
					List<Data> files = DataDAO.getAllFiles(this.email);
					System.out.println("ID - FileName");
					for(Data file : files) {
						System.out.println(file.getId()+ " - " +file.getFilename());
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("Enter file path: ");
				String path = sc.nextLine();
				
				try {
				File f = new File(path);
				Data file = new Data(0, f.getName(), path, this.email);
				
					DataDAO.hideFile(file);
				}catch(Exception e) {
					e.printStackTrace();
				}
				break;
			case 3:
				try {
				List<Data> files = DataDAO.getAllFiles(this.email);
				System.out.println("ID - FileName");
				for(Data file1 : files) {
					System.out.println(file1.getId()+ " - " +file1.getFilename());
				System.out.println("Enter the id of file to unhide: ");
				int id = Integer.parseInt(sc.nextLine());
				
				boolean isValidID = false;
				for(Data file2: files) {
					if(file2.getId() == id) {
						isValidID = true;
						break;
					}
				}
				if(isValidID)
					DataDAO.unhide(id);
				else
					System.out.println("Invalid id !");
				}
				}catch(IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 0:
				System.exit(0);
			default:
				System.exit(0);
			}
			
		}while(true);
	}

}
