package org.example.kr;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.kr.model.*;
import org.example.kr.service.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FrontControllerServlet", urlPatterns = "/do/*")
public class FrontControllerServlet extends HttpServlet {
    UserService userService;
    EquipmentService equipmentService;
    TypesService typesService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute("userService");
        equipmentService = (EquipmentService) config.getServletContext().getAttribute("equipmentService");
        typesService = (TypesService) config.getServletContext().getAttribute("typesService");
    }

    public void proceed(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String check;
        String query;
        String equipId;
        String type;
        int result;
        String path = req.getPathInfo();

        if (path == null) {
            path = "/";
        }
        switch (path) {
            case "/logout":
                logout(req, resp);
                break;
            case "/login":
                check = req.getParameter("submit");
                if (check.equals("Log in")) {
                    login(req, resp);
                }else if (check.equals("Register")) {
                    register(req, resp);
                }
                break;
            case "/profile":
                profile(req, resp);
                break;
            case "/equipment":
                query = req.getQueryString();
                result = Integer.parseInt(query);
                equipment(req, resp, result, false);
                break;
            case "/action":
                check = req.getParameter("action");
                query = req.getParameter("hidden");
                type = req.getParameter("type");

                // Перевірка Редагування чи Видалення
                // Всередині першої перевірки - перевірка чи категорія чи товар
                if (check.equals("Edit")) {
                    if (type.equals("equip")) {
                        result = Integer.parseInt(query);
                        equipment(req, resp, result, true);
                    }else if (type.equals("category")) {
                        req.setAttribute("edit", true);
                        req.setAttribute("equip", false);
                        req.setAttribute("query", query);
                        req.getRequestDispatcher("/jsp/add.jsp").forward(req, resp);
                    }
                }else if (check.equals("Delete")) {
                    if (type.equals("equip")) {
                        delete(req, resp, query, true);
                    }else if (type.equals("category")) {
                        delete(req, resp, query, false);
                    }
                }
                break;
            case "/edit":
                query = req.getParameter("hidden");
                edit(req, resp, query);
                break;
            case "/editCategory":
                query = req.getParameter("hidden");
                editCategory(req, resp, query);
                break;
            case "/proceedAdd":
                check = req.getParameter("add");

                if (check.equals("Add Equipment")) {
                    req.setAttribute("equip", true);
                    req.getRequestDispatcher("/jsp/add.jsp").forward(req, resp);
                } else if (check.equals("Add Category")) {
                    req.setAttribute("equip", false);
                    req.getRequestDispatcher("/jsp/add.jsp").forward(req, resp);
                }
                break;
            case "/add":
                check = req.getParameter("hidden");

                if (check.equals("equip")) {
                    add(req, resp, true);
                } else if (check.equals("category")) {
                    add(req, resp, false);
                }
                break;
            case "/addToWishList":
                equipId = req.getParameter("id");
                addToWishList(req, resp,equipId);
                break;
            case "/removeFromWishList":
                equipId = req.getParameter("id");
                removeFromWishList(req, resp, equipId);
                break;
            case "/sort":
                query = req.getQueryString();
                sort(req, resp, query);
            default:
                catalog(req, resp);
                break;
        }
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        String username = req.getParameter("login");
        String password = req.getParameter("pass");

        User user = userService.getByLogin(username);


        if (user == null) {
            error(req, resp, "Invalid username");
            return;
        }

        if (!userService.checkPass(user, password)) {
            error(req, resp, "Invalid password");
            return;
        }

        List<Integer> wish = userService.getWishList(user.getId());
        req.getSession().setAttribute("user", user);
        req.getSession().setAttribute("wish", wish);
        resp.sendRedirect(".");
    }

    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(".");
    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        String username = req.getParameter("login");
        String password = req.getParameter("pass");

        User user = userService.getByLogin(username);
        if (user != null) {
            error(req, resp, "User already exists");
            return;
        }

        boolean check = userService.create(username, password);
        if (check) {
            req.getSession().setAttribute("user", userService.getByLogin(username));
            resp.sendRedirect(".");
        }else {
            error(req, resp, "Unable to register");
        }
    }

    public void profile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<Integer> wishes = (List<Integer>) req.getSession().getAttribute("wish");
        List<Equipment> list = new ArrayList<>();
        for (int id: wishes) {
            list.add(equipmentService.getById(id));
        }
        req.setAttribute("list", list);
        req.getRequestDispatcher("/jsp/profile.jsp").forward(req, resp);
    }

    public void catalog(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        if (req.getAttribute("types") == null) {
            req.setAttribute("types", typesService.getAll());
            req.setAttribute("list", equipmentService.getAll());
        }else {
            String type_id = String.valueOf(req.getAttribute("type_id"));
            List<Equipment> list;
            if (type_id != null) {
                list = equipmentService.getAll(Integer.parseInt(type_id));
            }
            else {
                list = new ArrayList<>();
            }
            req.setAttribute("list", list);
        }

        req.getRequestDispatcher("/jsp/catalog.jsp").forward(req, resp);
    }

    public void equipment(HttpServletRequest req, HttpServletResponse resp, int query, boolean edit) throws ServletException, IOException, SQLException {
        Equipment equip = equipmentService.getById(query);
        req.setAttribute("equip", equip);

        if (edit) {
            req.setAttribute("edit", true);
        }

        req.getRequestDispatcher("/jsp/equipment.jsp").forward(req, resp);
    }

    public void edit(HttpServletRequest req, HttpServletResponse resp, String key) throws ServletException, IOException, SQLException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Float price = 0.0F;
        String type = req.getParameter("type");

        if (!req.getParameter("price").isEmpty()) {
            try {
                price = Float.parseFloat(req.getParameter("price"));
            }catch (NumberFormatException e) {
                error(req, resp, "Invalid price");
                return;
            }
        }

        if (description.isEmpty() && !name.isEmpty() && price != 0.0 && !type.isEmpty()) {
            // Назва + Тип + Ціна
            if (!equipmentService.updateEquipment(key, name, type, price)) {
                error(req, resp, "Unable to update equipment");
                return;
            }
        }else if (description.isEmpty() && name.isEmpty() && price == 0.0 && !type.isEmpty()) {
            // Тип
            if (!equipmentService.updateEquipment(key, type)) {
                error(req, resp, "Unable to update equipment");
                return;
            }
        }else if (description.isEmpty() && name.isEmpty() && price != 0.0 && type.isEmpty()) {
            // Ціна
            if (!equipmentService.updateEquipment(key, price)) {
                error(req, resp, "Unable to update equipment");
                return;
            }
        }else {
            // Назва + Опис + Тип + Ціна
            if (!equipmentService.updateEquipment(key, name, description, type, price)) {
                error(req, resp, "Unable to update equipment");
                return;
            }
        }
        req.getRequestDispatcher("/do").forward(req, resp);
    }

    public void editCategory(HttpServletRequest req, HttpServletResponse resp, String key) throws ServletException, IOException {
        String name = req.getParameter("name");
        String category = req.getParameter("category");

        if (category.isEmpty()) {
            if (!typesService.updateCategory(key, name)) {
                error(req, resp, "Unable to update category");
            }
        }
        else if (name.isEmpty()) {
            int typeId = typesService.getId(category);
            if (!typesService.updateCategory(key, typeId)) {
                error(req, resp, "Unable to update category");
            }
        }
        else {
            int typeId = typesService.getId(category);
            if (!typesService.updateCategory(key, name, typeId)) {
                error(req, resp, "Unable to update category");
            }
        }
        req.getRequestDispatcher("/do").forward(req, resp);
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp, String key, Boolean equip) throws ServletException, IOException, SQLException {
        if (equip) {
            if (!equipmentService.deleteEquipment(key)) {
                error(req, resp, "Unable to delete equipment");
                return;
            }
        }else {
            int id = typesService.getId(key);
            if (!typesService.delete(id)) {
                error(req, resp, "Unable to delete category. Try to delete all elements from this category");
            }
        }

        req.getRequestDispatcher("/do").forward(req, resp);
    }

    public void addToWishList(HttpServletRequest req, HttpServletResponse resp, String equipId) throws ServletException, IOException, SQLException {
        User user = (User) req.getSession().getAttribute("user");
        if (!userService.addToWishList(Integer.parseInt(equipId), user.getId())) {
            error(req, resp, "Unable to add to wish list");
            return;
        }
        List<Integer> wish = userService.getWishList(user.getId());
        req.getSession().setAttribute("wish", wish);
        req.getRequestDispatcher("/do").forward(req, resp);
    }

    public void removeFromWishList(HttpServletRequest req, HttpServletResponse resp, String equipId) throws ServletException, IOException, SQLException {
        User user = (User) req.getSession().getAttribute("user");
        if (!userService.removeFromWishList(Integer.parseInt(equipId), user.getId())) {
            error(req, resp, "Unable to remove from wish list");
            return;
        }
        List<Integer> wish = userService.getWishList(user.getId());
        req.getSession().setAttribute("wish", wish);
        profile(req, resp);
    }

    public void sort(HttpServletRequest req, HttpServletResponse resp, String query) throws ServletException, IOException {
        int id = Integer.parseInt(query);
        List<Type> types = typesService.getAllById(id);

        req.setAttribute("types", types);
        req.setAttribute("type_id", id);
        req.getRequestDispatcher(".").forward(req, resp);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp, Boolean equipment) throws ServletException, IOException, SQLException {
        if (equipment) {
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            Float price = null;
            String type = req.getParameter("type");

            if (!req.getParameter("price").isEmpty()) {
                try {
                    price = Float.parseFloat(req.getParameter("price"));
                }catch (NumberFormatException e) {
                    error(req, resp, "Invalid price");
                    return;
                }
            }else {
                error(req, resp, "Price cannot be empty");
                return;
            }

            if (!equipmentService.addEquipment(name, description, type, price)) {
                error(req, resp, "Unable to add equipment");
            }
        }
        else {
            String name = req.getParameter("name");
            String category = req.getParameter("category");

            if (category.isEmpty()) {
                if (!typesService.addCategory(name)) {
                    error(req, resp, "Unable to add category");
                }
            }else {
                int id = typesService.getId(category);
                if (!typesService.addCategory(name, id)) {
                    error(req, resp, "Unable to add category");
                }
            }
        }
        req.getRequestDispatcher("/do").forward(req, resp);
    }

    public void error(HttpServletRequest req, HttpServletResponse resp, String txt) throws ServletException, IOException {
        req.setAttribute("error", txt);
        req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            proceed(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            proceed(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
