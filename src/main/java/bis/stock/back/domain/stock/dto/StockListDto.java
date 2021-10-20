package bis.stock.back.domain.stock.dto;

import lombok.Getter;

@Getter
public class StockListDto {

    private String name;
    private String code;
    private String category;
    private String market;

    public StockListDto(Stock stock) {
        this.name = stock.getName();
        this.code = stock.getCode();
        this.category = stock.getCategory();
        this.market = stock.getMarketName();
    }
}
