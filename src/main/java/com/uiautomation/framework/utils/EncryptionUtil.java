package com.uiautomation.framework.utils;

import java.util.Base64;
import java.util.Base64.*;

public class EncryptionUtil {
    private String text;
    public EncryptionUtil(String text){
        this.text = text;
    }

    public String getEncodedText(){
        Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(text.getBytes());
    }
    public String getDecodedText(){
        Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(text));
    }
}
