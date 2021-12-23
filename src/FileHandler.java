import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class FileHandler {
	String filename;
	File myFile;
	Scanner reader;
	int size;
	FileHandler(String filename){
		this.filename = filename;
		this.myFile = new File(filename);
		
		if(this.myFile.exists() && !this.myFile.isDirectory()) {
			
		}else {
			try {
				this.myFile.createNewFile();
				
				FileWriter writer = new FileWriter(this.myFile);
				writer.write("0");
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			this.reader = new Scanner(this.myFile);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	
	void write(ArrayList<String> contents) {
		try {
			FileWriter writer = new FileWriter(this.myFile);
			for(int i=0; i < contents.size(); i++) {
				writer.write(contents.get(i));
				
				if(i == contents.size()-1) break;
				
				writer.write("\n");
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	ArrayList<String> read() {
		ArrayList<String> contents = new ArrayList<String>();
		try {
			int i=0;
			while(this.reader.hasNextLine()) {
				if(i==0) {
					size = Integer.parseInt(reader.nextLine());
				}else {
					String line = reader.nextLine();
					contents.add(line);
					
					
				}
				i++;
			}
			reader.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return contents;
	}
}
