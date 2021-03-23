package csol.restapi1.demo.service;

import csol.restapi1.demo.model.Member;
import csol.restapi1.demo.repository.MemberRepository;
import csol.restapi1.demo.util.NumberExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    //public MemberService(MemberRepository memberRepository){this.memberRepository = memberRepository;}

    public Member insertOne(Map<String, String> map){
        if(map == null) return null;
        else {
            return memberRepository.save(new Member(
                    map.get("name"),
                    NumberExtension.intNullToNull(map.get("age")),
                    map.get("address")));
        }
    }

    public List<Member> selectAll(){
        return memberRepository.findAll();
    }

    public Member selectOne(long id){
        return memberRepository.findById(id).orElse(null);
    }

    public Member updateOne(long id, Map<String, String> map){
        Member member = memberRepository.findById(id).orElse(null);
        if(member != null){
            member.setName(map.get("name"));
            member.setAge(Integer.parseInt(map.get("age")));
            member.setAddress(map.get("address"));
            return memberRepository.save(member);
        }
        else
            return null;
    }

    public String deleteOne(long id){
        memberRepository.deleteById(id);
        return "delete success";
    }
}
