package problems.libms.manager.interfaces;

import problems.libms.models.Member;

import java.util.Optional;

public interface MemberCatalogueInterface {
    void addMember(Member member);
    void removeMember(String memberId);
    void updateMember(Member member);
    Optional<Member> getMemberById(String memberId);
}
