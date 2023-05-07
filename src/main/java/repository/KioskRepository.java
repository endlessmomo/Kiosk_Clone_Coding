package repository;

import domain.*;
import lombok.Getter;

import java.util.*;

@Getter
public class KioskRepository {
    private final Map<Category, List<Item>> repo = new HashMap<>();
    private final List<Item> cart = new ArrayList<>();

    public KioskRepository() {
        repo.put(Category.CLOTHES, new ArrayList<>());
        repo.put(Category.FOOD, new ArrayList<>());
        repo.put(Category.BEVERAGE, new ArrayList<>());
        repo.put(Category.BAG, new ArrayList<>());

        // 테스트를 위한 코드입니다.
        repo.get(Category.CLOTHES).add(new Clothes("청바지", 54000));
        repo.get(Category.CLOTHES).add(new Clothes("후드티", 59000));
        repo.get(Category.BEVERAGE).add(new Beverage("아메리카노", 1500));
        repo.get(Category.BEVERAGE).add(new Beverage("라떼", 2500));
        repo.get(Category.BEVERAGE).add(new Beverage("밀크티", 3000));
        repo.get(Category.FOOD).add(new Food("팟타이", 15000));
        repo.get(Category.FOOD).add(new Food("우동", 10000));
        repo.get(Category.BAG).add(new Bag("크로스백", 34000));
        repo.get(Category.BAG).add(new Bag("에코백", 20000));
    }

    public void upload(Category type, String name, int price) {
        switch (type) {
            case CLOTHES:
                repo.get(type).add(new Clothes(name, price));
                break;
            case FOOD:
                repo.get(type).add(new Food(name, price));
                break;
            case BEVERAGE:
                repo.get(type).add(new Beverage(name, price));
                break;
            case BAG:
                repo.get(type).add(new Bag(name, price));
                break;
        }
    }

    public Map<Category, List<Item>> viewItem() {
        return repo;
    }

    // key - value
    public void addCart(Category type, String itemName) {
        cart.add(findByName(type, itemName));
    }

    public Item findByName(Category type, String itemName) {
        // key : type : type -> ArrayList -> 순차적으로 확일할게
        // 필터 where <<<<
        return repo.get(type).stream()
                .filter(i -> i.getName().equals(itemName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 상품은 존재하지 않아요"));
    }

    public List<Item> viewCart() {
        return cart;
    }
}
