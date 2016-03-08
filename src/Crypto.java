/**
 *
 * @author Jie Li
 * Description:
 *  - implementing the DES algorithm
 *  - implementing the ECB algorithm
 *  - implementing the CBC algorithm
 */
public class Crypto {
    //create 8 s-boxes
    private String[] box_1 = {"1110","0100","1101","0001","0010","1111","1011","1000","0011","1010","0110","1100","0101","1001","0000","0111",
            "0000","1111","0111","0100","1110","0010","1101","0001","1010","0110","1100","1011","1001","0101","0011","1000",
            "0100","0001","1110","1000","1101","0110","0010","1011","1111","1100","1001","0111","0011","1010","0101","0000",
            "1111","1100","1000","0010","0100","1001","0001","0111","0101","1011","0011","1110","1010","0000","0110","1101"
        
    };
    private String[] box_2 = {"1111","0001","1000","1110","0110","1011","0011","0100","1001","0111","0010","1101","1100","0000","0101","1010",
             "0011","1101","0100","0111","1111","0010","1000","1110","1100","0000","0001","1010","0110","1001","1011","0101",
             "0000","1110","0111","1011","1010","0100","1101","0001","0101","1000","1100","0110","1001","0011","0010","1111",
             "1101","1000","1010","0001","0011","1111","0100","0010","1011","0110","0111","1100","0000","0101","1110","1001"        
    };
    private String[] box_3 = {
        "1010","0000","1001","1110","0110","0011","1111","0101","0001","1101","1100","0111","1011","0100","0010","1000",
        "1101","0111","0000","1001","0011","0100","0110","1010","0010","1000","0101","1110","1100","1011","1111","0001",
        "1101","0110","0100","1001","1000","1111","0011","0000","1011","0001","0010","1100","0101","1010","1110","0111",
        "0001","1010","1101","0000","0110","1001","1000","0111","0100","1111","1110","0011","1011","0101","0010","1100"
    };
    private String[] box_4 ={
        "0111","1101","1110","0011","0000","0110","1001","1010","0001","0010","1000","0101","1011","1100","0100","1111",
        "1101","1000","1011","0101","0110","1111","0000","0011","0100","0111","0010","1100","0001","1010","1110","1001",
        "1010","0110","1001","0000","1100","1011","0111","1101","1111","0001","0011","1110","0101","0010","1000","0100",
        "0011","1111","0000","0110","1010","0001","1101","1000","1001","0100","0101","1011","1100","0111","0010","1110"
    };
    private String[] box_5 ={
        "0010","1100","0100","0001","0111","1010","1011","0110","1000","0101","0011","1111","1101","0000","1110","1001",
        "1110","1011","0010","1100","0100","0111","1101","0001","0101","0000","1111","1010","0011","1001","1000","0110",
        "0100","0010","0001","1011","1010","1101","0111","1000","1111","1001","1100","0101","0110","0011","0000","1110",
        "1011","1000","1100","0111","0001","1110","0010","1101","0110","1111","0000","1001","1010","0100","0101","0011"
    };
    private String[] box_6 = {
        "1100","0001","1010","1111","1001","0010","0110","1000","0000","1101","0011","0100","1110","0111","0101","1011",
        "1010","1111","0100","0010","0111","1100","1001","0101","0110","0001","1101","1110","0000","1011","0011","1000",
        "1001","1110","1111","0101","0010","1000","1100","0011","0111","0000","0100","1010","0001","1101","1011","0110",
        "0100","0011","0010","1100","1001","0101","1111","1010","1011","1110","0001","0111","0110","0000","1000","1101"
    };
    private String[] box_7 ={
        "0100","1011","0010","1110","1111","0000","1000","1101","0011","1100","1001","0111","0101","1010","0110","0001",
        "1101","0000","1011","0111","0100","1001","0001","1010","1110","0011","0101","1100","0010","1111","1000","0110",
        "0001","0100","1011","1101","1100","0011","0111","1110","1010","1111","0110","1000","0000","0101","1001","0010",
        "0110","1011","1101","1000","0001","0100","1010","0111","1001","0101","0000","1111","1110","0010","0011","1100"
    };
    private String[] box_8 ={
        "1101","0010","1000","0100","0110","1111","1011","0001","1010","1001","0011","1110","0101","0000","1100","0111",
        "0001","1111","1101","1000","1010","0011","0111","0100","1100","0101","0110","1011","0000","1110","1001","0010",
        "0111","1011","0100","0001","1001","1100","1110","0010","0000","0110","1010","1101","1111","0011","0101","1000",
        "0010","0001","1110","0111","0100","1010","1000","1101","1111","1100","1001","0000","0011","0101","0110","1011"
    };
    
