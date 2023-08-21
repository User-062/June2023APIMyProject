package com.test.bulkemail;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BulkEmail {

	    public static void main(String args[])
	    {
	     int n = 4600;
	        String filename = "Ruble.csv";

	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
	            for (int i = 1; i <= n; i++) {
	                // String s = "fun" + i;
	                String s = "seth.ruble+test"+i+"@wizehive.com";
	                writer.write(s);
	                writer.newLine();
	            }
	            System.out.println("Functions written to the file successfully.");
	        } catch (IOException e) {
	            System.err.println("Error writing to the file: " + e.getMessage());
	        }
	    }
	}


