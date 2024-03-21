package br.com.yesv.ecommercems.adapters.in.controller.common.serialization;

import com.fasterxml.jackson.core.util.JsonParserDelegate;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = {LocalDateTimeDeserializer.class})
@ExtendWith(SpringExtension.class)
class LocalDateTimeDeserializerTest {
    @Autowired
    private LocalDateTimeDeserializer localDateTimeDeserializer;

    @MockBean
    JsonParserDelegate jsonParserDelegate;

    @Test
    void testDeserialize() throws IOException {
        Mockito.when(jsonParserDelegate.getValueAsString()).thenReturn("2020-06-15 10:00:00");

        var result = localDateTimeDeserializer.deserialize(jsonParserDelegate,
                new DefaultDeserializationContext.Impl(new BeanDeserializerFactory(new DeserializerFactoryConfig())));
        assertEquals(LocalDateTime.of(2020, 6, 15, 10, 0, 0), result);
    }
}
