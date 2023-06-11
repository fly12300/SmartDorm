package cn.edu.xmu.dorm.mapper.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "nickname")
    private String nickname;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "salt")
    private String salt;
    @Basic
    @Column(name = "head")
    private String head;
    @Basic
    @Column(name = "register_date")
    private Timestamp registerDate;
    @Basic
    @Column(name = "last_login_date")
    private Timestamp lastLoginDate;
    @Basic
    @Column(name = "login_count")
    private Integer loginCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Timestamp getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
    }

    public Timestamp getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Timestamp lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPo that = (UserPo) o;
        return id == that.id && Objects.equals(nickname, that.nickname) && Objects.equals(password, that.password) && Objects.equals(salt, that.salt) && Objects.equals(head, that.head) && Objects.equals(registerDate, that.registerDate) && Objects.equals(lastLoginDate, that.lastLoginDate) && Objects.equals(loginCount, that.loginCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, password, salt, head, registerDate, lastLoginDate, loginCount);
    }
}
