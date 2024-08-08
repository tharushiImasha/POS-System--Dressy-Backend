package lk.ijse.gdse68.clothingpos.entity;

import jakarta.json.bind.annotation.JsonbProperty;
import lk.ijse.gdse68.clothingpos.dto.OrderDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Orders {
    private String order_id;
    private String cus_id;
    private double total;
    private LocalDate date;
    @JsonbProperty("order_details")
    private List<OrderDetailsDTO> order_details;

    public Orders(String order_id, String cus_id, double total, LocalDate date) {
        this.order_id = order_id;
        this.cus_id = cus_id;
        this.total = total;
        this.date = date;
    }
}
