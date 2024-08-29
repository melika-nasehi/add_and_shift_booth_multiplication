public class Functions {

    Bin2Int bin2Int = new Bin2Int();
    Int2Bin int2Bin = new Int2Bin();

    boolean isCarry = false ;

    public void add (int[] binaryUpperM , int[] binaryA ,int[] binaryM,int bitB , int step , int m ){
        //boothe needs extending
        int bit = bitB ;
        if(m==1)
            bit++;
        //use just required bits of A (LSB)
        int [] binaryRequiredA = new int[32];
        for (int i = 0 ; i < bit ; i++)
            binaryRequiredA[i] = binaryA[i] ;

        //using upperM not all the M
        for (int i=0 ; i <bit ; i++)
            binaryUpperM[i] = binaryM[step-1+i] ;

        //keeping sign_bit before adding(for checking overflow)
        int upperM_sign_bit = binaryUpperM[bit -1] ; //upperM
        int A_sign_bit = binaryA[bit -1] ;    //A

        //adding
        int a = bin2Int.binaryToInteger(binaryUpperM);
        int b = bin2Int.binaryToInteger(binaryRequiredA);
        int res = a+b ;

        //System.out.println("upperM: "+ a +" A: "+ b +" res: "+ res);
        binaryUpperM = int2Bin.integerToBinary(res , false);

        //checking carry
        int result_sign_bit = binaryUpperM [bit];
        isCarry = checkCarry(upperM_sign_bit , A_sign_bit , result_sign_bit) ;

        //updating big M with UpperM
        for (int i=0 ; i < bit ; i++)
            binaryM[step-1+i] = binaryUpperM[i];

    }


    public int[] shift (int[] binary, int step , int bit ,int sign , boolean isCarry , int m){
        //boothe needs extending
        int bits = bit ;
        if(m==1)
            bits++;

        if(!isCarry) {
            //unsigned
            if (sign == 0)
                binary[step + bits - 1] = 0;

                //signed
            else if (sign == 1) {
                if (binary[step + bits - 2] == 0)
                    binary[step + bits - 1] = 0;
                else if (binary[step + bits - 2] == 1)
                    binary[step + bits - 1] = 1;
            }
        }

        else if (isCarry)
            binary[step + bit - 1] = 1;

        return binary ;
    }


    public boolean checkCarry(int signBit1 ,int signBit2 , int resultBit){
        boolean carry = false ;
        //there is a carry,and it's not overflow (we omit overflows)
        if (resultBit == 1 && (signBit1 != signBit2))
            carry = false ;
        else if (resultBit == 1 && (signBit1 == signBit2))
            carry = true ;
        return carry ;
    }


    public void initialize(int A , int B , int[] binaryA , int[] binaryB , int[] binaryM , int[] binaryUpperM){

        binaryA = int2Bin.integerToBinary(A , A < 0); //if A<0 is true then the boolean "negative" passes true
        binaryB = int2Bin.integerToBinary(B , B < 0);
        binaryM = int2Bin.integerToBinary(0 , false);
        binaryUpperM = int2Bin.integerToBinary(0 , false);
    }


}
