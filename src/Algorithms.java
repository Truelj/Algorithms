/**
 *
 * @author Jie Li
 * Description:
 *  - implementing the DES algorithm
 *  - implementing the ECB algorithm
 *  - implementing the CBC algorithm
 */
public class Algorithms {
    public Algorithms(){
        
    }

    /*********************************************generating per-round key****************************************************/
    /*
     * @param key is an array of 64 integers, 0 or 1
     * @return an array of 56 integers, 0 or 1
     */
    public int[] initialKeyPermutation(int[] key){
        int[] newKey = new int[56];
        //disgarding 8 parities in key
        //i is row number, j is column number
        for(int i1 = 0, j2 = 0; i1 < 7 && j2 <7; i1++, j2++){//7 rows
            for(int j1 = 0, i2 = 7; j1 < 8 && i2 >= 0; j1++,i2--){//8 columns
                newKey[i1 * 8 + j1] = key[i2 * 8 + j2 ];
            } 
        }
        //perform initial permutation on newKey
        //System.out.println("upper side:");
        int[] key_i  = new int[56];
        for(int i1 = 0, i2 = 0; i1 < 4 && i2 < 4; i1++, i2++){//row0,1,2,3
            for(int j1 = 0, j2 = 0; j1 < 7 && j2 < 7; j1++, j2++){//column0,1,2,3,4,5,6
                System.out.print(newKey[i1 * 7 + j1]);
                key_i[i2 * 7 + j2] = newKey[i1 * 7 + j1];
            }
            System.out.println("");
        }
        
        int[] key_right = new int[28];
        for(int i1 = 6, i2 = 0; i1 >3 && i2 < 3; i1--, i2++){//row 4,5,6
            for(int j1 = 0, j2 = 0; j1 < 8 && j2 < 8; j1++, j2++){
                key_right[i2 * 8 + j2] = newKey[i1 * 8 + j1];
            }    
        }
        key_right[24] = newKey[28];key_right[25] = newKey[29]; key_right[26] = newKey[30]; key_right[27] = newKey[31];
        for(int i1 = 0, i2 = 4; i1 < 4 && i2 < 8; i1++, i2++){
            for(int j1 = 0, j2 = 0; j1 < 7 && j2 < 7; j1++, j2++){
                System.out.print(key_right[i1 * 7 + j1]);
                key_i[i2 * 7 + j2] = key_right[i1 * 7 + j1];
            }
            System.out.println("");
        }

        return key_i;
    }

    /*
     * @param key_i is an array of 56 integers, 0 or 1
     * divide key ino 2 sub arrays of 28 integers, 0 or 1
     * Perform rotation on each sub array
     * @return is an array of 56 integers, 0 or 1
     */
    public int[] perRoundRotation(int[] key_i){
        return null;
    }
    /*
     * @param key is an array of 56 integers, 0 or 1
     * @return an arry of 48 integers, 0 or 1, as a per-round key
     */
    public int[] perRoundPermutation(int[] key){
        return null;
    }
    /*********************************************End of generating per-round key*********************************************/
    
    
    
