package repository;

import domain.Category;
import domain.Item;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class KioskRepository {
    private final Map<Category, List<Item>> repo = new HashMap<>();
    private final List<Item> cart = new ArrayList<>();

    public KioskRepository() {
        repo.put(Category.CLOTHES, new ArrayList<>());
        repo.put(Category.FOOD, new ArrayList<>());
        repo.put(Category.BEVERAGE, new ArrayList<>());
        repo.put(Category.BAG, new ArrayList<>());
    }
}
