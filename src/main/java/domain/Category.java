package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Category {
    CLOTHES("의류"),
    FOOD("음식"),
    BAG("가방"),
    BEVERAGE("음료");

    private final String category;

    public static Category from(String value) {
        return Arrays.stream(values())
                .filter(i -> i.category.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리 품목은 존재하지 않습니다."));
    }
}
