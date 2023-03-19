package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // MVC 방식 : 템플릿 엔진을 사용하여 HTML을 반환
    // 데이터를 내부에서 받음 (값을 바꾸고 싶으면 매번 코드 변경 필요)
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");  // data라는 이름으로 hello!!(지정한 값)를 넘겨줌
        return "hello";
    }


    // MVC 방식 : 템플릿 엔진을 사용하여 HTML을 반환
    // 데이터를 외부에서 받음 (웹사이트에서 값 넘어옴)
    @GetMapping("hello-mvc")
    // required = true : 파라미터가 없으면 오류 발생 (기본값이라 없어도 무방)
    public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model) {
        model.addAttribute("name", name);   // "name"이라는 이름으로 name(파라미터에서 넘어온 값=RequestParam의 value "name")을 넘겨줌
        return "hello-template";
    }


    // API 방식 : 문자를 반환하면 그대로 반환
    @GetMapping("hello-string")
    @ResponseBody   // HTTP의 BODY에 문자 내용을 직접 반환
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello spring"
    }


    // API 방식 : 객체를 반환하면 JSON 방식으로 반환
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
