package cn.edu.csust.liman.erobot.admin.entity;

import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Table(name = "e_account")
public class Account {
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    private static final ThreadLocal<MessageDigest> MESSAGE_DIGEST = ThreadLocal.withInitial(() -> {
        try {
            return MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            // 不可能发生
            e.printStackTrace();
            return null;
        }
    });
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    @NotBlank
    private String username;
    private byte[] password;
    private byte[] salt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void generateSalt() {
        this.salt = new byte[4];
        int random = RANDOM.nextInt();
        this.salt[0] = (byte) (random & 0xff);
        random >>= 4;
        this.salt[1] = (byte) (random & 0xff);
        random >>= 4;
        this.salt[2] = (byte) (random & 0xff);
        random >>= 4;
        this.salt[3] = (byte) (random & 0xff);
    }


    public void setPlaintextOfPassword(byte[] plaintextOfPassword) {
        if (salt == null) {
            this.generateSalt();
        }
        MessageDigest messageDigest = MESSAGE_DIGEST.get();
        messageDigest.reset();
        messageDigest.update(plaintextOfPassword);
        messageDigest.update(this.salt);
        this.password = messageDigest.digest();
    }

    public boolean verifyPassword(byte[] plaintextOfPassword) {
        MessageDigest messageDigest = MESSAGE_DIGEST.get();
        messageDigest.reset();
        messageDigest.update(plaintextOfPassword);
        messageDigest.update(this.salt);
        return Arrays.equals(this.password, messageDigest.digest());
    }
}
