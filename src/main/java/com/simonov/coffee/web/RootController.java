package com.simonov.coffee.web;

import com.simonov.coffee.service.CoffeeOrderService;
import com.simonov.coffee.to.OrderTO;
import com.simonov.coffee.to.TypeToSelected;
import com.simonov.coffee.to.TypeToSelectedWraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RootController {

    private static final String ATTR_LIST_COFFEE_TYPES = "typeToSelectedWraper";
    private static final String ATTR_ORDER_CONFIRMED = "orderConfirmed";
    private static final String ATTR_Q_FREE_CUP = "nCupFree";
    private static final String ATTR_ERROR = "errorValue";
    private static final String ATTR_ORDER = "orderTO";

    private CoffeeOrderService service;


    @Autowired
    public RootController(CoffeeOrderService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String root(HttpSession session) {
//        session.removeAttribute(ATTR_LIST_COFFEE_TYPES);
        return "redirect:coffeelist";
    }

    @GetMapping("/coffeelist")
    public String indexPage(ModelMap model) {
        return getCoffeListPage(model, false, false);
    }

    @RequestMapping("/orderlist") //not only PostMapping. To change locale we need GET
    public String confirmOrder(HttpSession session, @Valid TypeToSelectedWraper typeToSelectedWraper, BindingResult result, ModelMap map) {
        TypeToSelectedWraper toSelectedFromSession = (TypeToSelectedWraper) session.getAttribute(ATTR_LIST_COFFEE_TYPES);
        if (result.hasErrors()) {
            if (toSelectedFromSession == null || toSelectedFromSession.getItems() == null || toSelectedFromSession.getItems().isEmpty()) {
                return "redirect:coffeelist";
            } else typeToSelectedWraper = toSelectedFromSession;
        }
        if (toSelectedFromSession == null) session.setAttribute(ATTR_LIST_COFFEE_TYPES, typeToSelectedWraper);

        List<TypeToSelected> checkedList = new ArrayList<>();

        for (TypeToSelected item : typeToSelectedWraper.getItems()) {
            if (item.getQuantity() != null && item.getQuantity() > 0) {
                checkedList.add(item);
            }
        }
        if (checkedList.isEmpty()) return "redirect:coffeelist";

        OrderTO orderTO = service.prepareOrder(checkedList);
        map.addAttribute(ATTR_ORDER, orderTO);
        session.setAttribute(ATTR_ORDER, orderTO);

        return "orderlist";
    }


    @RequestMapping("/order")
    public String saveOrder(HttpSession session, @Valid OrderTO orderTO, BindingResult result, SessionStatus status, ModelMap map) {
        if (!result.hasErrors()) {
            service.save(orderTO);

            session.removeAttribute(ATTR_LIST_COFFEE_TYPES);
            session.removeAttribute(ATTR_ORDER);
            status.setComplete();
            return getCoffeListPage(map, false, true);
        }
        return "orderlist";
    }

    private String getCoffeListPage(ModelMap model, Boolean error, Boolean confirmOrder) {
        model.addAttribute(ATTR_LIST_COFFEE_TYPES, service.getWrapper());
        model.addAttribute(ATTR_Q_FREE_CUP, service.getNFreeCup());
        if (confirmOrder) model.addAttribute(ATTR_ORDER_CONFIRMED, true);
        if (error) model.addAttribute(ATTR_ERROR, "exception.wrongQuantity");
        return "coffeelist";
    }
}
