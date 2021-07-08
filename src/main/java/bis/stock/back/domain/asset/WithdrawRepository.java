package bis.stock.back.domain.asset;

import org.springframework.data.jpa.repository.JpaRepository;

import bis.stock.back.domain.asset.dto.Withdraw;

public interface WithdrawRepository extends JpaRepository<Withdraw, Long>{


}
