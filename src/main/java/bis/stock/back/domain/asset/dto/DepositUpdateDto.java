package bis.stock.back.domain.asset.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DepositUpdateDto {
	private String user_id;
	private String contents;
	private Long amount;
	private java.util.Date date;

	@Builder
	public DepositUpdateDto(String user_id, String contents, Long amount, Date date) {
		this.user_id = user_id;
		this.contents = contents;
		this.amount = amount;
		this.date = date;
	}
}