    /*********************************************Encypting plaintext*********************************************************/
    /*
     * @param plaintext is an array of 64 integers, 0 or 1
     * @return an array of 64 integers, 0 or 1
     */
    public int[] initialDataPermutation(int[] input){
        int[] output  = new int[64];
        for( int input_column = 0; input_column < 8; input_column++){
            for( int input_row = 0, output_column = 7; input_row < 8 && output_column >= 0; input_row++, output_column--){
                if(input_column % 2 == 1){//odd column
                    output[(input_column/2)*8 + output_column] = input[input_row*8 + input_column];
                }else{//even column
                    output[(input_column/2 + 4)*8 + output_column] = input[input_row*8 + input_column];
                }
            }
        }
        System.out.println("input:");
        //testing the initial permutation
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.print(input[i*8+j]);
               
            }
            System.out.println("");
        }
        System.out.println("output:");
        //testing the initial permutation
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.print(output[i*8+j]);
               
            }
            System.out.println("");
        }
        return output;
    }

    /*
     * @param perRoundText is an array of 64 integers, 0 or 1
     * @param perRoundKey is an array of 48 integers, 0 or 1
     * @return an array of 68 integers, 0 or 1, as per-round ciphertext. 
     */
    public int[] perRoundEncryption(int[] perRoundText,int[] perRoundKey){
        return null;
    }
    
    /*
     * @param rightText is an array of 32 integers, 0 or 1
     * @param perRoundKey is an array of 48 integers, 0 or 1
     * @return an array of 32 integers, 0 or 1, as 2nd half of per-round ciphertext
     */
    public int[] manglerFunctoin(int[] rightText, int[] perRoundKey){
        return null;
    }
    /*
     * @param output is an array of 64 integers, 0 or 1
     * @return an array of 64 integers, 0 or 1
     * finalPermutation is an inverse of initialPermutation
     */
    public int[] finalDataPermutation(int[] output){
        int[] input = new int[64];
        for(int output_row = 0; output_row < 8; output_row++){
            for(int output_column = 7; output_column >= 0; output_column-- ){
                if(output_row < 4){
                    input[output_column*8 + output_row*2 + 1] = output[output_row*8 + output_column];
                }else{
                    input[output_column*8 + (output_row - 4)*2] = output[output_row*8 + output_column];
                }
            }
        }
        return input;
    }
    public void printInput(int[] input){
        
        System.out.println("input:");
        //testing the final permutation
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.print(input[i*8+j]);
               
            }
            System.out.println("");
        }
    }
    public void printOutput(int[] output){
        System.out.println("output:");
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.print(output[i*8+j]);
               
            }
            System.out.println("");
        }
    }
    /*********************************************End of encypting plaintext********************************************************/
    
    /*********************************************algorithms********************************************************/
    /*
     * @param plaintext is an array of 64 integers, 0 or 1
     * @param key is an array of 64 integers, 0 or 1
     * @return an array of 64 integers, 0 or 1
     */
    public int[] DES(int[] plaintext, int[] key){
        //do the initial permunation on key to get 56 bits 
        //generate per-round 48-bit key-- perRoundRotation(), perRoundPermutation()
        
        //encript 64-bit plaintext in each round
        return null;
    }
    
    
    
    /*
     * @param plaintext is a string
     *                  needs to be converted to an array of integers, 0 or 1, by transforming each letter in the string into 8-bit ASCII code              
     * @param key is a string
     *            needs to be converted to an array of integers, 0 or 1, by transforming each letter in the string into 8-bit ASCII code 
     * @return an array of decimal numbers, by grouping every 8 elemnents in the array as one ASCII code and converting it into its decimal format.      
     */
    public int[] ECB(String plaintext, String key){
        return null;
    }
    
    
    /*
     * @param plaintext - is a string
     *                  - needs to be converted to an array of integers, 0 or 1, by transforming each letter in the string into 8-bit ASCII code   
     * @param key - is a string 
     *            - needs to be converted to an array of integers, 0 or 1, by transforming each letter in the string into 8-bit ASCII code
     * @param IV - is initialization verctor
     *           - is a string
     *           - needs to be converted to an array of integers, 0 or 1, by transforming each letter in the string into 8-bit ASCII code
     * @return an array of decimal numbers, by grouping every 8 elemnents in the array as one ASCII code and converting it into its decimal format. 
     */
    public int[] CBC(String plaintext, String key, String IV){
        return null;
    }
    
    public static void main(String[] args){
        //testing 3 algorithms
        Algorithms algorithms = new Algorithms();
        int[] input = new int[64];
        int[] output = new int[64];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(j % 2 == 1){
                    input[i*8+j] = 1;
                }else{
                    input[i*8+j] = 0;
                }
            }
        }
        output = algorithms.initialDataPermutation(input);
        algorithms.finalDataPermutation(output);
        int[] key = new int[64];
        int index = 1;
        for(int i = 0; i< 8; i++){
            for(int j= 0; j< 8; j++){
                key[i*8+ j] = index++;
                
            }
        }
        algorithms.initialKeyPermutation(key);
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
