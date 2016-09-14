package org.cxio.aspects.datamodels;

import java.io.IOException;
import java.util.List;

import org.cxio.aspects.writers.WriterUtil;
import org.cxio.util.CxConstants;
import org.cxio.util.JsonWriter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;

/**
 *
 * This is the base class for EdgeAttributeElement, NodeAttributeElement, and NetworkAttributesElement.
 *
 * @author cmzmasek
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)

public abstract class AbstractAttributesAspectElement extends AbstractAspectElement {

    /** The name of this attribute. */
    public final static String ATTR_NAME        = "n";

    /** The node or edge this attribute is a property of. */
    public final static String ATTR_PROPERTY_OF = "po";

    /** The subnetwork this attribute belongs to. */
    public final static String ATTR_SUBNETWORK  = "s";

    /** The data type of this attribute (either atomic or list). */
    public final static String ATTR_DATA_TYPE   = "d";

    /** The value(s) of this attribute. */
    public final static String ATTR_VALUES      = "v";

	@JsonProperty(ATTR_NAME)
    String                     _name;
	
	@JsonProperty(ATTR_PROPERTY_OF)
    Long                 _property_of;
	
	@JsonProperty(ATTR_SUBNETWORK)
    Long                       _subnetwork;

	@JsonProperty(ATTR_DATA_TYPE)
    ATTRIBUTE_DATA_TYPE        _data_type;

	@JsonProperty(ATTR_VALUES )
    Object               _values;
  //  boolean                    _is_single_value;

    /**
     * This is for getting the name of the attribute.
     *
     * @return the name of the attribute
     */
    public final String getName() {
        return _name;
    }

    /**
     * This returns a list of identifiers of the elements this attribute is a property of.
     *
     * @return a list of identifiers of the elements this attribute is a property o
     */
    public Long getPropertyOf() {
        return _property_of;
    }

    /**
     * This returns the identifier of the subnetwork this attribute belongs to.
     *
     * @return the identifier of the subnetwork this attribute belongs to
     */
    public final Long getSubnetwork() {
        return _subnetwork;
    }

    /**
     * This returns the data type of the attribute.
     *
     *
     * @return the data type of the attribute
     */
    public final ATTRIBUTE_DATA_TYPE getDataType() {
        return _data_type;
    }

    /**
     * This returns the list values of the (list) attribute as list of Strings.
     *
     * @return the list values of the (list) attribute as list of Strings
     */
    public final List<String> getValues() {
    	if ( _values == null) return null;
        if (_values instanceof String) {
            throw new IllegalStateException("attempt to return single value as list of values");
        }
        return (List<String>)_values;
    }

    /**
     * This returns the value of the (single) attribute as String.
     *
     * @return the value of the (single) attribute as String
     */
    public final String getValue() {
    	if (_values == null) return null;
    	
        if (! (_values instanceof String)) {
            throw new IllegalStateException("attempt to return list of values as single value");
        }
        return ( String) _values;
    }

    /**
     * This returns true if the value of this attribute is a single value,
     * false if it is a list of values (even if the list just contains one value).
     *
     *
     * @return true if single value, false if list of values
     */
   public final boolean isSingleValue() {
	  return ATTRIBUTE_DATA_TYPE.isSingleValueType(this._data_type) ;
      //  return (_values == null) || (_values instanceof String) ;
    } 
    
    
    public void setPropertyOf(Long id) {
    	this._property_of = id;
    }
    

    @Override
	public void write(JsonWriter w) throws IOException {
        WriterUtil.writeAttributesElement(w,  this, null, true);		
        w.flush();
	}
    
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getAspectName());
        sb.append(": ");
        sb.append("\n");
        sb.append("edges: ");
        sb.append(_property_of);
        sb.append("\n");
        if (_subnetwork != null) {
            sb.append("subnetwork       : ");
            sb.append(_subnetwork);
            sb.append("\n");
        }
        sb.append("name             : ");
        sb.append(_name);
        sb.append("\n");
        if (isSingleValue()) {
            sb.append("value            : ");
            sb.append(getValue());
        }
        else {
            sb.append("values           : ");
            sb.append(_values);
        }
        sb.append("\n");
        sb.append("data type        : ");
        sb.append(_data_type.toString());
        sb.append("\n");
        return sb.toString();
    }

}