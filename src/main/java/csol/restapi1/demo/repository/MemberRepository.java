package csol.restapi1.demo.repository;

import csol.restapi1.demo.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
