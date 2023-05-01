package hjh.capstone.domain.member;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository
{
    Optional<Member> findById(Long memberId);

    Optional<Member> findByName(String memberName);

    Member save(Member member);

    List<Member> findAll();

    void deleteByName(String memberName);

    Member findByNameAndPassword(String memberName, String password);
}
