package br.com.yesv.ecommercems.domain.usecase;

import br.com.yesv.ecommercems.domain.model.Price;
import br.com.yesv.ecommercems.domain.ports.out.FindPriceOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {FindPriceUseCase.class})
@ExtendWith(SpringExtension.class)
class FindPriceUseCaseTest {
    @MockBean
    private FindPriceOutputPort findPriceOutputPort;

    @Autowired
    private FindPriceUseCase findPriceUseCase;

    @Test
    void testFind() {
        var currencyResult = Price.builder().brandId(1).currency("EUR");
        var productIdResult = currencyResult.endApplicationDate(LocalDate.of(2020, 1, 1).atStartOfDay())
                .finalPrice(10.0d)
                .priceList(1)
                .productId(1);
        var buildResult = productIdResult.startApplicationDate(LocalDate.of(2020, 1, 1).atStartOfDay()).build();
        when(findPriceOutputPort.find(anyInt(), anyInt(), any()))
                .thenReturn(Optional.of(buildResult));

        var actualFindResult = findPriceUseCase.find(1, 1, LocalDate.of(2020, 1, 1).atStartOfDay());

        verify(findPriceOutputPort).find(anyInt(), anyInt(), any());
        LocalTime expectedToLocalTimeResult = actualFindResult.get().getStartApplicationDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualFindResult.get().getEndApplicationDate().toLocalTime());
    }
}
