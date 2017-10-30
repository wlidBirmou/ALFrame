/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.al.frame.frameTocken.defaultFrameTocken;

import java.io.Serializable;

/**
 *
 * @author rahimAdmin
 */
public class StringFrameTocken extends DefaultFrameTocken implements Serializable{
    private static final long serialVersionUID = 211211;

    public StringFrameTocken(String stringFrameTocken) {
        super();
        this.setFrameTockenString(stringFrameTocken);
    }


    @Override
    public String getFrameTockenValue() {
        return this.frameTockenString;
    }

    @Override
    public void setFrameTockenString(String frameTockenString) {
        this.frameTockenString=frameTockenString;
    }
 
}
