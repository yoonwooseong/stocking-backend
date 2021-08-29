package bis.stock.back.domain.stock;

import bis.stock.back.domain.stock.dto.Stock;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Builder
@RequiredArgsConstructor
public class StockPrice {

    @OneToOne(mappedBy = "stockPrice")
    private Stock stock;

    private int price;
}
