package cn.edu.csust.liman.erobot.admin.entity;

import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

@Table(name = "e_group")
public class Group {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    @Transient
    private Long[] receiverId;
    @Transient
    private Integer receiverNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long[] getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long[] receiverId) {
        this.receiverId = receiverId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(id, group.id) &&
                Objects.equals(name, group.name) &&
                Arrays.equals(receiverId, group.receiverId);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(id, name);
        result = 31 * result + Arrays.hashCode(receiverId);
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", receiverId=" + Arrays.toString(receiverId) +
                '}';
    }

    public Integer getReceiverNumber() {
        return receiverNumber;
    }

    public void setReceiverNumber(Integer receiverNumber) {
        this.receiverNumber = receiverNumber;
    }
}
