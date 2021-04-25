package user;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import java.util.List;
import java.util.Locale;

public class Main
{
    public static void main(String[] args)
    {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());
        FakeUserGenerator userGenerator = new FakeUserGenerator(new Locale("hu"));
        List<User> users = jdbi.withExtension(UserDAO.class, dao ->
        {
            dao.createTable();
            System.out.println(dao.addUser(userGenerator .createUser()));
            System.out.println(dao.addUser(userGenerator .createUser()));
            System.out.println(dao.addUser(userGenerator .createUser()));
            System.out.println(dao.addUser(userGenerator .createUser()));
            System.out.println(dao.addUser(userGenerator .createUser()));
            System.out.println(dao.addUser(userGenerator .createUser()));
            System.out.println(dao.addUser(userGenerator .createUser()));
            System.out.println(dao.addUser(userGenerator .createUser()));
            System.out.println(dao.addUser(userGenerator .createUser()));

            System.out.println(dao.getUserById((long)2));
            System.out.println(dao.getUserByUsername(dao.getUserById((long)2).get().getUsername()));
            dao.deleteUser(dao.getUserById((long)2).get());

            return  dao.listUsers();
        });
        users.forEach(System.out::println);
    }
}