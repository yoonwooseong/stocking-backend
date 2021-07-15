package bis.stock.back.domain.asset.dto;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DepositSaveDto {
	//View layer와 DB layer는 철저히 분리하는게 좋아서 Entity와 Dto 둘다 존재

	private String user_id;
	private String contents;
	private Long amount;
	private java.util.Date date;

	@Builder
	public DepositSaveDto(String user_id, String contents, Long amount, Date date) {
		this.user_id = user_id;
		this.contents = contents;
		this.amount = amount;
		this.date = date;
	}

	public Deposit toEntity() {
		return Deposit.builder()
				.user_id(user_id)
				.contents(contents)
				.amount(amount)
				.date(date)
				.build();
	}
}
