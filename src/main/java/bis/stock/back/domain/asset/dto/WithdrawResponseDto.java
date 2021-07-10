package bis.stock.back.domain.asset.dto;

import lombok.Getter;

@Getter
public class WithdrawResponseDto {
	private Long id;
	private String user_id;
	private String contents;
	private Long amount;
	private boolean fixed;
	private java.util.Date date;

	public WithdrawResponseDto(Withdraw entity) {
		this.id = entity.getId();
		this.user_id = entity.getUser_id();
		this.contents = entity.getContents();
		this.amount = entity.getAmount();
		this.date = entity.getDate();
		//this.fixed = entity.get
		//error
	}
}
