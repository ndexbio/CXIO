package org.cxio.examples.custom_aspects;

import java.io.IOException;

import org.cxio.aspects.datamodels.AbstractAspectElement;
import org.cxio.util.CxioUtil;
import org.cxio.util.JsonWriter;

public final class ProfileElement extends AbstractAspectElement {

    public final static String NAME         = "profile";
    public final static String PROFILE_NAME = "name";
    public final static String PROFILE_DESC = "desc";
    private final String       _name;
    private final String       _desc;

    @Override
    public String getAspectName() {
        return NAME;
    }

    public ProfileElement(final String name, final String description) {
        if (CxioUtil.isEmpty(name)) {
            throw new IllegalArgumentException("profile name must not be null or empty");
        }
        if (CxioUtil.isEmpty(description)) {
            throw new IllegalArgumentException("profile description must not be null or empty");
        }
        _name = name;
        _desc = description;
    }

    public final String getName() {
        return _name;
    }

    public final String getDescription() {
        return _desc;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("name: ");
        sb.append(_name);
        sb.append("\n");
        sb.append("description: ");
        sb.append(_desc);
        return sb.toString();
    }

	@Override
	public void write(JsonWriter w) throws IOException {
        final ProfileElement e = this;
        w.writeStartObject();
        w.writeStringFieldIfNotEmpty(ProfileElement.PROFILE_NAME, e.getName());
        w.writeStringFieldIfNotEmpty(ProfileElement.PROFILE_DESC, e.getDescription());
        w.writeEndObject();	
        w.flush();
	}

}
