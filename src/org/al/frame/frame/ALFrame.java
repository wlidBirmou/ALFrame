/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.al.frame.frame;

import java.io.Serializable;
import java.util.BitSet;
import org.al.frame.frameTocken.FrameTocken;
import org.al.frame.frameTocken.defaultFrameTocken.defaultFrameTockenException.InexistantKeyException;
import java.util.HashMap;
import org.al.frame.frameTocken.defaultFrameTocken.BitSetFrameTocken;
import org.al.frame.frameTocken.defaultFrameTocken.DateFrameTocken;
import org.al.frame.frameTocken.defaultFrameTocken.DoubleFrameTocken;
import org.al.frame.frameTocken.defaultFrameTocken.StringFrameTocken;
import org.al.frame.frameTocken.defaultFrameTocken.TimeFrameTocken;
import org.al.frame.frameTocken.defaultFrameTocken.defaultFrameTockenException.IrregularKeyException;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 *
 * @author rahimAdmin
 */
public abstract class ALFrame implements Serializable {

    static final long serialVersionUID = 1111;

    private HashMap<String, FrameTocken> tockensHashMap;
    private String frameString = "";
    protected String delimiter = "|";

    public ALFrame(String frame, String delimiter) {
        this.tockensHashMap = new HashMap<String, FrameTocken>();
        this.delimiter = delimiter;
        this.setAndParseFrameString(frame);
    }

    public HashMap<String, FrameTocken> getTockensHashMap() {
        return new HashMap<>(tockensHashMap);
    }

    public void addTocken(FrameTocken frameTocken, String key) {
        this.tockensHashMap.put(key, frameTocken);
    }

    public final FrameTocken getTocken(String key) {
        FrameTocken frameTocken = this.tockensHashMap.get(key);
        if (frameTocken != null) {
            return frameTocken;
        } else {
            throw new InexistantKeyException();
        }
    }

    public final Double getDoubleFromTocken(String key) {
        FrameTocken frameTocken = this.tockensHashMap.get(key);
        if (frameTocken != null) {
            if (frameTocken instanceof DoubleFrameTocken) {
                return (Double) frameTocken.getFrameTockenValue();
            } else {
                throw new IrregularKeyException();
            }
        } else {
            throw new InexistantKeyException();
        }
    }

    public final String getStringFromTocken(String key) {
        FrameTocken frameTocken = this.tockensHashMap.get(key);
        if (frameTocken != null) {
            if (frameTocken instanceof StringFrameTocken) {
                return (String) frameTocken.getFrameTockenValue();
            } else {
                throw new IrregularKeyException();
            }
        } else {
            throw new InexistantKeyException("Key : " + key + " don't exist in the hash map " + this.tockensHashMap.get(key));
        }
    }

    public final LocalDate getDateFromTocken(String key) {
        FrameTocken frameTocken = this.tockensHashMap.get(key);
        if (frameTocken != null) {
            if (frameTocken instanceof DateFrameTocken) {
                return (LocalDate) frameTocken.getFrameTockenValue();
            } else {
                throw new IrregularKeyException();
            }
        } else {
            throw new InexistantKeyException();
        }
    }

    public final LocalTime getTimeFromTocken(String key) {
        FrameTocken frameTocken = this.tockensHashMap.get(key);
        if (frameTocken != null) {
            if (frameTocken instanceof TimeFrameTocken) {
                return (LocalTime) frameTocken.getFrameTockenValue();
            } else {
                throw new IrregularKeyException();
            }
        } else {
            throw new InexistantKeyException();
        }
    }

    public final BitSet getBitSet(String key) {
        FrameTocken frameTocken = this.tockensHashMap.get(key);
        if (frameTocken != null) {
            if (frameTocken instanceof BitSetFrameTocken) {
                return (BitSet) frameTocken.getFrameTockenValue();
            } else {
                throw new IrregularKeyException();
            }
        } else {
            throw new InexistantKeyException();
        }
    }

    public String getFrameString() {
        return new String(frameString);
    }

    public final void setAndParseFrameString(String frame) {
        this.frameString = frame;
        if (this.frameString.isEmpty()) {
            this.tockensHashMap.clear();
        } else {
            this.parseFrame(this.frameString.split(this.delimiter));
        }
    }

    protected abstract void parseFrame(String[] tockensArray);

}
