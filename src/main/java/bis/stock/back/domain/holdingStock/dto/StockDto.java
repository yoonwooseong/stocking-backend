package bis.stock.back.domain.holdingStock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class StockDto {

    private String code;
    private LocalDateTime date;
    private int price;
    private int amount;
}
