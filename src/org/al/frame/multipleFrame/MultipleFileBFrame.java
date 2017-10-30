/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.al.frame.multipleFrame;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author rahimAdmin
 */
public abstract class MultipleFileBFrame {

    private Path filePath;
    private RandomAccessFile randomAccessFile;
    private int position = 0;
    private ByteBuffer buffer;
    private char delimiter;

    public MultipleFileBFrame(Path filePath, char delimiter) {
        this.filePath = filePath;
        this.delimiter = delimiter;
    }

    public void doAction() throws FileNotFoundException, IOException {

        String frameString = "";
        long fileSize = Files.size(filePath);
        boolean markFlag = false;
        byte readedByte;
        char readedChar;

        randomAccessFile = new RandomAccessFile(this.filePath.toString(), "rw");
        this.buffer = ByteBuffer.allocate(2048);
        FileChannel channel = randomAccessFile.getChannel();

        while (channel.position() < fileSize) {
            channel.read(this.buffer);
            this.buffer.flip();
            while (this.buffer.hasRemaining()) {
                readedByte = buffer.get();
                readedChar =(char) (readedByte& 0xFF);
                if (readedChar != delimiter) {
                    frameString = frameString + readedChar;
                    if (markFlag) {
                        this.buffer.mark();
                        
                        markFlag = false;
                    }
                    if (!this.buffer.hasRemaining()) {
                        this.buffer.reset();
                        this.buffer.flip();
                        this.buffer.compact();
                        this.buffer.rewind();
                        break;
                    }
                } else {
                    if(frameString.length()<=6){
                        int i=0;
                    }
                    this.actionInEachFrame(frameString);
                    frameString = "";
                    markFlag = true;
                }
            }
        }
        channel.close();
        this.randomAccessFile.close();
        this.lastExecution();
        
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }

    public void setBuffer(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    public char getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }

    protected void lastExecution(){
        
    }
    
    protected abstract void actionInEachFrame(String string);
    
}
