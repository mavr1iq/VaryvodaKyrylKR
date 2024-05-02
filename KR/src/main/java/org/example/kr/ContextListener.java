package org.example.kr;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.example.kr.dao.DAOFactory;
import org.example.kr.service.EquipmentService;
import org.example.kr.service.UserService;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DAOFactory factory = new DAOFactory();

        UserService userService = new UserService(factory);
        EquipmentService equipmentService = new EquipmentService(factory);

        sce.getServletContext().setAttribute("userService", userService);
        sce.getServletContext().setAttribute("equipmentService", equipmentService);
    }
}
