package vieira.pedro.gerenciadorsenhas.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.data.domain.Page;

import java.io.IOException;

@JsonComponent
public class PageSerializerConfig extends JsonObjectSerializer<Page<?>> {
    @Override
    protected void serializeObject(Page value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeObjectField("content", value.getContent());
        jgen.writeObjectField("pageNumber", value.getNumber());
        jgen.writeObjectField("numberOfElements", value.getNumberOfElements());
        jgen.writeObjectField("totalPages", value.getTotalPages());
        jgen.writeObjectField("size", value.getSize());
        jgen.writeObjectField("isFirts", value.isFirst());
        jgen.writeObjectField("isLast", value.isLast());
    }
}
