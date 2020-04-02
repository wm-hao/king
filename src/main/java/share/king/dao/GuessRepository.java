package share.king.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import share.king.entity.GuessEntity;

@Repository
public interface GuessRepository extends JpaRepository<GuessEntity, Integer> {
}
