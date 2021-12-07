package bis.stock.back.domain.asset;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import bis.stock.back.domain.asset.dto.Withdraw;
import org.springframework.data.jpa.repository.Query;

public interface WithdrawRepository extends JpaRepository<Withdraw, Long>{
	List<Withdraw> findAllById(Long user_id);

//	@Query("select w.username from Withdraw w")
//	List<String> findUsernameList();
}
