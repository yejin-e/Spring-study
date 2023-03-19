package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  // null이면 Optional.empty()를 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()      // store의 값들을 하나씩 꺼내서 stream으로 반환
                .filter(member -> member.getName().equals(name))    // filter(조건)로 매개변수 name과 같은 member 찾기
                .findAny();         // 하나라도 찾으면 Optional로 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());    // store의 값들을 ArrayList로 반환
    }

    public void clearStore() {
        store.clear();  // store 초기화
    }

}
