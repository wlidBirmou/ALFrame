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
import org.joda.time.LocalDate;

/**
 *
 * @author rahimAdmin
 */
public class DateFrameTocken extends DefaultFrameTocken implements Serializable {

    private static final long serialVersionUID = 21127;
 
    private LocalDate localDate;
    private String dateDelimiter;
    private DatePattern datePattern;

    public DateFrameTocken(String dateFrameTocken, DateFrameTocken.DatePattern datePattern, String dateDelimiter) throws IrregularPatternException {
        super();
        this.datePattern = datePattern;
        this.dateDelimiter=dateDelimiter;
        this.setFrameTockenString(dateFrameTocken);
    }

    public DateFrameTocken(String dateFrameTocken, DateFrameTocken.DatePattern datePattern) throws IrregularPatternException {
        this(dateFrameTocken, datePattern, "-");
    }

    @Override
    public Object getFrameTockenValue() {
        return this.localDate;
    }

    @Override
    public void setFrameTockenString(String frameTockenString) throws IrregularPatternException {
        if (!frameTockenString.equals("")) {
            Pattern pattern=Pattern.compile("[0-9]{2}" + dateDelimiter + "[0-9]{2}" + dateDelimiter + "[0-9]{2,4}");
            Matcher matcher=pattern.matcher(frameTockenString);
            matcher.find();
            String dateString = matcher.group();
            String[] dateArrayString = dateString.split(this.dateDelimiter);

            if (dateArrayString.length != 3) {
                throw new IrregularPatternException();
            }

            switch (datePattern) {
                case YMD:
                    this.localDate = new LocalDate(Integer.parseInt("20" + dateArrayString[0]), Integer.parseInt(dateArrayString[1]), Integer.parseInt(dateArrayString[2]));
                    break;
                case YDM:
                    this.localDate = new LocalDate(Integer.parseInt(dateArrayString[0]), Integer.parseInt("20" + dateArrayString[2]), Integer.parseInt(dateArrayString[1]));
                    break;
                case MYD:
                    this.localDate = new LocalDate(Integer.parseInt(dateArrayString[1]), Integer.parseInt(dateArrayString[0]), Integer.parseInt("20" + dateArrayString[2]));
                    break;
                case MDY:
                    this.localDate = new LocalDate(Integer.parseInt("20" + dateArrayString[2]), Integer.parseInt(dateArrayString[0]), Integer.parseInt(dateArrayString[1]));
                    break;
                case DYM:
                    this.localDate = new LocalDate(Integer.parseInt(dateArrayString[1]), Integer.parseInt("20" + dateArrayString[2]), Integer.parseInt(dateArrayString[0]));
                    break;
                case DMY:
                    this.localDate = new LocalDate(Integer.parseInt("20" + dateArrayString[2]), Integer.parseInt(dateArrayString[1]), Integer.parseInt(dateArrayString[0]));
                    break;
            }
            this.frameTockenString = localDate.toString("dd" + this.dateDelimiter + "mm+" + this.dateDelimiter + "yyyy");
        } else {
            this.frameTockenString = "";
            this.localDate = null;
            this.dateDelimiter = "-";
        }
    }

    public enum DatePattern {
        DMY, DYM, MDY, MYD, YMD, YDM;
    }
}
