package com.simonov.coffee.web;

import com.simonov.coffee.model.Greeting;
import com.simonov.coffee.service.CoffeeOrderService;
import com.simonov.coffee.to.CoffeeOrderItemTo;
import com.simonov.coffee.to.OrderTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class RootController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
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

    @GetMapping(value = "coffeelist/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    List<Greeting> sayHello(@PathVariable("name") String name) {
        List<Greeting> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Greeting(counter.incrementAndGet(), String.format(template, name)));
        }
        return list;
    }

    @RequestMapping("/orderlist")  //not PostMapping. To change  locale we need GET
    public String confirmOrder(/*Locale locale, @RequestParam Map<String, String> orderParameters*/
                               @RequestParam(name = "id", required = false) List<Integer> idL
            , @RequestParam(name = "selected", required = false) List<Integer> selectedL
//            , @RequestParam(name = "typeName", required = false) List<String> typeNameL
            , @ModelAttribute @RequestParam(name = "quantity", required = false) List<String> quantityL
//            , BindingResult result
            , ModelMap model
    ) {
        if (idL == null || idL.isEmpty()) return "redirect:coffeelist";// try to change locale on orderTO
        HashMap<Integer, Integer> chekedCT = new HashMap<>();
        System.out.println("");

        for (int i = 0; i < idL.size(); i++) {
            int quantity;
            String s = quantityL.get(i);
            Integer key = idL.get(i);
            if (s != null && s.length() > 0 && selectedL.contains(key)) {
                try {
                    quantity = Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    return getCoffeListPage(model, true, false);

//                result.rejectValue("quantity", "exception.wrongQuantity");
                }
                if (quantity <= 0) return getCoffeListPage(model, true, false);
                chekedCT.put(key, quantity);
            }

        }
        if (chekedCT.isEmpty()) return "redirect:coffeelist";

        List<CoffeeOrderItemTo> coffeeOrderItemList = service.getByCoffeeTypeIdAndQuantity(chekedCT);
        OrderTO orderTO = service.prepareOrder(coffeeOrderItemList);

        model.addAttribute("orderTO", orderTO);

        System.out.println("");
        return "orderlist";
    }

    @RequestMapping("/order")
    public String confirmOrder(@Valid OrderTO orderTO, BindingResult result, SessionStatus status, ModelMap map) {
        if (!result.hasErrors()) {
            service.save(orderTO);
            status.setComplete();
            return getCoffeListPage(map, false, true);
        }
        return "orderlist";
    }


    @NotNull
    private String getCoffeListPage(ModelMap model, Boolean error, Boolean confirmOrder) {
        model.addAttribute("now", (LocalDate.now() + " " + LocalTime.now()));
        model.addAttribute("coffeetypelist", service.getAllEnabledCoffeType());
        if (confirmOrder) model.addAttribute("orderConfirmed", true);
        if (error) {
            model.addAttribute("errorValue", "exception.wrongQuantity");
        }
        return "coffeelist";
    }

    /*@RequestMapping("/error")
    public String error(BindingResult result, SessionStatus status, ModelMap model){
        System.out.println("");
        return "redirect:coffeelist";
    }*/
}
