/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author balaji
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

//This class reads n number of bytes from a text file at a time
public class nBytes {
	int blockSize;
	File fout;
	long numberofBlocks;
	byte[] array;
	InputStream in;
	BufferedWriter bw;
	
	//Read a file n bytes at a time (to handle large files)
	public byte[] readFile(int blockSize, InputStream in) throws IOException{
		this.blockSize = blockSize; //specify the size of the file block (in bytes)
		this.in = in;
		array = new byte[blockSize];
		this.in.read(array, 0, blockSize); //read 8 bytes into the array
		return array;
	}
	
	
	public void writeFile(String line, BufferedWriter bw) throws IOException{
		this.bw = bw;
		this.bw.write(line);
	}
        public byte[] bread(String name)
        {
         

		   // String example ="6c23e2dd0eef3afc4eadfbff4446e5c8";
		    byte[] bytes=name.trim().getBytes();

		    System.out.println("Text : "+name);
		    System.out.println("Text [Byte Format] : " + bytes);
              return bytes;

	}
}
        
	

