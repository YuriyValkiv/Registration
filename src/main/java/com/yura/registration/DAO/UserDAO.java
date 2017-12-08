package com.yura.registration.DAO;

import com.yura.registration.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }

    public void removeUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        if (user != null) session.delete(user);
    }

    public User getUser(int id) {
        User user = (User) sessionFactory.getCurrentSession().get(User.class, id);
        return user;
    }

    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        session.save(user);
        session.clear();
        session.close();
    }

    public void editUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        User userInDB = (User) session.get(User.class, user.getId());

        userInDB.setName(user.getName());
        userInDB.setFirstName(user.getFirstName());
        userInDB.setemail(user.getemail());
        userInDB.setPassword(user.getPassword());

        session.save(userInDB);
    }
}
