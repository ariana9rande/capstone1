package hjh.capstone.domain.member;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

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
    public Member findById(Long memberId)
    {
        if (memberId == null)
        {
            return null;
        }
        return em.find(Member.class, memberId);
    }

    @Override
    public Member findByName(String memberName)
    {
        if (memberName == null)
        {
            return null;
        }
        return em.find(Member.class, memberName);
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
        return em.createQuery("select m from Member m where m.memberName = :memberName and m.password = :password",
                        Member.class)
                .setParameter("memberName", memberName)
                .setParameter("password", password)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public void update(Member member)
    {
        em.merge(member);
    }

    public String generateToken(Long memberId)
    {
        int length = 255;
        int byteLength = (int) Math.ceil(length * 6.0 / 8.0);

        SecureRandom secureRandom = new SecureRandom();

        // 바이트 배열로 난수 생성
        byte[] randomBytes = new byte[byteLength];
        secureRandom.nextBytes(randomBytes);

        // Base64 인코딩
        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);

        // 길이 조정
        return token.substring(0, length);
    }

    public boolean verifyToken(String token)
    {
        try
        {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            // 토큰이 유효한 경우
            return true;
        }
        catch (FirebaseAuthException e)
        {
            // 토큰이 유효하지 않은 경우
            return false;
        }
    }

    public String generateValidToken(Long memberId)
    {
        int i = 0;
        String token = generateToken(memberId);

        while(!verifyToken(token) && i < 10)
        {
            System.out.println("MemberRepository.generateValidToken : 유효하지 않은 token -> 재생성");
            token = generateToken(memberId);
            i++;
        }

        return token;
    }
}
