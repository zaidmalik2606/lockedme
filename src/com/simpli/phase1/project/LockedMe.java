package com.simpli.phase1.project;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class LockedMe {
	public  String dirName;
	File folderName;
	
	public LockedMe() {
		dirName = System.getProperty("user.dir");
		folderName = new File(dirName+"/thisFolder");//makes new folder named "thisfolder" where all files operations would be performed
		if(!folderName.exists()) {
			folderName.mkdir();
		}
		System.out.println("DIRECTORY : "+ folderName.getAbsolutePath());
	}
	
	public  void AlphaMenu() {
        System.out.println("\nMAIN MENU - Select any of the following operations: \n"+
                "1 -> List of files in this directory\n"+
                "2 -> Add, Delete or Search\n"+
                "3 -> Exit Program");
        try{
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();
            switch (option){
                case 1 : {
                	//TO get the list of all the files in the directory
                    listFiles();
                    AlphaMenu();
                }
                case 2 : {
                	//to enter the second menu
                    BetaMenu();
                }
                case 3 : {
                	//To exit the program
                    System.out.println("Thank You");
                    System.exit(0);
                }
                default: AlphaMenu();
            }
        }
        catch (Exception e){
            System.out.println("Please only enter 1, 2 or 3");
            AlphaMenu();
        }
    }
	
	
	
	public  void BetaMenu() {
        System.out.println("   \nSelect any of the following: \n"+
                "   a -> Add a file\n"+
                "   b -> Delete a file\n"+
                "   c -> Search a file\n"+
                "   d -> GoBack");
        try{
            Scanner scanner = new Scanner(System.in);
            char[] input = scanner.nextLine().toLowerCase().trim().toCharArray();
            char choice = input[0];

            switch (choice){
                case 'a' : {
                    System.out.print("Please Enter a File Name to add: ");
                    String fileName = scanner.next().trim().toLowerCase();
                    addFile(fileName);
                    break;
                }
                case 'b' : {
                    System.out.print("Please Enter a File Name to delete: ");
                    String fileName = scanner.next().trim();
                    deleteFile(fileName);
                    break;
                }
                case 'c' : {
                    System.out.print("Please Enter a File Name to search: ");
                    String fileName = scanner.next().trim();
                    searchFile(fileName);
                    break;
                }
                case 'd' : {
                    System.out.println("Back to MAIN menu");
                    AlphaMenu();
                    break;
                }
                default : System.out.println("Please enter a, b, c or d");
            }
            BetaMenu();
        }
        catch (Exception e){
            System.out.println("Please enter a, b, c or d");
            BetaMenu();
        }
    }
	
	public  void listFiles() {
        if (folderName.list().length==0)
            System.out.println("This folder is empty");
        else {
            String[] fileList = folderName.list();
            System.out.println("The files in "+ folderName +" are :");
            Arrays.sort(fileList);//uses quick sort
            for (String files:fileList) {
                System.out.println(files);
            }
        }
    }
	
	void addFile(String fileName) throws IOException {
        File filePath = new File(folderName +"/"+fileName);
        String[] fileList = folderName.list();
        for (String file: fileList) {
            if (fileName.equalsIgnoreCase(file)) {
                System.out.println("The file with name " + fileName + " already exists at " + folderName);
                return;
            }
        }
        filePath.createNewFile();
        System.out.println("File "+fileName+" added to folder "+ folderName);
    }
	
	void deleteFile(String fileName) {
        File filePath = new File(folderName +"/"+fileName);
        String[] fileList = folderName.list();
        for (String file: fileList) {
            if (fileName.equals(file) && filePath.delete()) {
                System.out.println("File " + fileName + " deleted from " + folderName);
                return;
            }
        }
        System.out.println("Deletion failed. No FILE FOUND WITH NAME "+fileName.toUpperCase()+" TO DELETE.");
    }
	
	void searchFile(String fileName) {
        String[] fileList = folderName.list();
        for (String file: fileList) {
            if (fileName.equals(file)) {
                System.out.println("FOUND : File " + fileName + " exists at " + folderName);
                return;
            }
        }
        System.out.println("File Not found.");
    }
	
	
	
	

}
