package org.varioml.generator;

import java.util.Date;

import org.simpleframework.xml.transform.Matcher;
import org.simpleframework.xml.transform.Transform;

public class VarioTypeMatcher  implements Matcher {

	@Override
    @SuppressWarnings("unchecked")
    public Transform match(Class type) throws Exception {
        if (type.equals(VarioDate.class))
            return new DateTransform();
        else if ( type.equals(VarioDateTime.class)) {
        	return new DateTimeTransform();
        }
        return null;
    }

}
