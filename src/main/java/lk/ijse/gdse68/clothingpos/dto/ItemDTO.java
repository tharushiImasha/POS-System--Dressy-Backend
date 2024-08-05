package lk.ijse.gdse68.clothingpos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ItemDTO implements Serializable {
    private String costume_id;
    private String type;
    private String color;
    private int amount;
    private String price;
}
