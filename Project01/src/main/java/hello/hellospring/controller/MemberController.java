//package hello.hellospring.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import hello.hellospring.service.MemberService;
                // @Component 애노테이션이 있으면 스프링 빈에 자동 등록된다. → @Controller, @Service, @Repository
//@Controller     // Controller 컨트롤러가 스프링 빈에 자동 등록되는 이유도 컴포넌트 스캔 때문
//public class MemberController {
//
//        // 필드 주입
////      @Autowired private MemberService memberService;
//
//        // 생성자 주입       // 의존관계가 실행 중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입 권장
//        private MemberService memberService;
//        @Autowired  // 스프링 컨테이너에 있는 MemberService를 가져와서 연결
//        public MemberController(MemberService memberService) {
//                this.memberService = memberService;
//        }
//
//        // Setter 주입    // public으로 열어두면 변경 가능성이 있어서 좋지 않음
////      private MemberService memberService;
////      @Autowired
////      public void setMemberService(MemberService memberService) {
////              this.memberService = memberService;
////      }
//
//}






package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import hello.hellospring.service.MemberService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
            this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        System.out.println("member = " + member.getName());
        memberService.join(member);
        return "redirect:/";    // 홈 화면으로 redirect
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}

