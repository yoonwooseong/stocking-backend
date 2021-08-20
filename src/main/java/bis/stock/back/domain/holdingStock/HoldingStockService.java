package bis.stock.back.domain.holdingStock;

import bis.stock.back.domain.holdingStock.dto.StockDto;
import bis.stock.back.domain.holdingStock.dto.StockResponseDto;
import bis.stock.back.domain.user.User;
import bis.stock.back.domain.user.UserRepository;
import bis.stock.back.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HoldingStockService {

    private final HoldingStockRepository holdingStockRepository;
    private final UserRepository userRepository;

    public StockDto stockSave(Long userId, StockDto stockDto, boolean isBuy) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("유저가 존재하지 않습니다."));

        BuyOrSell action = isBuy ? BuyOrSell.BUY : BuyOrSell.SELL;

        holdingStockRepository.save(HoldingStock.builder()
                .buyOrSell(action)
                .amount(stockDto.getAmount())
                .price(stockDto.getPrice())
                .date(stockDto.getDate())
                .stockCode(stockDto.getCode())
                .user(user)
                .build());

        return stockDto;
    }

    public List<StockResponseDto> tradeList(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("유저가 존재하지 않습니다."));

        List<StockResponseDto> stockList = new ArrayList<>();
        for(HoldingStock entity : user.getHoldingStocks()) {
            stockList.add(new StockResponseDto(entity));
        }
        return stockList;
    }
}
