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
public class DoubleFrameTocken extends DefaultFrameTocken implements Serializable {

    private static final long serialVersionUID = 21129;

    private Double doubleValue;

    public DoubleFrameTocken(String doubleString) throws NumberFormatException {
        this.setFrameTockenString(doubleString);
    }

    @Override
    public Object getFrameTockenValue() {
        return this.doubleValue;

    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    @Override
    public void setFrameTockenString(String doubleString) throws NumberFormatException {
        if (!doubleString.equals("")) {
            Matcher matcher = Pattern.compile("[0-9]++\\.?[0-9]*+").matcher(doubleString);
            matcher.find();
            String string = matcher.group();
            this.doubleValue = Double.parseDouble(string);
        } else {
            this.doubleValue = null;
        }
        this.frameTockenString = doubleString;
    }
}
