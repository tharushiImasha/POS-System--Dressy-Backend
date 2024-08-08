package lk.ijse.gdse68.clothingpos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Item implements Serializable {
    private String costume_id;
    private String type;
    private String color;
    private int amount;
    private String price;
}
