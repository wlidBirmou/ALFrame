/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.al.frame.frameTocken.defaultFrameTocken;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author rahimAdmin
 */
public class IntegerFrameTocken extends DefaultFrameTocken implements Serializable{
    private static final long serialVersionUID = 211210;

    private Integer integerValue;

    public IntegerFrameTocken(String integerFrameTocken) throws NumberFormatException {
        super();
        this.setFrameTockenString(integerFrameTocken);

    }
    

    @Override
    public Object getFrameTockenValue() {
        return integerValue;
    }

    @Override
    public void setFrameTockenString(String tockenString) throws NumberFormatException {
        if (!tockenString.equals("")) {
            Pattern pattern=Pattern.compile("[0-9]++");
            Matcher matcher=pattern.matcher(tockenString);
            matcher.find();
            this.integerValue = Integer.parseInt(matcher.group());
            this.frameTockenString = tockenString;
        } else {
            integerValue = null;
            this.frameTockenString = "";
        }
    }

    public int getIntegerValue() {
        return integerValue;
    }

   

    

}
