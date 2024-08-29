public class Extend_Bits {
    public int[] extend(int[] binaryNumber , int bits){
        if(binaryNumber[bits-1]==1)
            binaryNumber[bits] = 1 ;
        return binaryNumber ;
    }
}
