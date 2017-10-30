/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.al.frame.frameTocken;

import java.io.Serializable;

/**
 *
 * @author rahimAdmin
 */
public abstract class FrameTocken implements Serializable {

    private static final long serialVersionUID = 21121;

    protected String frameTockenString;

    protected FrameTocken() {
        this.frameTockenString = "";
    }

    public abstract Object getFrameTockenValue();

    public abstract void setFrameTockenString(String frameTockenString);

    public String getFrameTockenString() throws Exception {
        return frameTockenString;
    }

}
