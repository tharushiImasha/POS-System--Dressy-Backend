package lk.ijse.gdse68.clothingpos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDTO implements Serializable {
    private String cus_id;
    private String name;
    private String email;
    private String address;
    private String phone;
}
