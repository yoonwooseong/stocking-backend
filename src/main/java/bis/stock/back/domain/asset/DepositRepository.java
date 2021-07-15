package bis.stock.back.domain.asset;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bis.stock.back.domain.asset.dto.Deposit;
import bis.stock.back.domain.user.User;

public interface DepositRepository extends JpaRepository<Deposit, Long>{
	Optional<User> findById(String user_id);
}
