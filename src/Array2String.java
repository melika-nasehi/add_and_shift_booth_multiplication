public class Array2String {
    public String array2string(int start_bit ,int end_bit , int[] array){
        String result = "" ;
        for (int i = start_bit ; i >=end_bit && i >= 0 ; i--) {
            result = result + array[i];
        }

        return result ;
    }
}
