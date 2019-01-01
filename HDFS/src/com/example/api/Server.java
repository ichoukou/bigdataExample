package com.example.api;

public class Server {
	
	public static void main(String[] args) throws Exception {

		ApiFunction funciton = new ApiFunction();
		//funciton.initHDFS();
		
		//funciton.putFileToHDFS();
		
		funciton.readListFiles();
		funciton.findAtHDFS();
		
		/////////////////////////////
		System.out.println("over"); 
		
	}
	


	
}