    public Crypto(){
        
    }

    /*********************************************generating per-round key****************************************************/
    /*
     * @param key is an array of 64 integers, 0 or 1
     * @return an array of 56 integers, 0 or 1
     */
    public int[] initialKeyPermutation(int[] key){
        System.out.println("Initial key permutation");
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
                key_i[i2 * 7 + j2] = newKey[i1 * 7 + j1];
            }
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
                key_i[i2 * 7 + j2] = key_right[i1 * 7 + j1];
            }
        }
        
        return key_i;
    }
    /*
     * @param key_i is an array of 56 intergers, 0 or 1
     * @return is an array of 48 integers, 0 or 1, which is the per-round key
     */
    public int[] perRoundPermutation(int[] key_i){
        int[] keyPerRound = new int[48];
        keyPerRound[0] = key_i[31];keyPerRound[1] = key_i[0];keyPerRound[2] = key_i[1];keyPerRound[3] = key_i[2];keyPerRound[4] = key_i[3];keyPerRound[5] = key_i[4];
        keyPerRound[6] = key_i[3];keyPerRound[7] = key_i[4];keyPerRound[8] = key_i[5];keyPerRound[9] = key_i[6];keyPerRound[10] = key_i[7];keyPerRound[11] = key_i[8];
        keyPerRound[12] = key_i[7];keyPerRound[13] = key_i[8];keyPerRound[14] = key_i[9];keyPerRound[15] = key_i[10];keyPerRound[16] = key_i[11];keyPerRound[17] = key_i[12];
        keyPerRound[18] = key_i[11];keyPerRound[19] = key_i[12];keyPerRound[20] = key_i[13];keyPerRound[21] = key_i[14];keyPerRound[22] = key_i[15];keyPerRound[23] = key_i[16];
        
        keyPerRound[24] = key_i[15];keyPerRound[25] = key_i[16];keyPerRound[26] = key_i[17];keyPerRound[27] = key_i[18];keyPerRound[28] = key_i[19];keyPerRound[29] = key_i[20];
        keyPerRound[30] = key_i[19];keyPerRound[31] = key_i[20];keyPerRound[32] = key_i[21];keyPerRound[33] = key_i[22];keyPerRound[34] = key_i[23];keyPerRound[35] = key_i[24];
        keyPerRound[36] = key_i[23];keyPerRound[37] = key_i[24];keyPerRound[38] = key_i[25];keyPerRound[39] = key_i[26];keyPerRound[40] = key_i[27];keyPerRound[41] = key_i[28];
        keyPerRound[42] = key_i[27];keyPerRound[43] = key_i[28];keyPerRound[44] = key_i[29];keyPerRound[45] = key_i[30];keyPerRound[46] = key_i[31];keyPerRound[47] = key_i[0];
 
        return keyPerRound;
        
    }
            
    /*
     * @param key_i is an array of 56 integers, 0 or 1
     * divide key ino 2 sub arrays of 28 integers, 0 or 1
     * Perform rotation on each sub array
     * On round 1,2,9,16 rotates 1 bit to the left; on other rounds, rotates 2 bits to the left
     * After rotation, perform permutation on 56 bits to get 48-bit per-round key
     * @return is an array of 56 integers, 0 or 1
     */
    public int[] perRoundRotation_1(int[] key_i){
        int[] key = new int[56];
        for(int i = 1; i < key_i.length/2; i++){//1 to 27
            key[i-1] = key_i[i];
            
        }
        key[27] = key_i[0];
        for(int i = 29; i < key_i.length; i++){//28 to 55
            key[i-1] = key_i[i];
        }
        key[55] = key_i[28];  
        return key;
    }
    /*
     * @param key_i is an array of 56 integers, 0 or 1
     * divide key ino 2 sub arrays of 28 integers, 0 or 1
     * Perform rotation on each sub array
     * On round 1,2,9,16 rotates 1 bit to the left; on other rounds, rotates 2 bits to the left
     * After rotation, perform permutation on 56 bits to get 48-bit per-round key
     * @return is an array of 56 integers, 0 or 1
     */
    public int[] perRoundRotation_2(int[] key_i){
        //make sure that there is no index-out-of-bound error
        int[] key = new int[56];
        for(int i = 2; i < key_i.length/2; i++){//2 to 27
            key[i-2] = key_i[i];
        }
        key[26] = key_i[1];
        key[27] = key_i[1];
        for(int i = 30; i < key_i.length; i++){//30 to 55
            key[i-2] = key_i[i];
        }
        key[54] = key_i[28];
        key[55] = key_i[29];;    
        return key;
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
        System.out.println("Data input:");
        //testing the initial permutation
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.print(input[i*8+j]);
               
            }
            System.out.println("");
        }
        System.out.println("Initial data permutation:");
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
        int[] left_input = new int[32];
        int[] right_input = new int[32];
        int[] output = new int[64];
        for(int i = 0; i < perRoundText.length/2; i++){//0 to 31
            left_input[i] = perRoundText[i];
        }
        for(int i = 32; i < perRoundText.length; i++){//32 to 63
            right_input[i-32] = perRoundText[i];
            output[i-32] = perRoundText[i];
        }
        
        int[] manglerOutput =  manglerFunction(right_input, perRoundKey);//32 bits
        //XOR manglerOutput and left_input
        for(int i =0; i < manglerOutput.length; i++){
            if((manglerOutput[i]+left_input[i])==1){
                output[32 + i] = 1;
            }else{
                output[32 + i] = 0;
            }
        }
        return output;
    }
    
    /*
     * @param rightText is an array of 32 integers, 0 or 1
     * @param perRoundKey is an array of 48 integers, 0 or 1
     * @return an array of 32 integers, 0 or 1, as 2nd half of per-round ciphertext
     */
    public int[] manglerFunction(int[] rightText, int[] perRoundKey){
        String boxOutput="";
        int[] one  = new int[6];
        int[] two = new int[6];
        int[] three = new int[6];
        int i = 0, j = 0;
        
        //divide rightText into 8 chuncks of 4 bits
        //divide perRoundKey into 8 chuncks of 6 bits
        while(i<8){
            if(i == 0){
                one[j++] = rightText[31];
                one[j++] = rightText[i*4];
                one[j++] = rightText[i*4+1];
                one[j++] = rightText[i*4+2];
                one[j++] = rightText[i*4+3];
                one[j++] = rightText[i*4+4];
            }else if(i==7){
                one[j++] = rightText[i*4 - 1];
                one[j++] = rightText[i*4];
                one[j++] = rightText[i*4+1];
                one[j++] = rightText[i*4+2];
                one[j++] = rightText[i*4+3];
                one[j++] = rightText[0];   
            }else{
                one[j++] = rightText[i*4 - 1];
                one[j++] = rightText[i*4];
                one[j++] = rightText[i*4+1];
                one[j++] = rightText[i*4+2];
                one[j++] = rightText[i*4+3];
                one[j++] = rightText[i*4+4];
            }
            j = 0;
            two[j++] = perRoundKey[6*i];
            two[j++] = perRoundKey[6*i+1];
            two[j++] = perRoundKey[6*i+2];
            two[j++] = perRoundKey[6*i+3];
            two[j++] = perRoundKey[6*i+4];
            two[j++] = perRoundKey[6*i+5];
            j = 0;
            //XOR one and two to get s-box input, which is three 
            for(int k = 0; k < 6; k++){
                if((one[k] + two[k])== 1){
                    three[k] = 1;
                }else{
                    three[k] = 0;
                }
            }
            //get row and column number in s-box
            int row = 2*three[5] + three[4];
            int column = 8*three[3] + 4*three[2] + 2*three[1] + three[0];
            //decide which s-box to use
            switch(i){
                case 0:
                    //read box_1
                    boxOutput += box_1[16*row + column];
                    break;
                case 1:
                    boxOutput += box_2[16*row + column];
                    break;
                case 2:
                    boxOutput += box_3[16*row + column];
                    break;
                case 3:
                    boxOutput += box_4[16*row + column];
                    break;
                case 4:
                    boxOutput += box_5[16*row + column];
                    break;
                case 5:
                    boxOutput += box_6[16*row + column];
                    break;
                case 6:
                    boxOutput += box_7[16*row + column];
                    break;
                case 7:
                    boxOutput += box_8[16*row + column];
                    break;
            }
                    
            i++;
        }//end of while
        //convert boxOutput to integer array
        char[] boxOutputChar = boxOutput.toCharArray();
        int[] boxOutputInt = new int[32];
        for(int k = 0; k < boxOutputChar.length; k++){
            if(boxOutputChar[k]=='1'){
               boxOutputInt[k] = 1; 
            }else{
               boxOutputInt[k] = 0;
            }
        }
        return boxOutputInt;
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
        System.out.println("final data permutation:");
        //testing the final permutation
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.print(input[i*8+j]);
               
            }
            System.out.println("");
        }
        return input;
    }

    public void print(int[] data){
        for(int i =0; i < data.length; i++){
            System.out.print(data[i] + " ");
        }
        System.out.println("");
    }
    /*********************************************End of encypting plaintext********************************************************/
    
    /*********************************************algorithms********************************************************/
    /*
     * @param plaintext is an array of 64 integers, 0 or 1
     * @param key is an array of 64 integers, 0 or 1
     * @return an array of 64 integers, 0 or 1
     */
    public int[] DES(int[] plaintext, int[] key){
        //do the inital permutation on data 
        int[] output_round = initialDataPermutation(plaintext);
        //do the initial permunation on key to get 56 bits 
        int[] key_i = new int[56];
        key_i = initialKeyPermutation(key);
        //generate per-round 48-bit key-- perRoundRotation(), perRoundPermutation()
        int[] key_round = new int[48];
        for(int i=1; i<17; i++){//16 rounds
            if(i == 1 || i == 2 || i == 9 || i == 16){
               key_i = perRoundRotation_1(key_i);//56 bits
               key_round = perRoundPermutation(key_i);//48 bits
               //do the per-round encryption 
               output_round = perRoundEncryption(output_round, key_round);//64 bits
               
            }else{
               key_i = perRoundRotation_2(key_i);//56 bits
               key_round = perRoundPermutation(key_i);//48 bits
               //do the per-round encryption 
               output_round = perRoundEncryption(output_round, key_round);//64 bits
            }
            System.out.println("Per round key:");
            print(key_round);
            System.out.println("Per round output");
            print(output_round);
        }//finish 16 rounds
        //final permutation on data
        int[] output = finalDataPermutation(output_round);
        return output;
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
        Crypto algorithms = new Crypto();
        int[] input = new int[64];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(j % 2 == 1){
                    input[i*8+j] = 1;
                }else{
                    input[i*8+j] = 0;
                }
            }
        }
        
        int[] key = new int[64];
        for(int i = 0; i< 8; i++){
            for(int j= 0; j< 8; j++){
                key[i*8+ j] = 0;    
            }
        }
        algorithms.DES(input, key);
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
