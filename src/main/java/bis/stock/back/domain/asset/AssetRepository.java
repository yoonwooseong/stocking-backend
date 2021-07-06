package bis.stock.back.domain.asset;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Long> {

    Optional<Asset> findByEmail(String email);
}
