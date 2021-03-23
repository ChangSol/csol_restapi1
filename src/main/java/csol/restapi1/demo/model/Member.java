package csol.restapi1.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity //JPA가 관리하기 위함
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String name;
    private Integer age;
    private String address;
    @CreationTimestamp
    @Column(name = "create_date")
    private Date createDate;

    public Member(String name, Integer age, String address){
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
