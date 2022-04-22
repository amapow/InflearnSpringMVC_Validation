package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.*;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ItemValidator implements Validator {
    private final ItemRepository itemRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
        //item == clazz
        //item == subItem
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;

        //검증 로직
//        if (! StringUtils.hasText(item.getItemName())) {
//            errors.rejectValue("itemName", "required");
//        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemName", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "required");

        if (item.getPrice() != null && (item.getPrice() < 1000 || item.getPrice() > 1000000)) {
            errors.rejectValue("price", "range", new Object[]{1000,1000000}, null);
        }

        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            errors.rejectValue("quantity", "max", new Object[]{10000}, null);
        }

        if (itemRepository.findById(item.getId()) != null ) {
            errors.rejectValue("id", "Overlap");
        }

        //특정 필드가 아닌 복합 룰 검증
        if(item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                errors.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }
    }
}
