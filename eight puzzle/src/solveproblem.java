import java.io.File;  
import java.util.ArrayList;
import java.io.BufferedReader; 
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class solveproblem 
{
	 public static void main(String [] args) 
	 {
            //Start read file
	        String fileName = "input.txt";
	        String line = null;
	        ArrayList<String> temp= new ArrayList<String>();

	        try {
	            
	            FileReader fileReader = 
	                new FileReader(fileName);

	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = 
	                new BufferedReader(fileReader);

	            while((line = bufferedReader.readLine()) != null) {
	                temp.add(line);
	            }   
	            // Always close files.
	            bufferedReader.close();         
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println(
	                "Unable to open file '" + 
	                fileName + "'");                
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Error reading file '" 
	                + fileName + "'");                  
	        }
	 ArrayList<int[]> board = new ArrayList<int[]>();
	 for(int i=0; i<temp.size();i++)
	 {
		String testcase=temp.get(i);
		//testcase=testcase.trim();
		int[] Snode=new int[9];
		Snode= input(testcase);
		
		//System.out.println(Snode);
		board.add(Snode);
	 }
	
	 
	 ArrayList<String> printfile1 = new ArrayList<String>();
	 ArrayList<String> printfile2 = new ArrayList<String>();
	 ArrayList<String> printfile3 = new ArrayList<String>();
	 
	 //Add all the String to printfile arraylist, each one is one file.
	 for(int i=0; i<board.size();i++)
		 
	 {  if(i==0)
	   {
		 printfile1.add("Start of Set"+Integer.toString((i/5)+1));
		 printfile2.add("Start of Set"+Integer.toString((i/5)+1));
		 printfile3.add("Start of Set"+Integer.toString((i/5)+1));
		 }
		 else if (i==5)
		 {
			 printfile1.add("End of Set"+Integer.toString((i/5))+"\n");
			 printfile1.add("Start of Set"+Integer.toString((i/5)+1));
			 printfile2.add("End of Set"+Integer.toString((i/5))+"\n");
			 printfile2.add("Start of Set"+Integer.toString((i/5)+1));
			 printfile3.add("End of Set"+Integer.toString((i/5))+"\n");
			 printfile3.add("Start of Set"+Integer.toString((i/5)+1));
		 }
		 else if (i==10)
		 {
			 printfile1.add("End of Set"+Integer.toString((i/5))+"\n");
			 printfile1.add("Start of Set"+Integer.toString((i/5)+1));
			 printfile2.add("End of Set"+Integer.toString((i/5))+"\n");
			 printfile2.add("Start of Set"+Integer.toString((i/5)+1));
			 printfile3.add("End of Set"+Integer.toString((i/5))+"\n");
			 printfile3.add("Start of Set"+Integer.toString((i/5)+1));
		 }
	 
		printfile1.add("	Problem"+Integer.toString((i%5)+1)+" "+"Set"+Integer.toString((i/5)+1));
	    printfile2.add("	Problem"+Integer.toString((i%5)+1)+" "+"Set"+Integer.toString((i/5)+1));
	    printfile3.add("	Problem"+Integer.toString((i%5)+1)+" "+"Set"+Integer.toString((i/5)+1));
		ArrayList<String> temprint= astarsearch.search(board.get(i), 'p');
		ArrayList<String> temprint2= astarsearch.search(board.get(i), 'm');
		ArrayList<String> temprint3= astarsearch.search(board.get(i), 'c');
	    
		for(int k= 0; k<temprint.size();k++)
		{
			printfile1.add(temprint.get(k));
			
		}
		for(int k= 0; k<temprint2.size();k++)
		{
			printfile2.add(temprint2.get(k));
			
		}
		for(int k= 0; k<temprint3.size();k++)
		{
			printfile3.add(temprint3.get(k));
			
		}
		
		if(i==14)
		{
			printfile1.add("End of Set"+Integer.toString((i/5)+1)+"\n");
			printfile2.add("End of Set"+Integer.toString((i/5)+1)+"\n");
			printfile3.add("End of Set"+Integer.toString((i/5)+1)+"\n");
		}
	 }
	 /*for(int i=0; i<printfile1.size();i++)
	 {
		 System.out.println(printfile1.get(i));
	 }*/
	 
	 // Start print file
	 try{
			File file = new File("OutfileHeuristic1.txt");
			FileWriter fileWriter = new FileWriter(file);
			File file2 = new File("OutfileHeuristic2.txt");
			FileWriter fileWriter2 = new FileWriter(file2);
			File file3 = new File("OutfileHeuristic3.txt");
			FileWriter fileWriter3 = new FileWriter(file3);
			
			for(int x=0;x<printfile1.size();x++){
				fileWriter.write(printfile1.get(x));
				fileWriter.write("\n");
			}
			fileWriter.flush();
			fileWriter.close();
			
			for(int x=0;x<printfile2.size();x++){
				fileWriter2.write(printfile2.get(x));
				fileWriter2.write("\n");
			}
			fileWriter2.flush();
			fileWriter2.close();
			
			for(int x=0;x<printfile3.size();x++){
				fileWriter3.write(printfile3.get(x));
				fileWriter3.write("\n");
			}
			fileWriter3.flush();
			fileWriter3.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
}   
	 
	private static int[] input(String in){
		int [] puzzle=new int[9];
	    String[] str= in.split(",");
		for(int i=0; i<str.length;i++){
			puzzle[i]=Integer.parseInt(str[i]);
		}

	    return puzzle;
	    
	}
}
