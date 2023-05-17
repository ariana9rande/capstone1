package hjh.capstone.service;

import hjh.capstone.domain.member.Member;
import hjh.capstone.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService
{
    private final MemberRepository memberRepository;

    public Optional<Member> findMember(Long memberId)
    {
        return memberRepository.findById(memberId);
    }

    @Transactional
    public void join(Member member)
    {
        validateDuplicateMember(member);
        memberRepository.save(member);
//        return member.getMemberId();
    }

    public void validateDuplicateMember(Member member)
    {
        memberRepository.findById(member.getMemberId())
                .ifPresent(m ->
                {
                    throw new IllegalStateException("이미 존재하는 아이디입니다.");
                });
    }

    public List<Member> findMembers()
    {
        return memberRepository.findAll();
    }

    @Transactional
    public void deleteMemberByName(String memberName)
    {
        memberRepository.deleteByName(memberName);
    }

    public Member login(String memberName, String password)
    {
        return memberRepository.findByNameAndPassword(memberName, password);
    }
}
