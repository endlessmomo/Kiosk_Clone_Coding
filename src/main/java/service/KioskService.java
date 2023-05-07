package service;


import domain.*;
import domain.dto.IntoCartDto;
import domain.dto.UploadDto;
import repository.KioskRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KioskService {
    private final KioskRepository kioskRepository;

    public KioskService(KioskRepository kioskRepository) {
        this.kioskRepository = kioskRepository;
    }


    public void upload(UploadDto dto) {
        String[] info = dto.getUploadInfo().split(", ");
        try {
            kioskRepository.upload(Category.from(info[0]), info[1], Integer.parseInt(info[2]));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void viewItem() {
        Map<Category, List<Item>> items = kioskRepository.viewItem();

        for (Category key : items.keySet()) {
            System.out.println(key.getCategory()); // 의류, 음식, 음료, 가방
            int i = 1;
            for (Item value : items.get(key)) {
                System.out.println(i++ + ". " + value.toString());   // 상품명, 가격
            }
            // 카테고리별 띄어쓰기
            System.out.println();
        }
    }

    public void addCart(IntoCartDto dto) {
        List<String> orders = someOrder(dto.getOrder());

        try {
            for (String order : orders) {
                String[] info = order.split(", ");

                kioskRepository.addCart(Category.from(info[0]), info[1]);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public List<String> someOrder(String orders) {
        if (orders.contains("|")) {
            String[] items = orders.split("\\|");

            return Arrays.stream(items).collect(Collectors.toList());
        }
        return new ArrayList<>(List.of(orders));
    }

    public void viewCart() {
        List<Item> cart = kioskRepository.viewCart();

        for (Item item : cart) {
            System.out.println(item.toString());
        }
    }

    public void pay() {
        List<Item> cart = kioskRepository.getCart();
        int total = 0;

        for (Item item : cart) {
            Category type = getType(item);

            total += getItemPrice(type, item);
        }

        System.out.println("결제하실 금액은 " + total + "입니다.");
    }

    private int getItemPrice(Category type, Item item) {
        switch (type) {
            case CLOTHES:
                return ((Clothes) item).getPrice();
            case FOOD:
                return ((Food) item).getPrice();
            case BEVERAGE:
                return ((Beverage) item).getPrice();
            case BAG:
                return ((Bag) item).getPrice();
        }

        throw new IllegalArgumentException("지정된 값이 없습니다.");
    }

    public Category getType(Item item) {
        Map<Category, List<Item>> items = kioskRepository.getRepo();

        for (Category key : items.keySet()) {
            if (items.get(key).contains(item)) {
                return key;
            }
        }

        throw new IllegalArgumentException("잘못된 상품입니다.");
    }
}
