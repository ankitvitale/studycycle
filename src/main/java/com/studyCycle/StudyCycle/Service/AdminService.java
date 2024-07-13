package com.studyCycle.StudyCycle.Service;


import com.studyCycle.StudyCycle.Payload.CLaimMoney;
import com.studyCycle.StudyCycle.Repository.ShopkeeperRepository;
import com.studyCycle.StudyCycle.Repository.UserRepository;
import com.studyCycle.StudyCycle.entity.Category;
import com.studyCycle.StudyCycle.entity.Shopkeeper;
import com.studyCycle.StudyCycle.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

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
                LocalDate currentDueDate = shopkeeper.getDue_date().toLocalDate();
                LocalDate newDueDate = currentDueDate.plus(1, ChronoUnit.MONTHS);
                shopkeeper.setDue_date(java.sql.Date.valueOf(newDueDate));
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

    public List<CLaimMoney> claimMoney() {
        List<User> userList= userDao.finalAllByClaimstatus("pending");
        List<CLaimMoney> list= new ArrayList<>();
        for(User u: userList){
            CLaimMoney cLaimMoney= new CLaimMoney(u.getId(),u.getFullName(),u.getUpi_id(),u.getClaimedMoney(),u.getWallet());
            list.add(cLaimMoney);
        }
        return list;
    }

    public void markClaimed(Long userId, Double claimedMoney) {
        Optional<User> user_Opt= userDao.findById(userId);
        if(user_Opt.isPresent()){
            User user = user_Opt.get();
            if(Objects.equals(user.getClaimedMoney(), claimedMoney)){
                user.setClaimstatus("Claimed");
                user.setClaims(user.getClaims()+claimedMoney);
                user.setClaimedMoney(0.0);
                userDao.save(user);
            }
        }
    }
}
