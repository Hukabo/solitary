package solo.Project.Solitary.sevice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import solo.Project.Solitary.member.dto.MemberDto;
import solo.Project.Solitary.member.entity.Member;
import solo.Project.Solitary.exception.BusinessLogicException;
import solo.Project.Solitary.exception.ExceptionCode;
import solo.Project.Solitary.member.repository.MemberRepository;
import solo.Project.Solitary.member.sevice.MemberService;


import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static solo.Project.Solitary.member.dto.MemberDto.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName("회원 등록 테스트")
    public void createMemberTest() {
        Member member = buildMember();

        when(memberRepository.save(any(Member.class))).thenReturn(member);

        Member savedMember = memberService.createMember(member);

        assertThat(savedMember).isNotNull();
        assertThat(savedMember).isEqualTo(member);

        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @Test
    @DisplayName("회원 조회 테스트")
    public void checkMemberTest() {
        Member member = buildMember();

        when(memberRepository.findById(1L)).thenReturn(Optional.ofNullable(member));

        Member verifiedMember = memberService.findVerifiedMember(1L);

        assertThat(verifiedMember).isNotNull();

        verify(memberRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("회원 수정 테스트")
    public void updateMemberTest() {
        Member existingMember = buildMember();
        Member updatedMember = buildMember();
        updatedMember.setMemberName("newName");
        updatedMember.setPassword("newPassword");

        when(memberRepository.findById(1L)).thenReturn(Optional.of(existingMember));
        when(memberRepository.save(any(Member.class))).thenReturn(updatedMember);

        Member target = memberService.updateMember(updatedMember);

        assertThat(target).isNotNull();
        assertThat(target.getMemberName()).isEqualTo("newName");
        assertThat(target.getPassword()).isEqualTo("newPassword");

        verify(memberRepository, times(1)).findById(1L);
        verify(memberRepository, times(1)).save(any(Member.class));
    }

    public Member buildMember() {
        return Member.builder()
                .memberId(1L)
                .email("dlwlsdn153@gmail.com")
                .memberName("이진우")
                .password("1234")
                .build();
    }
}
