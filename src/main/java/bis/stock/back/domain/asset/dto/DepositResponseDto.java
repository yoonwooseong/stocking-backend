package bis.stock.back.domain.asset.dto;

import lombok.Getter;

@Getter
public class DepositResponseDto {
	private Long id;
	private String user_id;
	private String contents;
	private Long amount;
	private java.util.Date date;

	public DepositResponseDto(Deposit entity) {
		this.id = entity.getId();
		this.user_id = entity.getUser_id();
		this.contents = entity.getContents();
		this.amount = entity.getAmount();
		this.date = entity.getDate();
	}
}
