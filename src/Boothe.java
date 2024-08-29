import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Boothe {

    Functions functions = new Functions();
    Array2String array2String = new Array2String();
    Int2Bin int2Bin = new Int2Bin();
    TwosComplement twosComplement = new TwosComplement();
    Extend_Bits extendBits = new Extend_Bits();

    public void Boothe(int b , int s ,int A, int B, int outNum , int m){
        int[] binaryA ;
        int[] binaryB ;
        int[] binaryM ;
        int[] binaryUpperM ;
        int[] binaryNotA ;

        //functions.initialize(A, B, binaryA, binaryB, binaryM, binaryUpperM);
        binaryA = int2Bin.integerToBinary(A , A < 0); //if A<0 is true then the boolean "negative" passes true
        binaryB = int2Bin.integerToBinary(B*2 , B < 0);
        binaryM = int2Bin.integerToBinary(0 , false);
        binaryUpperM = int2Bin.integerToBinary(0 , false);
        binaryNotA = int2Bin.integerToBinary(0,false);

        binaryA = extendBits.extend(binaryA , b);
        binaryNotA = extendBits.extend(binaryNotA , b);

        try {
            FileWriter myWriter = new FileWriter("out.txt", true) ;
            BufferedWriter bf = new BufferedWriter(myWriter);

            //intro of the text
            bf.write("------------------------------------------");
            bf.newLine();
            bf.write("out-" + outNum);
            bf.newLine();
            bf.write("Boothe multiplication");
            bf.newLine();
            bf.write("A=" + A + "=" + array2String.array2string(b , 0 , binaryA)
                        + ",  B=" + B + "=" + array2String.array2string(b , 1, binaryB));
            bf.newLine();
            bf.write("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            bf.newLine();
            bf.write("(0, )  M=" + array2String.array2string(b ,0, binaryM) + " | " +
                    array2String.array2string(b ,0, binaryB));
            bf.newLine();
            bf.write("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            bf.newLine();

            for (int step = 1 ; step < b ; step +=2 ){
                //000 --> NOP
                //111 --> NOP
                if ((binaryB[step-1]==0 && binaryB[step]==0 && binaryB[step+1] == 0)
                    || (binaryB[step-1]==1 && binaryB[step]==1 && binaryB[step+1] == 1))
                {
                    bf.write("(" + (step/2 +1) + ", )  M=" + array2String.array2string(step + b -1, 0, binaryM)
                            + " | " + array2String.array2string(b, step - 1, binaryB));
                    bf.newLine();

                    //shift();
                    binaryM = functions.shift(binaryM, step, b, s, functions.isCarry,m);
                    binaryM = functions.shift(binaryM, step, b, s, functions.isCarry,m);
                    bf.write("(" + (step/2 +1) + ",>>)  M=" + array2String.array2string(step + b, 0, binaryM)
                            + " | " + array2String.array2string(b, step+1, binaryB));
                    bf.newLine();
                    bf.write("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    bf.newLine();
                }

                //001 --> +A
                //010 --> +A
                if ((binaryB[step-1]==1 && binaryB[step]==0 && binaryB[step+1] == 0)
                    || (binaryB[step-1]==0 && binaryB[step]==1 && binaryB[step+1] == 0))
                {
                    functions.add(binaryUpperM , binaryA , binaryM , b , step , m);
                    //printing in out.txt file
                    bf.write("("+(step/2 +1)+",+A)  M=" + array2String.array2string(step+ b -1 , 0 ,binaryM)
                            + " | " + array2String.array2string(b ,step-1 , binaryB));
                    bf.newLine();

                    //SHIFT
                    binaryM = functions.shift(binaryM , step , b ,s, functions.isCarry, m);
                    binaryM = functions.shift(binaryM , step , b ,s, functions.isCarry, m);
                    bf.write("("+(step/2 +1)+",>>)  M=" + array2String.array2string(step+ b  , 0 ,binaryM)
                            + " | " + array2String.array2string(b ,step+1 , binaryB));
                    bf.newLine();
                    bf.write("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    bf.newLine();
                }

                //011 --> +2A
                if (binaryB[step-1]==1 && binaryB[step]==1 && binaryB[step+1] == 0)
                {
                    functions.add(binaryUpperM , binaryA , binaryM , b , step, m);
                    functions.add(binaryUpperM , binaryA , binaryM , b , step, m);
                    //printing in out.txt file
                    bf.write("("+(step/2 + 1)+",+2A)  M=" + array2String.array2string(step+ b -1 , 0 ,binaryM)
                            + " | " + array2String.array2string(b ,step-1 , binaryB));
                    bf.newLine();

                    //SHIFT
                    binaryM = functions.shift(binaryM , step , b ,s, functions.isCarry , m);
                    binaryM = functions.shift(binaryM , step , b ,s, functions.isCarry , m);
                    bf.write("("+(step/2 +1)+",>>)  M=" + array2String.array2string(step+ b+1  , 0 ,binaryM)
                            + " | " + array2String.array2string(b ,step+1 , binaryB));
                    bf.newLine();
                    bf.write("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    bf.newLine();
                }

                //100 --> -2A
                if (binaryB[step-1]==0 && binaryB[step]==0 && binaryB[step+1] == 1)
                {
                    binaryNotA = binaryA ;   //don't want to change binaryA
                    binaryNotA = twosComplement.getTwosComplement(binaryNotA);
                    functions.add(binaryUpperM , binaryNotA , binaryM , b , step, m);
                    functions.add(binaryUpperM , binaryNotA , binaryM , b , step, m);
                    //printing in out.txt file
                    bf.write("("+(step/2 + 1)+",-2A)  M=" + array2String.array2string(step+ b -1 , 0 ,binaryM)
                            + " | " + array2String.array2string(b ,step-1 , binaryB));
                    bf.newLine();

                    //SHIFT
                    binaryM = functions.shift(binaryM , step , b ,s, functions.isCarry , m);
                    binaryM = functions.shift(binaryM , step , b ,s, functions.isCarry , m);
                    bf.write("("+(step/2 + 1)+",>>)  M=" + array2String.array2string(step+ b+1  , 0 ,binaryM)
                            + " | " + array2String.array2string(b ,step+1 , binaryB));
                    bf.newLine();
                    bf.write("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    bf.newLine();
                }

                //101 --> -A
                //110 --> -A
                if ((binaryB[step-1]==1 && binaryB[step]==0 && binaryB[step+1] == 1)
                        || (binaryB[step-1]==0 && binaryB[step]==1 && binaryB[step+1] ==1))
                {
                    binaryNotA = binaryA ;   //don't want to change binaryA
                    binaryNotA = twosComplement.getTwosComplement(binaryNotA);
                    functions.add(binaryUpperM , binaryNotA , binaryM , b , step, m);
                    //printing in out.txt file
                    bf.write("("+(step/2 +1)+",-A)  M=" + array2String.array2string(step+ b -1 , 0 ,binaryM)
                            + " | " + array2String.array2string(b ,step-1 , binaryB));
                    bf.newLine();

                    //SHIFT
                    binaryM = functions.shift(binaryM , step , b ,s, functions.isCarry , m);
                    binaryM = functions.shift(binaryM , step , b ,s, functions.isCarry , m);
                    bf.write("("+(step/2 +1)+",>>)  M=" + array2String.array2string(step+ b+1  , 0 ,binaryM)
                            + " | " + array2String.array2string(b ,step+1 , binaryB));
                    bf.newLine();
                    bf.write("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    bf.newLine();
                }


                functions.isCarry = false ;
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
