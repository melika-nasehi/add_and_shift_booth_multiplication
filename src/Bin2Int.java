public class Bin2Int {
    public int binaryToInteger(int[] binary){
        int result = 0 ;
        for (int i =0 ; i < 31 ; i++){
            if(binary[i] == 1) {
                result = (int) (result + binary[i]*(Math.pow(2,i)));
            }
        }

        return result;
    }
}
