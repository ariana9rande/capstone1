package hjh.capstone.domain.member;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository
{
    Member findById(Long memberId);

    Member findByName(String memberName);

    Member save(Member member);

    List<Member> findAll();

    void deleteByName(String memberName);

    Member findByNameAndPassword(String memberName, String password);

    void update(Member member);

    String generateToken(Long memberId);

    boolean verifyToken(String token);

    String generateValidToken(Long memberId);
}
