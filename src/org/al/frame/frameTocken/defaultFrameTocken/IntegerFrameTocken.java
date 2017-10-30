/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.al.frame.frameTocken.defaultFrameTocken;

import java.io.Serializable;
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
        this.setFrameTockenString(frameTockenString);

    }
    

    @Override
    public Object getFrameTockenValue() {
        return integerValue;
    }

    @Override
    public void setFrameTockenString(String tockenString) throws NumberFormatException {
        if (!tockenString.equals("")) {
            Pattern pattern=Pattern.compile("\\d++");
            this.integerValue = Integer.parseInt(pattern.matcher(tockenString).group());
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
