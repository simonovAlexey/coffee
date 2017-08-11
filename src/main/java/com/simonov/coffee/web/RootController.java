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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RootController {

    private CoffeeOrderService service;


    @Autowired
    public RootController(CoffeeOrderService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String root() {
        return "redirect:coffeelist";
    }

    @GetMapping("/coffeelist")
    public String indexPage(ModelMap model) {
        return getCoffeListPage(model, false, false);
    }

    @RequestMapping("/orderlist") //not only PostMapping. To change locale we need GET
    public String confirmOrder(@Valid TypeToSelectedWraper typeToSelectedWraper, BindingResult result, ModelMap map) {
        if (result.hasErrors()) {
            map.addAttribute("nCupFree", service.getNFreeCup());
            return "coffeelist";
        }
        if (typeToSelectedWraper.getItems() == null || typeToSelectedWraper.getItems().isEmpty())
            return "redirect:coffeelist";                               // try to change locale on orderTO
        List<TypeToSelected> checkedList = new ArrayList<>();

        for (TypeToSelected item : typeToSelectedWraper.getItems()) {  //double check validation
            if (item.isSelected()) {
                if (item.getQuantity() != null && item.getQuantity() > 0) {
                    checkedList.add(item);
                } else return getCoffeListPage(map, true, false);
            }
        }
        if (checkedList.isEmpty()) return "redirect:coffeelist";

        OrderTO orderTO = service.prepareOrder(checkedList);
        map.addAttribute("orderTO", orderTO);

        return "orderlist";
    }


    @RequestMapping("/order")
    public String saveOrder(@Valid OrderTO orderTO, BindingResult result, SessionStatus status, ModelMap map) {
        if (!result.hasErrors()) {
            service.save(orderTO);
            status.setComplete();
            return getCoffeListPage(map, false, true);
        }
        return "orderlist";
    }

    private String getCoffeListPage(ModelMap model, Boolean error, Boolean confirmOrder) {
        model.addAttribute("typeToSelectedWraper", service.getWrapper());
        model.addAttribute("nCupFree", service.getNFreeCup());
        if (confirmOrder) model.addAttribute("orderConfirmed", true);
        if (error) {
            model.addAttribute("errorValue", "exception.wrongQuantity");
        }
        return "coffeelist";
    }
}
