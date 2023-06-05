
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
    final String FILENAME="data.csv";
    final int MAXLINES=100;
    final int VALUESPERLINE=3;
    File thefile = new File(FILENAME);
    String CSVlines[] = new String[MAXLINES];
    String AllLinesAllElements[][]=new String[MAXLINES][VALUESPERLINE];

    /**
     * Constructor for objects of class filereader
     */
    public filereader()
    {
        // initialise instance variables
        int lineCount=0;

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
                    System.out.print(values[j]+"****");
                }
                System.out.println("");
            } 
        }catch (IOException e) {System.out.println(e);}
    }

    public String getData(int maxlines, int valuesperline){
        return AllLinesAllElements[maxlines][valuesperline];
    }
}