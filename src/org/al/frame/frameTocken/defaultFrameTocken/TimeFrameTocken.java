/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.al.frame.frameTocken.defaultFrameTocken;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.al.frame.frameTocken.defaultFrameTocken.defaultFrameTockenException.IrregularPatternException;
import org.joda.time.LocalTime;

/**
 *
 * @author rahimAdmin
 */
public class TimeFrameTocken extends DefaultFrameTocken implements Serializable {

    private static final long serialVersionUID = 21125;

    private LocalTime localTime;
    private String timeDelimiter;
    private TimePattern timePattern;

    public TimeFrameTocken(String timeFrameTocken, TimeFrameTocken.TimePattern timePattern) throws IrregularPatternException {
        this(timeFrameTocken, timePattern, ":");
    }

    public TimeFrameTocken(String timeFrameTocken, TimeFrameTocken.TimePattern timePattern, String timeDelimiter) throws IrregularPatternException {
        super();
        this.timeDelimiter = timeDelimiter;
        this.timePattern = timePattern;
        this.setFrameTockenString(timeFrameTocken);
    }

    @Override
    public Object getFrameTockenValue() {
        return this.localTime;

    }

    @Override
    public void setFrameTockenString(String frameTockenString) throws IrregularPatternException {

        if (!frameTockenString.isEmpty()) {
            Pattern pattern = Pattern.compile("[0-9]{2}" + timeDelimiter + "[0-9]{2}" + timeDelimiter + "[0-9]{2}");
            Matcher matcher = pattern.matcher(frameTockenString);
            matcher.find();
            String timeString = matcher.group();

            String[] timeArrayString = timeString.split(this.timeDelimiter);

            if (timeArrayString.length != 3) {
                throw new IrregularPatternException();
            }

            switch (timePattern) {
                case HMS:
                    this.localTime = new LocalTime(Integer.parseInt(timeArrayString[0]), Integer.parseInt(timeArrayString[1]), Integer.parseInt(timeArrayString[2]));
                    break;
                case HSM:
                    this.localTime = new LocalTime(Integer.parseInt(timeArrayString[0]), Integer.parseInt(timeArrayString[2]), Integer.parseInt(timeArrayString[1]));
                    break;
                case MHS:
                    this.localTime = new LocalTime(Integer.parseInt(timeArrayString[1]), Integer.parseInt(timeArrayString[0]), Integer.parseInt(timeArrayString[2]));
                    break;
                case MSH:
                    this.localTime = new LocalTime(Integer.parseInt(timeArrayString[2]), Integer.parseInt(timeArrayString[0]), Integer.parseInt(timeArrayString[1]));
                    break;
                case SHM:
                    this.localTime = new LocalTime(Integer.parseInt(timeArrayString[1]), Integer.parseInt(timeArrayString[2]), Integer.parseInt(timeArrayString[0]));
                    break;
                case SMH:
                    this.localTime = new LocalTime(Integer.parseInt(timeArrayString[2]), Integer.parseInt(timeArrayString[1]), Integer.parseInt(timeArrayString[0]));
                    break;
            }
            this.frameTockenString = localTime.toString("hh" + this.timeDelimiter + "MM" + this.timeDelimiter + "SS");
        } else {
            this.frameTockenString = "";
            this.timeDelimiter = ":";
            this.localTime = null;
        }

    }

    public enum TimePattern {
        HMS, HSM, MSH, MHS, SHM, SMH;
    }

}
