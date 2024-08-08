package lk.ijse.gdse68.clothingpos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Customer implements Serializable {
    private String cus_id;
    private String name;
    private String email;
    private String address;
    private String phone;
}
