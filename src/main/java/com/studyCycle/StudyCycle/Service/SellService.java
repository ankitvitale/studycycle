package com.studyCycle.StudyCycle.Service;


import com.studyCycle.StudyCycle.Configuration.JwtRequestFilter;
import com.studyCycle.StudyCycle.Payload.ReceiptResponse;
import com.studyCycle.StudyCycle.Payload.SellProductRequest;
import com.studyCycle.StudyCycle.Payload.SellRequest;
import com.studyCycle.StudyCycle.Payload.Sellinput;
import com.studyCycle.StudyCycle.Repository.AddressRepository;
import com.studyCycle.StudyCycle.Repository.SellRepository;
import com.studyCycle.StudyCycle.Repository.TransactionRepository;
import com.studyCycle.StudyCycle.Repository.UserRepository;
import com.studyCycle.StudyCycle.entity.Address;
import com.studyCycle.StudyCycle.entity.Sell;
import com.studyCycle.StudyCycle.entity.Transaction;
import com.studyCycle.StudyCycle.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SellService {

    @Autowired
    private SellRepository sellRepository;
    @Autowired
    private UserRepository userDao;

    @Autowired
    private ProductService productService;

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressRepository addressRepository;

    public void addSellProduct(SellProductRequest sellproductRequest ) throws IOException {
        //String currentUser = JwtRequestFilter.CURRENT_USER;
        sellRepository.save(new Sell(productService.addProduct(sellproductRequest.product),
                                        userService.findUser(JwtRequestFilter.CURRENT_USER),
                                        sellproductRequest.cost,
                                        sellproductRequest.Quantity,
                                        sellproductRequest.type));

    }

    public void updateSellProduct(Long id, SellProductRequest sellproductRequest) throws IOException {
        Optional<Sell> sell= sellRepository.findById(id);
        if(sell.isPresent()){
            Sell sellprod= sell.get();
            sellprod.setProduct(productService.updateProduct(sellprod.getProduct().getId(),sellproductRequest.product));
            sellprod.setCost(sellproductRequest.cost);
            sellprod.setType(sellproductRequest.type);
            sellprod.setQuantity(sellproductRequest.Quantity);
            sellRepository.save(sellprod);
        }
    }

    public String deleteSellProduct(Long id) {
        Optional<Sell> sell= sellRepository.findById(id);
        sell.ifPresent(value -> sellRepository.delete(value));
        return "removed";
    }
    public List<Sell> getNewSellProducts() {
       return sellRepository.findAllByType("New");
    }

    public List<Sell> getOldSellProducts() {
        return sellRepository.findAllByType("Used");
    }

    public List<Sell> findNewMatching(String match) {
        return sellRepository.findNewMatching(match);
    }
    public List<Sell> findOldMatching(String match) {
        return sellRepository.findOldMatching(match);
    }


//    public ReceiptResponse getReceipt(SellRequest orderProd) {
//        ReceiptResponse receiptResponse = new ReceiptResponse();
//        receiptResponse.order_prod=orderProd.orderProd;
//
//        // Initialize variables
//        double mrp = 0.0;
//        double platformFee = 0.0;
//        double deliveryCharge = 0.0;
//
//        String currentUser = JwtRequestFilter.CURRENT_USER;
//
//        User buyer=userService.findUser(JwtRequestFilter.CURRENT_USER);
//        String  orderId = UUID.randomUUID().toString();
//        for (Sellinput a : orderProd.orderProd) {
//
//            Optional<Sell> sellOpt = sellRepository.findById(a.sell_id);
//            if (sellOpt.isPresent()) {
//                Sell sell = sellOpt.get();
//
//                // Calculate mrp
//                Double totalPrice = sell.getCost() * a.quantity;
//                mrp += totalPrice;
//
//                // Calculate platform fee (10% of cost)
//                Double pf = 0.10 * sell.getCost() * a.quantity;
//                platformFee += pf;
//
//                Double toseller = totalPrice - pf;
//
//                User user = sell.getUser();
//                user.setWallet(user.getWallet() + toseller);
//                userDao.save(user);
//                // Add fixed delivery charge per item
//                Double dc = 70.0 * a.quantity;
//                deliveryCharge += dc;
//
//                Optional<Address> delivery_address = addressRepository.findById(orderProd.addressId);
//
//                Address address = null;
//                if (delivery_address.isPresent()) {
//                    address = delivery_address.get();
//                }
//                Transaction transaction = new Transaction("incomplete", buyer, sell, a.quantity, totalPrice, pf, dc, toseller, orderId, address);
////                Transaction savedTransaction = transactionRepository.save(transaction);
////
////                // Now you can access the saved transaction's properties, including its ID
////                String transactionId = savedTransaction.getOrderId();
//                transactionRepository.save(transaction);
//
//                // Update sell quantity
//                sell.setQuantity(sell.getQuantity() - a.getQuantity());
//                sellRepository.save(sell);
//            }
//        }
//
//        receiptResponse.deliveryCharge=deliveryCharge;
//        receiptResponse.platformFee=platformFee;
//        receiptResponse.totalMrp=mrp;
//        receiptResponse.total=deliveryCharge+platformFee+mrp;
//        receiptResponse.orderId=orderId;
//        return receiptResponse;
//    }

    public ReceiptResponse getReceipt(SellRequest sellRequest) {
        ReceiptResponse receiptResponse = new ReceiptResponse();
        receiptResponse.order_prod=sellRequest.orderProd;

        // Initialize variables
        double mrp = 0.0;
        double platformFee = 0.0;
        double deliveryCharge = 0.0;

        User buyer = userService.findUser(JwtRequestFilter.CURRENT_USER);
        String orderId = UUID.randomUUID().toString();

        for (Sellinput input : sellRequest.orderProd) {
            Optional<Sell> sellOpt = sellRepository.findById(input.sell_id);
            if (sellOpt.isPresent()) {
                Sell sell = sellOpt.get();

                // Calculate mrp
                double totalPrice = sell.getCost() * input.getQuantity();
                mrp += totalPrice;

                // Calculate platform fee (10% of cost)
                double pf = 0.10 * sell.getCost() * input.getQuantity();
                platformFee += pf;

                double toSeller = totalPrice - pf;

                User seller = sell.getUser();
                seller.setWallet(seller.getWallet() + toSeller);
                userDao.save(seller);

                // Add fixed delivery charge per item
                double dc = 70.0 * input.getQuantity();
                deliveryCharge += dc;

                Optional<Address> deliveryAddressOpt = addressRepository.findById(sellRequest.addressId);

                if (deliveryAddressOpt.isPresent()) {
                    Address address = deliveryAddressOpt.get();

                    Transaction transaction = new Transaction("incomplete", buyer, sell, input.getQuantity(), totalPrice, pf, dc, toSeller, orderId, address);
                    transactionRepository.save(transaction);

                    // Update sell quantity
                    sell.setQuantity(sell.getQuantity() - input.getQuantity());
                    sellRepository.save(sell);
                } else {
                    throw new RuntimeException("Address not found for ID: " + sellRequest.addressId);
                }
            } else {
                throw new RuntimeException("Sell item not found for ID: " + input.sell_id);
            }
        }

        receiptResponse.deliveryCharge=deliveryCharge;
        receiptResponse.platformFee=platformFee;
        receiptResponse.totalMrp=mrp;
        receiptResponse.total=deliveryCharge+platformFee+mrp;
        receiptResponse.orderId=orderId;
        return receiptResponse;
    }

}
