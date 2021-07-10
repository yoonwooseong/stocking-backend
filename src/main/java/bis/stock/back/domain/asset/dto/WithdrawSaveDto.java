package bis.stock.back.domain.asset.dto;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WithdrawSaveDto {

	private String user_id;
	private String contents;
	private Long amount;
	private java.util.Date date;
	private boolean fixed;

	@Builder
	public WithdrawSaveDto(String user_id, String contents, Long amount, Date date, boolean fixed) {
		this.user_id = user_id;
		this.contents = contents;
		this.amount = amount;
		this.date = date;
		this.fixed = fixed;
	}

	public Withdraw toEntity() {
		return Withdraw.builder()
				.user_id(user_id)
				.contents(contents)
				.amount(amount)
				.date(date)
				.fixed(fixed)
				.build();
	}
}
