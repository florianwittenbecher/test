package videoshop.controller;

import videoshop.model.Disc;
import videoshop.model.Wishlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.salespointframework.catalog.Product;
import org.salespointframework.core.AbstractEntity;
import org.salespointframework.order.Cart;
import org.salespointframework.order.Order;
import org.salespointframework.order.OrderManager;
import org.salespointframework.payment.Cash;
import org.salespointframework.quantity.Quantity;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.web.LoggedIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@PreAuthorize("isAuthenticated()")
@SessionAttributes("wishlist")

class WishlistController {

	//@ModelAttribute("wishlist")
	Wishlist wishlist = new Wishlist();
	
	@RequestMapping(value = "/wishlist", method = RequestMethod.GET)
	public String desire() {
		return "wishlist";
	}
	
	@RequestMapping(value = "/wishlist", method = RequestMethod.POST)
	public String addDisc(@RequestParam("pid") Disc disc, Wishlist wishlist) {
		//System.out.println(wishlist);
		wishlist.setWishlist(disc.getName());
		// Je nachdem ob disc eine Dvd oder eine Bluray ist, leiten wir auf die richtige Seite weiter
		//System.out.println(wishlist.getWishlist(0));
		System.out.println(wishlist.lengthWishlist());
		switch (disc.getType()) {
			case DVD:
				return "redirect:dvdCatalog";
			case BLURAY:
			default:
				return "redirect:blurayCatalog";
		}
	}
}

