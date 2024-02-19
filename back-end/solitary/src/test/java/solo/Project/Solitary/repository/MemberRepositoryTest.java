package solo.Project.Solitary.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import solo.Project.Solitary.entity.Member;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 등록 테스트")
    public void postMember() {
        //given
        final Member member = Member.builder()
                .email("dlwlsdn153@gmail.com")
                .password("ABC123")
                .memberName("이진우")
                .memberRating(Member.MemberRating.BRONZE)
                .build();

        //when
        final Member save = memberRepository.save(member);

        //then
        assertThat(save.getMemberId()).isNotNull();
        assertThat(save.getEmail()).isEqualTo("dlwlsdn153@gmail.com");
        assertThat(save.getMemberName()).isEqualTo("이진우");
        assertThat(save.getMemberRating()).isEqualTo(Member.MemberRating.BRONZE);
        assertThat(save.getPassword()).isEqualTo("ABC123");
    }

    @Test
    @DisplayName("회원 조회 테스트")
    public void checkMember() {
        //given
        final Member member = Member.builder()
                .email("dlwlsdn153@gmail.com")
                .password("ABC123")
                .memberName("이진우")
                .memberRating(Member.MemberRating.BRONZE)
                .build();
        memberRepository.save(member);

        //when
        Member find = memberRepository.findByEmail(member.getEmail());

        //then
        assertThat(find.getMemberId()).isNotNull();
        assertThat(find.getEmail()).isEqualTo("dlwlsdn153@gmail.com");
        assertThat(find.getMemberName()).isEqualTo("이진우");
        assertThat(find.getMemberRating()).isEqualTo(Member.MemberRating.BRONZE);
        assertThat(find.getPassword()).isEqualTo("ABC123");
    }
}
