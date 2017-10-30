/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.al.frame.frameTocken.defaultFrameTocken;

import java.io.Serializable;
import org.al.frame.frameTocken.defaultFrameTocken.defaultFrameTockenException.IrregularPatternException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author rahimAdmin
 */
public class BitSetFrameTocken extends DefaultFrameTocken implements Serializable {

    private static final long serialVersionUID = 2116;

    private BitSet bitSetVector;
    private HashMap<String, Integer> bitsetMapKeys;

    public BitSetFrameTocken(String bitSetFrameTockenString, BitSetFrameTocken.Type type) throws IrregularPatternException {
        super();
        this.bitsetMapKeys = new HashMap<>();
        this.setFrameTockenString(bitSetFrameTockenString, type);
    }

    @Override
    public Object getFrameTockenValue() {
        return bitSetVector.get(0, bitSetVector.length());
    }

    public boolean getValueAt(int index) {
        return bitSetVector.get(index);
    }

    @Override
    public void setFrameTockenString(String tockenString) throws IrregularPatternException {
        String binaryString = null;
        if (!tockenString.equals("")) {
            binaryString = Pattern.compile("[01]++").matcher(tockenString).group();
            this.setBitsetFromString(binaryString);
        } else {
            this.bitSetVector = null;
        }
        this.frameTockenString = tockenString;
    }

    public boolean getBitset(String key) {
        return this.bitSetVector.get(this.bitsetMapKeys.get(key));
    }

    public Integer putbitSetKey(String key, Integer index) {
        return bitsetMapKeys.put(key, index);
    }

    public Integer getbitSet(String key) {
        return bitsetMapKeys.get(key);
    }

    public void setFrameTockenString(String tockenString, BitSetFrameTocken.Type type) throws IrregularPatternException {

        switch (type) {
            case DECIMAL:
                this.setFrameTockenStringFromDecimalBase(tockenString);
                break;
            case BINARY:
                this.setFrameTockenString(tockenString);
                break;
            case HEXA:
                this.setFrameTockenStringFromHexa(tockenString);
                break;
        }
    }

    private void setFrameTockenStringFromHexa(String tockenString) throws IrregularPatternException {
        if (!tockenString.isEmpty()) {
            Matcher matcher = Pattern.compile("[0-9a-fA-F]++").matcher(tockenString);
            matcher.find();
            String hexaString = matcher.group();
            Integer hexaNumber = Integer.parseInt(hexaString, 16);
            hexaString = Integer.toBinaryString(hexaNumber);
            int rest = (4 - hexaString.length() % 4) % 4;
            while (rest > 0) {
                hexaString = "0" + hexaString;
                rest--;
            }
            this.setBitsetFromString(hexaString);
        } else {
            this.bitSetVector = null;
        }
        this.frameTockenString = tockenString;
    }

    private void setFrameTockenStringFromDecimalBase(String tockenString) throws IrregularPatternException {
        if (!tockenString.isEmpty()) {

            String decimalString = Pattern.compile("[0-9]++").matcher(tockenString).group();
            int intNumber = Integer.parseInt(decimalString);
            decimalString = Integer.toBinaryString(intNumber);
            this.setBitsetFromString(decimalString);
        } else {
            this.bitSetVector = null;
        }
        this.frameTockenString = tockenString;

    }

    private void setBitsetFromString(String tockenString) {
        this.bitSetVector = new BitSet(tockenString.length());
        for (int i = 0; i < tockenString.length(); i++) {
            if (tockenString.charAt(i) != '0' && tockenString.charAt(i) != '1') {
                throw new IrregularPatternException();
            } else {
                if (tockenString.charAt(i) == '1') {
                    this.bitSetVector.set(i, true);
                }
            }
        }
    }

    public enum Type {
        DECIMAL, BINARY, HEXA;
    }

}
