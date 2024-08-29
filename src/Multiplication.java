import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Multiplication {

    int n,m,b,s,A,B ;
    int outNum = 0 ;
    File output = new File("out.txt");
    AddAndShift addAndShift = new AddAndShift();
    Boothe boothe = new Boothe();
    TwosComplement twosComplement = new TwosComplement();

    public void multiply(){   //reading user inputs from "in.txt" file

        try {
            BufferedReader Reader = new BufferedReader(new FileReader("in.txt"));
            String line = Reader.readLine() ;
            n = Integer.parseInt(line);

            while (line != null && n>0){
                for (int i = 1 ; i<=n ; i++){   //count of multiplication we should do

                    //assigning values :
                    //m
                    line = Reader.readLine();
                    m = Integer.parseInt(line);

                    //b
                    line = Reader.readLine();
                    b = Integer.parseInt(line);

                    //s
                    line = Reader.readLine();
                    s = Integer.parseInt(line);

                    //A
                    line = Reader.readLine();
                    A = Integer.parseInt(line);

                    //B
                    line = Reader.readLine();
                    B = Integer.parseInt(line);

                    //choosing between ADD & SHIFT and BOOTHE
                    if (m==0)
                        addAndShift.Add_and_Shift(b, s, A, B , outNum , m);
                    else if (m==1)
                        boothe.Boothe(b, 1, A, B, outNum , m);

                    outNum++;

                }
            }

        } catch (IOException | NumberFormatException ex) {
            System.out.print("");
        }
    }

}
