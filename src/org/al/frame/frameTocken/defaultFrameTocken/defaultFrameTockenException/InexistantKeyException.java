/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.al.frame.frameTocken.defaultFrameTocken.defaultFrameTockenException;

/**
 *
 * @author rahimAdmin
 */
public class InexistantKeyException extends RuntimeException{

    public InexistantKeyException(String message) {
        super(message);
    }
    
    public InexistantKeyException(){
        super();
    }
}
