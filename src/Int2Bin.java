public class Int2Bin {

    TwosComplement twosComplement = new TwosComplement();
    public int[] integerToBinary(int n , boolean negetive) {
        n = Math.abs(n) ;
        int i = 0 ;
        int[] binary = new int [32] ;
        while (n!=0){
            int mode = n%2 ;
            binary[i] = mode ;
            i++ ;
            n = n/2 ;
        }

        if(negetive){
            binary = twosComplement.getTwosComplement(binary);
        }


        return binary ;
    }
}
