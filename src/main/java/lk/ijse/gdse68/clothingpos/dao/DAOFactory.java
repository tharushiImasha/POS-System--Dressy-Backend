package lk.ijse.gdse68.clothingpos.dao;

import lk.ijse.gdse68.clothingpos.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.gdse68.clothingpos.dao.custom.impl.ItemDAOImpl;
import lk.ijse.gdse68.clothingpos.dao.custom.impl.OrderDetailsDAOImpl;
import lk.ijse.gdse68.clothingpos.dao.custom.impl.OrdersDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){

    }

    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)? daoFactory = new DAOFactory(): daoFactory;
    }

    public enum DAOType{
        CUSTOMER, ITEM, ORDER, ORDERDETAIL
    }

    public SuperDAO getSuperDAO(DAOType daoType){
        switch (daoType){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrdersDAOImpl();
            case ORDERDETAIL:
                return new OrderDetailsDAOImpl();
            default:
                return null;
        }
    }
}
