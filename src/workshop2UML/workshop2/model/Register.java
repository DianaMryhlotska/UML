package workshop2UML.workshop2.model;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class Register {
    private HashMap<Integer, Member> register;
    private int numberOfMembers;

    public boolean createMember(String name, String personalNum){
        Member member = new Member(name, personalNum, numberOfMembers);

        if (register.containsValue(member))
            return false;

        register.put(numberOfMembers, member);
        numberOfMembers++;
        return true;
    }

    public boolean deleteMember(Member member){
        return register.remove(member.getMemberId(), member);
    }

    public HashMap<Integer, Member> getRegister() {
        return register;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Register register1 = (Register) o;
        return register.equals(register1.register);
    }

    @Override
    public int hashCode() {
        return Objects.hash(register);
    }
}
