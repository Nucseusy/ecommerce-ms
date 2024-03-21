package br.com.yesv.capitoleproductms.adapters.in.controller;

import br.com.yesv.capitoleproductms.domain.model.Price;
import br.com.yesv.capitoleproductms.domain.ports.in.FindPriceInputPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FindPriceInputPort findPriceInputPort;

    @Test
    void testPriceQueryAt10AmOn14th_thenReturnResourceNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/price")
                        .param("applicationDate", "2023-06-14T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testPriceQueryAt10AmOn14th() throws Exception {
        Mockito.when(findPriceInputPort.find(anyInt(), anyInt(), any())).thenReturn(Price.builder()
                .productId(35455)
                .brandId(1)
                .startApplicationDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endApplicationDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .priceList(1)
                .currency("EUR")
                .finalPrice(35.5)
                .build());
        mockMvc.perform(MockMvcRequestBuilders.get("/product/price")
                        .param("applicationDate", "2020-06-14 10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("{" +
                        "'productId': 35455," +
                        "'brandId': 1," +
                        "'startApplicationDate': '2020-06-14 00:00:00'," +
                        "'endApplicationDate': '2020-12-31 23:59:59'," +
                        "'priceList': 1," +
                        "'finalPrice': 35.50" +
                        "}"));

    }

    @Test
    void testPriceQueryAt4PmOn14th() throws Exception {
        Mockito.when(findPriceInputPort.find(anyInt(), anyInt(), any())).thenReturn(Price.builder()
                .productId(35455)
                .brandId(1)
                .startApplicationDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endApplicationDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .priceList(1)
                .currency("EUR")
                .finalPrice(35.5)
                .build());

        mockMvc.perform(MockMvcRequestBuilders.get("/product/price")
                        .param("applicationDate", "2020-06-14 16:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("{" +
                        "'productId': 35455," +
                        "'brandId': 1," +
                        "'startApplicationDate': '2020-06-14 00:00:00'," +
                        "'endApplicationDate': '2020-12-31 23:59:59'," +
                        "'priceList': 1," +
                        "'finalPrice': 35.50" +
                        "}"));
    }

    @Test
    void testPriceQueryAt9PmOn14th() throws Exception {
        Mockito.when(findPriceInputPort.find(anyInt(), anyInt(), any())).thenReturn(Price.builder()
                .productId(35455)
                .brandId(1)
                .startApplicationDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endApplicationDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .priceList(1)
                .currency("EUR")
                .finalPrice(35.5)
                .build());

        mockMvc.perform(MockMvcRequestBuilders.get("/product/price")
                        .param("applicationDate", "2020-06-14 21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("{" +
                        "'productId': 35455," +
                        "'brandId': 1," +
                        "'startApplicationDate': '2020-06-14 00:00:00'," +
                        "'endApplicationDate': '2020-12-31 23:59:59'," +
                        "'priceList': 1," +
                        "'finalPrice': 35.50" +
                        "}"));
    }

    @Test
    void testPriceQueryAt10AmOn15th() throws Exception {
        Mockito.when(findPriceInputPort.find(anyInt(), anyInt(), any())).thenReturn(Price.builder()
                .productId(35455)
                .brandId(1)
                .startApplicationDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0))
                .endApplicationDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0))
                .priceList(3)
                .currency("EUR")
                .finalPrice(30.50)
                .build());

        mockMvc.perform(MockMvcRequestBuilders.get("/product/price")
                        .param("applicationDate", "2020-06-15 10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("{" +
                        "'productId': 35455," +
                        "'brandId': 1," +
                        "'startApplicationDate': '2020-06-15 00:00:00'," +
                        "'endApplicationDate': '2020-06-15 11:00:00'," +
                        "'priceList': 3," +
                        "'finalPrice': 30.50" +
                        "}"));
    }

    @Test
    void testPriceQueryAt9PmOn16th() throws Exception {
        Mockito.when(findPriceInputPort.find(anyInt(), anyInt(), any())).thenReturn(Price.builder()
                .productId(35455)
                .brandId(1)
                .startApplicationDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0))
                .endApplicationDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .priceList(4)
                .currency("EUR")
                .finalPrice(38.95)
                .build());

        mockMvc.perform(MockMvcRequestBuilders.get("/product/price")
                        .param("applicationDate", "2023-06-16 21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("{" +
                        "'productId': 35455," +
                        "'brandId': 1," +
                        "'startApplicationDate': '2020-06-15 16:00:00'," +
                        "'endApplicationDate': '2020-12-31 23:59:59'," +
                        "'priceList': 4," +
                        "'finalPrice': 38.95" +
                        "}"));
    }
}