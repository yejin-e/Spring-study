package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {   // 테스트는 한글로 메소드명 작성 가능
        // given    // 주어진 상황
        Member member = new Member();
        member.setName("hello");

        // when     // 이런 상황에서
        Long saveId = memberService.join(member);

        // then     // 이렇게 된다
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());    // member의 이름과 findMember의 이름이 같은지 확인
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

        // 방법 1
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));  // 예외가 발생해야 한다
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");     // 예외 메시지가 "이미 존재하는 회원입니다."와 같은지 확인

        // 방법 2
//        try {
//            memberService.join(member2);    // 이름이 중복인 회원이므로 예외 발생
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}