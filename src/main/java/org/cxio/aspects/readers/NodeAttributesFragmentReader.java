package org.cxio.aspects.readers;

import java.io.IOException;

import org.cxio.aspects.datamodels.ATTRIBUTE_DATA_TYPE;
import org.cxio.aspects.datamodels.AbstractAttributesAspectElement;
import org.cxio.aspects.datamodels.AttributesAspectUtils;
import org.cxio.aspects.datamodels.NodeAttributesElement;
import org.cxio.core.interfaces.AspectElement;

import com.fasterxml.jackson.databind.node.ObjectNode;

public final class NodeAttributesFragmentReader extends AbstractFragmentReader {

    public static NodeAttributesFragmentReader createInstance() {
        return new NodeAttributesFragmentReader();
    }

    private NodeAttributesFragmentReader() {
        super();
    }

    @Override
    public final String getAspectName() {
        return NodeAttributesElement.ASPECT_NAME;
    }

    @Override
    public final AspectElement readElement(final ObjectNode o) throws IOException {
        ATTRIBUTE_DATA_TYPE type = ATTRIBUTE_DATA_TYPE.STRING;
        if (o.has(AbstractAttributesAspectElement.ATTR_DATA_TYPE)) {
            type = AttributesAspectUtils.toDataType(ParserUtils.getTextValueRequired(o, AbstractAttributesAspectElement.ATTR_DATA_TYPE));
        }
        if (ParserUtils.isArray(o, AbstractAttributesAspectElement.ATTR_VALUES)) {
            if (o.has(AbstractAttributesAspectElement.ATTR_SUBNETWORK)) {
                return new NodeAttributesElement(ParserUtils.getTextValueAsLong(o, AbstractAttributesAspectElement.ATTR_SUBNETWORK),
                                                 ParserUtils.getTextValueAsLong(o, NodeAttributesElement.ATTR_PROPERTY_OF),
                                                 ParserUtils.getTextValueRequired(o, AbstractAttributesAspectElement.ATTR_NAME),
                                                 ParserUtils.getAsStringList(o, AbstractAttributesAspectElement.ATTR_VALUES),
                                                 type);

            }
            else {
                return new NodeAttributesElement(ParserUtils.getTextValueAsLong(o, NodeAttributesElement.ATTR_PROPERTY_OF),
                                                 ParserUtils.getTextValueRequired(o, AbstractAttributesAspectElement.ATTR_NAME),
                                                 ParserUtils.getAsStringList(o, AbstractAttributesAspectElement.ATTR_VALUES),
                                                 type);
            }
        }
        if (o.has(AbstractAttributesAspectElement.ATTR_SUBNETWORK)) {
            return new NodeAttributesElement(ParserUtils.getTextValueAsLong(o, AbstractAttributesAspectElement.ATTR_SUBNETWORK),
                                             ParserUtils.getTextValueAsLong(o, NodeAttributesElement.ATTR_PROPERTY_OF),
                                             ParserUtils.getTextValueRequired(o, AbstractAttributesAspectElement.ATTR_NAME),
                                             ParserUtils.getTextValue(o, AbstractAttributesAspectElement.ATTR_VALUES),
                                             type);
        }
        else {
            return new NodeAttributesElement(ParserUtils.getTextValueAsLong(o, NodeAttributesElement.ATTR_PROPERTY_OF),
                                             ParserUtils.getTextValueRequired(o, AbstractAttributesAspectElement.ATTR_NAME),
                                             ParserUtils.getTextValue(o, AbstractAttributesAspectElement.ATTR_VALUES),
                                             type);
        }
    }

}
