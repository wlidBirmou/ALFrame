/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.al.frame.frame;

import org.al.frame.frameTocken.defaultFrameTocken.defaultFrameTockenException.InexistantKeyException;
import java.util.HashMap;

/**
 *
 * @author rahimAdmin
 */
public  class ALFrameCommands {

    private static ALFrameCommands bFrameCommand;
    private HashMap<String, byte[]> commandsHashMap;
    
    private ALFrameCommands() {
        commandsHashMap = new HashMap<>();
    }

    public static ALFrameCommands getInstance() {
        if (bFrameCommand == null) {
            bFrameCommand = new ALFrameCommands();
        }
        return bFrameCommand;
    }

    public HashMap<String, byte[]> getCommandsHashMap() {
        return new HashMap<String, byte[]>(this.commandsHashMap);
    }

    public void addCommand(String key, String command) {
        this.commandsHashMap.put(key, command.getBytes());
    }

    public void addCommand(String key, byte[] command) {
        this.commandsHashMap.put(key, command);
    }

    public byte[] getCommand(String key) throws   InexistantKeyException{
        byte[] command = commandsHashMap.get(key);
        if (command != null) {
            return command;
        } else {
            throw new InexistantKeyException();
        }
    }

}
