package bis.stock.back.domain.asset;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import bis.stock.back.domain.asset.dto.Withdraw;
import bis.stock.back.domain.asset.dto.WithdrawResponseDto;
import bis.stock.back.domain.asset.dto.WithdrawSaveDto;
import bis.stock.back.domain.asset.dto.WithdrawUpdateDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WithdrawService {
	private final WithdrawRepository withdrawRepository;

	@Transactional
	public Long save(WithdrawSaveDto requestDto) {
		return withdrawRepository.save(requestDto.toEntity()).getId();
	}

	@Transactional
	public Long update(Long id, WithdrawUpdateDto requestDto) {
		Withdraw withdraw = withdrawRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("\"해당 id에 대한 정보가 존재하지 않습니다. id="+ id));

		withdraw.update(requestDto.getUser_id(), requestDto.getContents(), requestDto.getAmount(), requestDto.getDate());
		return id;
	}

	public WithdrawResponseDto findById(Long id) {
		Withdraw entity = withdrawRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id에 대한 정보가 존재하지 않습니다. id="+ id));

		return new WithdrawResponseDto(entity);
	}
}
