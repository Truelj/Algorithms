
/**
 *
 * @author Jie Li
 * Date: 3/7
 * Descrition:
 * 1. get the ASCII integer value of the selected charactor
 * 2. Convert the ASCII integer value of the given charactor to 8-bit binary value 
 */
public class ASCII {
    
    public ASCII(char charactor){
        
    }
    public static int[] convertToBinary(char charactor){
        int ascii_decimal = (int)charactor;
        System.out.println("charactor: "+ charactor +" decimal value: "+ ascii_decimal);
        int[] ascii_binary =  new int[8];
        int i = 0; 
        while(ascii_decimal/2 != 0){
            ascii_binary[i++] = ascii_decimal%2;
            ascii_decimal /= 2;
        }
        System.out.println("ascii_decimal: "+ ascii_decimal);
        ascii_binary[i++] = ascii_decimal%2;
        while(i < 8){
            ascii_binary[i++] = 0;
        }
        int[] inverse = new int[8];
        for(int j = 0; j < ascii_binary.length; j++){
            inverse[j] = ascii_binary[7-j];
        }
        
        return inverse;
    }
    /*
     * @param charactors is an array of charactors
     * if the length of charactors array is greater than 8, only read 8 charactors
     * if the length of charactors array is smaller than 8 ,adding enought 0s after output_binary array
     * @return int[] is the concatenation of 8-bit binary values of charactors
     */
    public static int[] convertToBinary_2(char[] charactors){
        int[] output_binary = new int[64];
        int i;
        for(i = 0; i < charactors.length && i < 8; i++){
            //get binary value of each charactor first
            int[] charactor_binary = convertToBinary(charactors[i]);
            for(int j = 0; j < charactor_binary.length; j++){
                output_binary[i*8 + j] = charactor_binary[j];
            }
        }
        while(i < 8){
            for(int j = 0; j<8;j++){
                output_binary[i*8 + j] = 0;
            }
            i++;
        }
        print(output_binary);
        return output_binary;
    }
     /*
     * @param charactors is an array of charactors
     * @param start -- if there are more than 8 charactors after the starting position, only read 8 charactors
     * if there are fewer than 8 charactors after the starting position ,adding enought 0s after output_binary array
     * @return int[] is the concatenation of 8-bit binary values of charactors
     */  
     public static int[] convertToBinary_3(char[] charactors, int start){
        int[] output_binary = new int[64];
        int i;
        for(i = 0; i < (charactors.length - start) && i < 8; i++){//set i < 8 to make sure only 8 charactors would be read at most
            //get binary value of each charactor first
            int[] charactor_binary = convertToBinary(charactors[i + start]);
            for(int j = 0; j < charactor_binary.length; j++){
                output_binary[i*8 + j] = charactor_binary[j];
            }
        }
        while(i < 8){
            for(int j = 0; j<8;j++){
                output_binary[i*8 + j] = 0;
            }
            i++;
        }
        print(output_binary);
        return output_binary;
    }
    /*
     * @param input_binary is an arary of the 8-bit binary representations for some ASCII charactors
     * @param start indicates which group of the 8-bit to be converted
     * @return the decimal order of the ASCII charactor
     */
    public static int convertToDecimal(int[] input_binary, int index){
        int decimal =0;
        for(int i = 0; i < 8; i++){
            decimal += (int)(input_binary[i + 8*index]*Math.pow(2, 7-i));
        }
        return decimal;
    }
    
        /*
     * @param input_binary is an arary of the 8-bit binary representations for some ASCII charactors
     * @return the decimal order of those ASCII charactors
     */
    public static int[] convertToDecimal_2(int[] input_binary){
        int[] decimals = new int[input_binary.length/8];
        for(int i = 0; i < input_binary.length/8; i++){
             decimals[i] = convertToDecimal(input_binary, i);
        }
        print(decimals);
        return decimals;
    }
    
    
    public static void print(int[] array){
        for(int i = 0; i<array.length; i++){
            System.out.print(array[i]+ " ");
        }
        System.out.println("");
    }
    
    public static void main(String[] args){
        String one = "ABCDABCD";
        char[] two = one.toCharArray();
        ASCII.convertToBinary_2(two);
        char[] ONE = {'A','B','C','D','A','B','C','D','A','B','C','D'};
        char[] TWO = {'A','B','C','D','A','B','C','D'};
        ASCII.convertToBinary_2(ONE);
        ASCII.convertToBinary_3(TWO,4);
        int[] input_binary = {
            0,1,0,0,0,0,1,0,
            0,1,0,0,0,0,1,0,
            0,1,0,0,0,0,1,0,
            0,1,0,0,0,0,1,0,
            0,1,0,0,0,0,0,1
        };
        ASCII.convertToDecimal_2(input_binary);
    }
}
