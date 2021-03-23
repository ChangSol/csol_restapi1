package csol.restapi1.demo.controller;

import csol.restapi1.demo.model.Member;
import csol.restapi1.demo.service.MemberService;
import csol.restapi1.demo.util.NumberExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

//    @GetMapping("/test")
//    public Member memberTest(){
//        return new Member(0L, "사용자1", 20, "인천", new Date());
//    }

    //@GetMapping("/insert")
    @PostMapping("/insert")
    public Member insert(@RequestBody Map<String, String> map){
        System.out.println(1);
        System.out.println(NumberExtension.intNullToNull(map.get("age")));
        System.out.println(2);
        return memberService.insertOne(map);
    }

    @GetMapping("/select")
    public List<Member> select(){
        return memberService.selectAll();
    }

    @GetMapping("/select/{id}")
    public Member select(@PathVariable("id") long id){
        return memberService.selectOne(id);
    }

    @PostMapping("/update/{id}")
    public Member update(@PathVariable("id") long id,@RequestBody Map<String,String> map){
        return memberService.updateOne(id, map);
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id){
        return memberService.deleteOne(id);
    }

}
