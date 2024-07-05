package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;

   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
      if (user.getCar() != null) {
         sessionFactory.getCurrentSession().save(user.getCar());
      }
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().
              createQuery("from User u left join fetch u.car");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> getUsersForCar(String model, int series) {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery(
              "from User as u left join fetch u.car where u.car.model=:model and u.car.series=:series");
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.getResultList();
   }
}
