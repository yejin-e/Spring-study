package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired  // 스프링 컨테이너에 있는 MemberRepository를 가져와서 연결
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     * 회원 가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    // 같은 이름이 있는 중복 회원 X
    private void validateDuplicateMember(Member member) {
        // 방법 1
//        Optional<Member> result = memberRepository.findByName(member.getName());    // null일 가능성이 있으면 Optional로 반환
//        result.ifPresent(m -> {    // Optional에서 값이 있으면 실행
//            throw new IllegalStateException("이미 존재하는 회원입니다.");  // IllegalStateException: 비정상적인 상황
//        });

        // 방법 2
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

    }

    /*
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
