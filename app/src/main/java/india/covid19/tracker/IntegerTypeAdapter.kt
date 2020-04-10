package india.covid19.tracker

import com.google.gson.JsonSyntaxException
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException

/**
 * @author shinilms
 */

class IntegerTypeAdapter : TypeAdapter<Number?>() {
    @Throws(IOException::class)
    override fun write(
        jsonWriter: JsonWriter,
        number: Number?
    ) {
        if (number == null) {
            jsonWriter.nullValue()
            return
        }
        jsonWriter.value(number)
    }

    @Throws(IOException::class)
    override fun read(jsonReader: JsonReader): Number? {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull()
            return null
        }
        return try {
            val value = jsonReader.nextString()
            if ("" == value) {
                0
            } else value.toInt()
        } catch (e: NumberFormatException) {
            throw JsonSyntaxException(e)
        }
    }
}