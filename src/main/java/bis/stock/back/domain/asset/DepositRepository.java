package bis.stock.back.domain.asset;

import org.springframework.data.jpa.repository.JpaRepository;

import bis.stock.back.domain.asset.dto.Deposit;

public interface DepositRepository extends JpaRepository<Deposit, Long>{

}
