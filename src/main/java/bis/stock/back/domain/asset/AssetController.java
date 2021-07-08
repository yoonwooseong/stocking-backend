package bis.stock.back.domain.asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bis.stock.back.domain.asset.dto.DepositResponseDto;
import bis.stock.back.domain.asset.dto.DepositSaveDto;
import bis.stock.back.domain.asset.dto.DepositUpdateDto;
import bis.stock.back.domain.asset.dto.WithdrawResponseDto;
import bis.stock.back.domain.asset.dto.WithdrawSaveDto;
import bis.stock.back.domain.asset.dto.WithdrawUpdateDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/asset")
@RestController
public class AssetController {

	private final DepositService depositService;
	private final WithdrawService withdrawService;

	@PostMapping("/asset/deposit/post")
	public Long insert(@RequestBody DepositSaveDto requestDto) {
		return depositService.save(requestDto);
	}
	@PutMapping("/asset/deposit/{id}")
	public Long update(@PathVariable Long id, @RequestBody DepositUpdateDto requestDto) {
		return depositService.update(id, requestDto);
	}
	@GetMapping("/asset/deposit/{id}")
	public DepositResponseDto findById(@PathVariable Long id) {
		return depositService.findById(id);
	}

	@PostMapping("/asset/withdraw/post")
	public Long insert(@RequestBody WithdrawSaveDto requestDto) {
		return withdrawService.save(requestDto);
	}
	@PutMapping("/asset/withdraw/{id}")
	public Long update(@PathVariable Long id, @RequestBody WithdrawUpdateDto requestDto) {
		return withdrawService.update(id, requestDto);
	}
	@GetMapping("/asset/withdraw/{id}")
	public WithdrawResponseDto WithdrawfindById(@PathVariable Long id) {
		return withdrawService.findById(id);
	}
}
