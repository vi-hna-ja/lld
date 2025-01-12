package problems.libms.manager;

import problems.libms.manager.interfaces.MemberCatalogueInterface;
import problems.libms.models.Member;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MemberCatalogueManager implements MemberCatalogueInterface {
    private final ConcurrentHashMap<String, Member> memberCatalogue;
    public MemberCatalogueManager() {
        this.memberCatalogue = new ConcurrentHashMap<>();
    }

    @Override
    public void addMember(Member Member) {
        memberCatalogue.put(Member.getMemberId(), Member);
    }

    @Override
    public void removeMember(String memberId) {
        if (!memberCatalogue.containsKey(memberId)) {
            System.out.printf("Member %s not present. Unable to remove.\n", memberId);
            return;
        }
        memberCatalogue.remove(memberId);
    }

    @Override
    public void updateMember(Member Member) {
        if (!memberCatalogue.containsKey(Member.getMemberId())) {
            System.out.printf("Member %s not present. Unable to update.\n", Member.getMemberId());
            return;
        }
        memberCatalogue.put(Member.getMemberId(), Member);
    }

    @Override
    public Optional<Member> getMemberById(String memberId) {
        if (!memberCatalogue.containsKey(memberId)) {
            System.out.printf("Member %s not present.\n", memberId);
            return Optional.empty();
        }

        return Optional.of(memberCatalogue.get(memberId));
    }
}
