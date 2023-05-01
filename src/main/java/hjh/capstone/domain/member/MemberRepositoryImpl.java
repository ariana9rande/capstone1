package hjh.capstone.domain.member;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepositoryImpl implements MemberRepository
{
    @PersistenceContext
    private EntityManager em;

    @Override
    public Member save(Member member)
    {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long memberId)
    {
        if (memberId == null) {
            return Optional.empty();
        }
        Member member = em.find(Member.class, memberId);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String memberName)
    {
        if (memberName == null)
        {
            return Optional.empty();
        }
        Member member = em.find(Member.class, memberName);
        return Optional.ofNullable(member);
    }

    @Override
    public List<Member> findAll()
    {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    @Override
    public void deleteByName(String memberName)
    {
        Member member = em.find(Member.class, memberName);
        if (member != null)
        {
            em.remove(member);
        }
    }

    @Override
    public Member findByNameAndPassword(String memberName, String password)
    {
        return em.createQuery("select m from Member m where m.memberName = :memberName and m.password = :password", Member.class)
                .setParameter("memberName", memberName)
                .setParameter("password", password)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }
}
