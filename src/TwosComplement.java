public class TwosComplement {
    public int[] getTwosComplement(int[] binary){

        int startIndex = 0 ;
        for (int j = 0 ; j <32 ; j++){
            if (binary[j] == 1) {
                startIndex = j + 1;
                break;
            }
        }
        for (int k = startIndex ; k<32 ; k++){
            if (binary[k]==0)
                binary[k] = 1 ;
            else if (binary[k] == 1)
                binary[k] = 0 ;
        }

        return binary ;
    }
}
