package hiber.service;

import hiber.dao.CarDao;
import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;
    private final CarDao carDao;

    public UserServiceImp(UserDao userDao, CarDao carDao) {
        this.userDao = userDao;
        this.carDao = carDao;
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
        if (user.getCar() != null) {
            carDao.add(user.getCar());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getUsersForCar(String model, int series) {
        return userDao.getUsersForCar(model, series);
    }
}
