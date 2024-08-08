package lk.ijse.gdse68.clothingpos.bo;

import lk.ijse.gdse68.clothingpos.bo.custom.impl.CustomerBOImpl;
import lk.ijse.gdse68.clothingpos.bo.custom.impl.ItemBOImpl;
import lk.ijse.gdse68.clothingpos.bo.custom.impl.PlaceOrderBOImpl;

public class BOFactory {
    private static BOFactory boFactory ;
    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory = new BOFactory():boFactory ;
    }

    public enum BOType{
        CUSTOMER, ITEM, ORDER
    }

    public SuperBO getSuperBO(BOType boType){
        switch (boType){
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case ORDER:
                return new PlaceOrderBOImpl();
            default:
                return null;

        }
    }
}
