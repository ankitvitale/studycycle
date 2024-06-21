package com.studyCycle.StudyCycle.Service;


import com.studyCycle.StudyCycle.Repository.ShopkeeperRepository;
import com.studyCycle.StudyCycle.Repository.UserRepository;
import com.studyCycle.StudyCycle.entity.Category;
import com.studyCycle.StudyCycle.entity.Shopkeeper;
import com.studyCycle.StudyCycle.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private UserRepository userDao;
    @Autowired
    private ShopkeeperRepository shopkeeperRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    public List<User> getUsers(String type) {
        return userDao.findByUsertype(type);
    }

    public List<Shopkeeper> dueList() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        Date endDate = calendar.getTime();

        return shopkeeperRepository.findShopkeepersWithDueDateWithinNext7Days((java.sql.Date) endDate);

    }


    @Transactional
    public void blockUser(Long username) {
        Optional<User> user = userDao.findById(username);
        if (user.isPresent()) {
            User user1 = user.get();
            user1.setVerified(false);
            userDao.save(user1);
        }
    }

    public void unBlockUser(Long id) {
        Optional<User> user = userDao.findById(id);
        if (user.isPresent()) {
            User user1 = user.get();
            user1.setVerified(true);
            userDao.save(user1);
        }
    }
    //check
    public void pushDueDate(Long id) {
        Optional<User> user = userDao.findById(id);
        if(user.isPresent()){
            Optional<Shopkeeper> entity = shopkeeperRepository.findByUser(user.get());
            if(entity.isPresent()){
                Shopkeeper shopkeeper= entity.get();
                shopkeeper.setDue_date(java.sql.Date.valueOf(LocalDate.now().plusMonths(1)));
                shopkeeperRepository.save(shopkeeper);
            }
        }
    }

//    public Category createNewCategory(String category) {
//        return categoryService.createNewCategory(category);
//    }

    public List<Category> getCategory() {
        return categoryService.getCategory();
    }

    public List<User> getBlockList() {
        return userService.getBlockList();
    }
}
