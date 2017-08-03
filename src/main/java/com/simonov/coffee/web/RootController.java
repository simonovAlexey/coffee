package com.simonov.coffee.web;

import com.simonov.coffee.model.Greeting;
import com.simonov.coffee.service.CoffeeOrderService;
import com.simonov.coffee.to.OrderTO;
import com.simonov.coffee.to.TypeToSelected;
import com.simonov.coffee.to.TypeToSelectedWraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class RootController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private CoffeeOrderService service;
    private ObjectError error;


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

    @GetMapping(value = "coffeelist/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    List<Greeting> sayHello(@PathVariable("name") String name) {
        List<Greeting> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Greeting(counter.incrementAndGet(), String.format(template, name)));
        }
        return list;
    }

    @RequestMapping("/orderlist")
    //not only PostMapping. To change locale we need GET
    public String confirmOrder(@Valid TypeToSelectedWraper typeToSelectedWraper, BindingResult result, SessionStatus status, ModelMap map) {
        if (result.hasErrors()) {
            map.addAttribute("nCupFree", service.getNFreeCup());
            return "coffeelist";
        }
        if (typeToSelectedWraper.getItems() == null || typeToSelectedWraper.getItems().isEmpty())
            return "redirect:coffeelist";// try to change locale on orderTO
        List<TypeToSelected> checkedList = new ArrayList<>();
        for (TypeToSelected item : typeToSelectedWraper.getItems()) {
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
