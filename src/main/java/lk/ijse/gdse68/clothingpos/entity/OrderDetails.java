package lk.ijse.gdse68.clothingpos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderDetails {
    private String order_detail_id;
    private String order_id;
    private String costume_id;
    private int quantity;
}
