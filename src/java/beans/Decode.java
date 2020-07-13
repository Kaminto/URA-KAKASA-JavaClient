/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author bajuna
 */
public class Decode {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String string=new String(Base64.decodeBase64("DYgS9VuybYc4GBBqO6Zg68kY7eSqNA+85zux2cFB4TNnwOBPpPXmYC2wrZcDyRWyJTAjzcDYfTPsfgYp9dNa3LZ8rxI+krv7+UD26vc7CoUvy+FCXPX9trcRRsPlNhSfbMaQuOKkly/KSyFdSvt77tmv0MeSYFH8j8WKgfADcuNDjLOfUEFN6W1L89FZrz5qZ+bPEpPpWTqMqK7jtoP86kJ6rHqQRG3ERCgi0v6MYOejB4F3T5WPoKsThYCFof7E27/JypGYlQFmNJCinT3qhAFxkZgziHTz8iv8nN1IkV0Jg8x/tT98jg0Nkz335mRkjU1tizkDUn/nSYLFexmRnQ=="));
        System.out.println(string);
    }
    
}
