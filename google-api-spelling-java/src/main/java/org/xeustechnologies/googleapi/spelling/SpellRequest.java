/**
 * Copyright 2009 Xeus Technologies 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 * 
 */

package org.xeustechnologies.googleapi.spelling;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Kamran Zafar
 * 
 */
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
