package org.xeustechnologies.googleapi.spelling;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "spellresult")
public class SpellResponse {

    protected int error = 0;

    protected int clipped = 0;

    protected int charsChecked = 0;

    protected SpellCorrection[] corrections;

    @XmlAttribute(name = "error", required = true)
    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    @XmlAttribute(name = "clipped")
    public int getClipped() {
        return clipped;
    }

    public void setClipped(int clipped) {
        this.clipped = clipped;
    }

    @XmlAttribute(name = "charschecked")
    public int getCharsChecked() {
        return charsChecked;
    }

    public void setCharsChecked(int charsChecked) {
        this.charsChecked = charsChecked;
    }

    @XmlElement(name = "c")
    public SpellCorrection[] getCorrections() {
        return corrections;
    }

    public void setCorrections(SpellCorrection[] corrections) {
        this.corrections = corrections;
    }
}
