package solo.Project.Solitary.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import solo.Project.Solitary.member.entity.Member;
import solo.Project.Solitary.member.repository.MemberRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 등록 테스트")
    public void saveMember() {
        //given
        final Member member = buildMember();

        //when
        final Member save = memberRepository.save(member);

        //then
        assertThat(save.getMemberId()).isNotNull();
        assertThat(save.getEmail()).isEqualTo("dlwlsdn153@gmail.com");
        assertThat(save.getMemberName()).isEqualTo("이진우");
        assertThat(save.getPassword()).isEqualTo("ABC123");
    }

    @Test
    @DisplayName("회원 조회 테스트")
    public void checkMember() {
        //given
        final Member member = buildMember();

        memberRepository.save(member);

        //when
        Optional<Member> optionalMember = memberRepository.findByEmail(member.getEmail());
        Member find = optionalMember.get();

        //then
        assertThat(find.getMemberId()).isNotNull();
        assertThat(find.getEmail()).isEqualTo("dlwlsdn153@gmail.com");
        assertThat(find.getMemberName()).isEqualTo("이진우");
        assertThat(find.getPassword()).isEqualTo("ABC123");
    }

    @Test
    @DisplayName("회원 탈퇴 테스트")
    public void deleteMember() {
        //given
        Member member = buildMember();
        memberRepository.save(member);

        //when
        memberRepository.deleteById(member.getMemberId());
        Optional<Member> memberReturn = memberRepository.findById(member.getMemberId());

        //then
        assertThat(memberReturn).isEmpty();
    }

    public Member buildMember() {
        return Member.builder()
                .email("dlwlsdn153@gmail.com")
                .password("ABC123")
                .memberName("이진우")
                .build();
    }
}
