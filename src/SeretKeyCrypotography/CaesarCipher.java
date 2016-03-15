/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SeretKeyCrypotography;

/**
 *
 * @author jieli
 * Description: Substitute for each letter of the message, the letter which is
 * 3 letters later in the alphabet
 */
public class CaesarCipher {
    /*
    Name: Caesar cipher
    @param text is the plaintext, assuming all letters in string are in lower case,
    and there is no space in the text. The defaul key used is 3.
    @return cipher text
    */
    public String encryption(String text){
        char[] textChar = text.toCharArray();
        String cipher = "";
        for(int i = 0; i < textChar.length; i++){
            //each letter is between 0 and 25
            textChar[i]=(char)('a' + (textChar[i] - 'a' +3)%26);
            cipher += textChar[i];
        }
        return cipher;
    }
    /*
    Name: Captain Midnight Secret Decoder rings
    @param text is the plaintext, assuming all letters in string are in lower case,
    and there is no space in the text
    @param key is between 0 and 25, including 0 and 25
    @return cipher text
    */
    public String encryption(String text, int key){
        char[] textChar = text.toCharArray();
        String cipher = "";
        for(int i = 0; i < textChar.length; i++){
            //each letter is between 0 and 25
            textChar[i]=(char)('a' + (textChar[i] - 'a' + key)%26);
            cipher += textChar[i];
        }
        return cipher;
    }
    public static void main(String[] args){
        CaesarCipher c = new CaesarCipher();
        System.out.println(c.encryption("security"));
        System.out.println(c.encryption("security",3));
    }
}
