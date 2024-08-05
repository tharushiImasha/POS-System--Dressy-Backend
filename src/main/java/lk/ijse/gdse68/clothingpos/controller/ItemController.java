package lk.ijse.gdse68.clothingpos.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse68.clothingpos.bo.impl.ItemBOImpl;
import lk.ijse.gdse68.clothingpos.dto.ItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/item", loadOnStartup = 2)
public class ItemController extends HttpServlet {

    Connection connection;
    static Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Override
    public void init() throws ServletException {
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/customerReg");
            this.connection = pool.getConnection();

        } catch (NamingException | SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        try (var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);

            ItemBOImpl itemBO = new ItemBOImpl();

            writer.write(itemBO.saveItem(itemDTO, connection));
            logger.info("Item saved successfully");
            resp.setStatus(HttpServletResponse.SC_CREATED);

            resp.setContentType("application/json");
            jsonb.toJson(itemDTO, writer);

        }catch (Exception e) {
            logger.error("Connection failed");
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);

            ItemBOImpl itemBO = new ItemBOImpl();

            if (itemBO.updateItem(itemDTO.getCostume_id(), itemDTO, connection)) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204 No Content for successful update with no response body
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request for failure
                writer.write(jsonb.toJson(Map.of("message", "Update item failed")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(var writer = resp.getWriter()) {
            var costumeId = req.getParameter("costume_id");
            ItemBOImpl itemBO = new ItemBOImpl();

            if (itemBO.deleteItem(costumeId, connection)) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }else {
                writer.write("Delete item failed");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var writer  = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();

            ItemBOImpl itemBO = new ItemBOImpl();
            List<ItemDTO> itemDTOS = itemBO.getAllItems(connection);

            resp.setContentType("application/json");
            jsonb.toJson(itemDTOS, writer);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
