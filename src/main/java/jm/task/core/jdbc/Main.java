package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


public class Main {
    public static void main(String[] args) {

        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        User user = new User();
           Util.getSessionFactory().openSession();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("Anton", "Sed", (byte) 45);
        System.out.println("User с именем – " + user.getName() + " " + user.getLastName() + " добавлен в базу данных");
        userDaoHibernate.saveUser("Antonina", "Sed", (byte) 35);
        System.out.println("User с именем – " + user.getName() + " " + user.getLastName() + " добавлен в базу данных");
        userDaoHibernate.saveUser("Max", "Pain", (byte) 55);
        System.out.println("User с именем – " + user.getName() + " " + user.getLastName() + " добавлен в базу данных");
        userDaoHibernate.saveUser("Helena", "Beauty", (byte) 25);
        System.out.println("User с именем – " + user.getName() + " " + user.getLastName() + " добавлен в базу данных");
        System.out.println(userDaoHibernate.getAllUsers());
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
        Util.getSessionFactory().close();
    }
}
