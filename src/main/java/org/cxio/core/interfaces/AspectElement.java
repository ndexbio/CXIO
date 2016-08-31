package org.cxio.core.interfaces;

import java.io.IOException;

import org.cxio.util.JsonWriter;

import com.fasterxml.jackson.core.JsonGenerator;

/**
 * The interface for all (named) AspectElements.
 *
 * @author cmzmasek
 *
 */
public interface AspectElement extends Comparable<AspectElement> {

    /**
     * This returns the name of the aspect.
     *
     * @return the name of the aspect
     */
    public String getAspectName();
    
    public abstract void write(JsonWriter out) throws IOException ;


}
