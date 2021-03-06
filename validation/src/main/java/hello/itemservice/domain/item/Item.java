package hello.itemservice.domain.item;

import hello.itemservice.web.validation.form.ItemSaveForm;
import hello.itemservice.web.validation.form.ItemUpdateForm;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity>= 10000", message = "상품의 가격 * 수량의 합은 10000원 이상이어야 합니다.")
public class Item {

//    @NotNull(groups = UpdateCheck.class)
    private Long id;

//    @NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;

//    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
//    @Range(min = 1000, max = 1000000, groups = {SaveCheck.class, UpdateCheck.class})
    private Integer price;

//    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
//    @Max(value = 9999, groups = {SaveCheck.class})
    private Integer quantity;
    public Item(){};

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public Item(ItemSaveForm itemSaveForm) {
        this.itemName = itemSaveForm.getItemName();
        this.price = itemSaveForm.getPrice();
        this.quantity = itemSaveForm.getQuantity();
    }

    public Item(ItemUpdateForm itemUpdateForm) {
        this.id = itemUpdateForm.getId();
        this.itemName = itemUpdateForm.getItemName();
        this.price = itemUpdateForm.getPrice();
        this.quantity = itemUpdateForm.getQuantity();
    }
}
