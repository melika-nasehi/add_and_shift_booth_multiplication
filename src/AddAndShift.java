import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AddAndShift {
    Int2Bin int2Bin = new Int2Bin();
    Array2String array2String = new Array2String();
    Functions functions = new Functions();
    TwosComplement twosComplement = new TwosComplement();


    //Add_and_Shift function generates output in out.txt
    public void Add_and_Shift(int b, int s , int A , int B , int outNum , int m){
        int[] binaryA ;
        int[] binaryB ;
        int[] binaryM ;
        int[] binaryUpperM ;

        //functions.initialize(A , B , binaryA , binaryB , binaryM , binaryUpperM);
        binaryA = int2Bin.integerToBinary(A , A < 0); //if A<0 is true then the boolean "negative" passes true
        binaryB = int2Bin.integerToBinary(B , B < 0);
        binaryM = int2Bin.integerToBinary(0 , false);
        binaryUpperM = int2Bin.integerToBinary(0 , false);

        try {
            FileWriter myWriter = new FileWriter("out.txt", true) ;
            BufferedWriter bf = new BufferedWriter(myWriter);

            //intro of the out text
            bf.write("------------------------------------------");
            bf.newLine();
            bf.write("out-" + outNum);
            bf.newLine();
            if(s==0)
                bf.write("un");
            bf.write("signed add & shift multiplication");
            bf.newLine();
            bf.write("A=" + A + "=" + array2String.array2string(b-1 , 0 , binaryA)
                    + ",  B=" + B + "=" + array2String.array2string(b-1 , 0, binaryB));
            bf.newLine();
            bf.write("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            bf.newLine();
            bf.write("(0, )  M=" + array2String.array2string(b-1 ,0, binaryM) + " | " +
                    array2String.array2string(b-1 ,0, binaryB));
            bf.newLine();
            bf.write("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            bf.newLine();


            // add & shift OR no operation & shift?
            //int bitB = 4 ;
            for (int step = 1; step <= b; step++){

                if (binaryB[step - 1] == 0) {  //no operation & shift

                    //nop
                    bf.write("("+step+", )  M=" + array2String.array2string(step+ b -2 , 0 ,binaryM)
                            + " | " + array2String.array2string(b-1 ,step-1 , binaryB));
                    bf.newLine();

                    //shift();
                    binaryM = functions.shift(binaryM , step , b ,s, functions.isCarry , m);
                    bf.write("("+step+",>)  M=" + array2String.array2string(step+ b -1 , 0 ,binaryM)
                            + " | " + array2String.array2string(b-1 ,step , binaryB));
                    bf.newLine();
                    bf.write("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    bf.newLine();
                }

                if (binaryB[step - 1] == 1) {  //add & shift

                    //ADD
                    char operation = '+';
                    if (step==b && s==1 &&(A<0 || B<0)) //for signed , we add -A in last step
                    {
                        binaryA = twosComplement.getTwosComplement(binaryA);
                        operation = '-';
                    }

                    functions.add(binaryUpperM , binaryA , binaryM , b , step , m);
                    //printing in out.txt file
                    bf.write("("+step+","+operation+")  M=" + array2String.array2string(step+ b -2 , 0 ,binaryM)
                            + " | " + array2String.array2string(b-1 ,step-1 , binaryB));
                    bf.newLine();

                    //SHIFT
                    binaryM = functions.shift(binaryM , step , b ,s, functions.isCarry ,m);
                    bf.write("("+step+",>)  M=" + array2String.array2string(step+ b -1  , 0 ,binaryM)
                            + " | " + array2String.array2string(b-1 ,step , binaryB));
                    bf.newLine();
                    bf.write("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    bf.newLine();


                    functions.isCarry = false ;
                }
            }
            //ending
            bf.write("M=AxB="+ A*B);
            bf.newLine();
            bf.write("------------------------------------------");
            bf.newLine();

            bf.close();

        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}
