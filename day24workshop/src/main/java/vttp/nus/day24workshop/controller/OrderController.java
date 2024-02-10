package vttp.nus.day24workshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpSession;
import vttp.nus.day24workshop.model.Order;
import vttp.nus.day24workshop.model.OrderDetail;
import vttp.nus.day24workshop.service.OrderService;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;


    @GetMapping("/order")
    public String getIndex(Model model, HttpSession session) {
        Order order = getOrder(session);
        session.setAttribute("order", order);
        model.addAttribute("order", order);
        return "index";
    }

    public Order getOrder(HttpSession session) {
        Object object = session.getAttribute("order");
        Order order;
        if (object == null) {
            order = new Order();
            session.setAttribute("order", order);
        } else {
            order = (Order) object;
        }
        return order;
    }

    @PostMapping("/order")
    public String postOrder(HttpSession session, 
                        @ModelAttribute Order inputOrder, 
                        @RequestBody MultiValueMap<String, String> form,
                        Model model) {
        
        Order order = getOrder(session);
        order.setName(inputOrder.getName());
        order.setAddress(inputOrder.getAddress());
        order.setNotes(inputOrder.getNotes());

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduct(form.getFirst("product"));
        orderDetail.setQuantity(Integer.parseInt(form.getFirst("quantity")));
        order.getOrderDetails().add(orderDetail);

        model.addAttribute("order", order);

        return "index";
    }

    @PostMapping("/checkout")
    public String postCheckout(HttpSession session, Model model) {
        Order order = getOrder(session);

        if (!orderService.createOrder(order)) {
            model.addAttribute("order", order);
            return "index";
        } else {
            session.invalidate();
            model.addAttribute("order", new Order());
            return "redirect:/";
        }
    }


}
