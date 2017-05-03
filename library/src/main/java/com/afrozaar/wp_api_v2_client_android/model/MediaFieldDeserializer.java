package com.afrozaar.wp_api_v2_client_android.model;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @author Jan-Louis Crafford
 *         Created on 2017/05/02.
 */

public class MediaFieldDeserializer extends TypeAdapter<WPGeneric> {

    @Override
    public void write(JsonWriter out, WPGeneric value) throws IOException {

    }

    @Override
    public WPGeneric read(JsonReader in) throws IOException {
        WPGeneric generic = new WPGeneric();

        if (in.peek() == JsonToken.STRING) {
            String value = in.nextString();
            generic.withRaw(value)
                    .withRendered(value);
        } else if (in.peek() == JsonToken.BEGIN_OBJECT) {
            in.beginObject();
            while (in.hasNext()) {
                String name = in.nextName();
                if (name.equals("rendered")) {
                    generic.withRendered(in.nextString());
                } else if (name.equals("raw")) {
                    generic.withRaw(in.nextString());
                } else {
                    in.skipValue();
                }
            }
            in.endObject();
        } else {
            return null;
        }

        return generic;
    }
}
