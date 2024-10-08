package lk.ijse.gdse68.clothingpos.dto;

import jakarta.json.bind.annotation.JsonbProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrdersDTO {
    private String order_id;
    private String cus_id;
    private double total;
    private LocalDate date;
    @JsonbProperty("order_details")
    private List<OrderDetailsDTO> order_details;

    public OrdersDTO(String order_id, String cus_id, double total, LocalDate date) {
        this.order_id = order_id;
        this.cus_id = cus_id;
        this.total = total;
        this.date = date;
    }
}
