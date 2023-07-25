
/**
 * cara templetpon
 * 
 * 6/06
 * 
 * this calss reads a afile
 */

import java.util.Scanner;
import java.io.IOException;
import java.io.File;
public class filereader
{
    // instance variables - replace the example below with your own
    String FILENAME;
    final int MAXLINES=100;
    final int VALUESPERLINE=3;
    File thefile;
    String CSVlines[] = new String[MAXLINES];
    String AllLinesAllElements[][]=new String[MAXLINES][VALUESPERLINE];
    int lineCount=0;
    boolean errorOccured=false;

    /**
     * Constructor for objects of class filereader
     */
    public filereader()
    {
        // initialise instance variables

    }
    public boolean hasErrorOccurred(){
        return errorOccured;
    }

    public int getLines(){
        return (lineCount);
    }

    public String getData(int column, int row){
        return AllLinesAllElements[row][column];
    }

    public void setFile(String name){

        thefile = new File(name);
        readFile();
    }

    public void readFile(){
        errorOccured=false;
        lineCount=0;
        

        try {
            Scanner reader = new Scanner(thefile);

            while(reader.hasNextLine() && lineCount < MAXLINES){
                String line=reader.nextLine();
                CSVlines[lineCount]=line;
                lineCount++;
            }

            for (int i=0;i<lineCount;i++){
                String values[] = CSVlines[i].split(",");

                for(int j=0;j<values.length;j++){
                    AllLinesAllElements[i][j]=values[j];
                }
            } 
        }catch (IOException e) {
            System.out.println(e);
            errorOccured=true;
        }
    }
}