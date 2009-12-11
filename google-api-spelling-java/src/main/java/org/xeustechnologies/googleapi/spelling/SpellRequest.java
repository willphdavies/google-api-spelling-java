package org.xeustechnologies.googleapi.spelling;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "spellrequest")
public class SpellRequest {

    protected int textAlreadyClipped = 0;

    protected int ignoreDuplicates = 0;

    protected int ignoreWordsWithDigits = 1;

    protected int ignoreWordsWithAllCaps = 1;

    protected String text;

    public SpellRequest() {
    }

    public SpellRequest(String text) {
        this.text = text;
    }

    @XmlElement(name = "text", required = true)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @XmlAttribute(name = "textalreadyclipped")
    public int getTextAlreadyClipped() {
        return textAlreadyClipped;
    }

    @XmlAttribute(name = "ignoredups")
    public int getIgnoreDuplicates() {
        return ignoreDuplicates;
    }

    @XmlAttribute(name = "ignoredigits")
    public int getIgnoreWordsWithDigits() {
        return ignoreWordsWithDigits;
    }

    @XmlAttribute(name = "ignoreallcaps")
    public int getIgnoreWordsWithAllCaps() {
        return ignoreWordsWithAllCaps;
    }

    public void setTextAlreadyClipped(int textAlreadyClipped) {
        this.textAlreadyClipped = textAlreadyClipped;
    }

    public void setIgnoreDuplicates(int ignoreDuplicates) {
        this.ignoreDuplicates = ignoreDuplicates;
    }

    public void setIgnoreWordsWithDigits(int ignoreWordsWithDigits) {
        this.ignoreWordsWithDigits = ignoreWordsWithDigits;
    }

    public void setIgnoreWordsWithAllCaps(int ignoreWordsWithAllCaps) {
        this.ignoreWordsWithAllCaps = ignoreWordsWithAllCaps;
    }
}
