package user;

import com.github.javafaker.Faker;
import org.apache.commons.codec.digest.DigestUtils;
import java.time.ZoneId;
import java.util.Locale;


public class FakeUserGenerator
{
    private Faker userFaker;
    public FakeUserGenerator(Locale locale)
    {
        userFaker = new Faker(locale);
    }

    public User createUser()
    {
        User fakeUser = User.builder()
                .username(userFaker.name().username())
                .password(DigestUtils.md5Hex(userFaker.internet().password()))
                .name(userFaker.name().fullName())
                .email(userFaker.internet().emailAddress())
                .gender(userFaker.options().option(User.Gender.class))
                .birthDate(userFaker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .enabled(userFaker.bool().bool())
                .build();
        return fakeUser;
    }
}
