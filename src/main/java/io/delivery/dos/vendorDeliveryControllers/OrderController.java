package io.delivery.dos.vendorDeliveryControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.delivery.dos.models.address.Address;
import io.delivery.dos.models.address.request.AddressRequestObject;
import io.delivery.dos.models.menu.Menu;
import io.delivery.dos.models.order.request.OrderItem;
import io.delivery.dos.models.order.request.OrderRequest;
import io.delivery.dos.models.order.response.OrderResponse;
import io.delivery.dos.repositories.address.AddressRepository;
import io.delivery.dos.repositories.menu.MenuRepository;
import io.delivery.dos.repositories.order.OrderRepository;
import io.delivery.dos.security.models.AuthenticationResponse;
import io.delivery.dos.security.util.JwtUtil;
import io.delivery.dos.models.order.Orders;

@RestController
public class OrderController {

	@Autowired
    private JwtUtil jwtUtil;

	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	MenuRepository menuRepository;
	
	static final String awaiting_confirmation = "AWAITING_CONFIRMATION";
	
	@RequestMapping(method=RequestMethod.POST,value="/order")
	public ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequestObject,@RequestHeader (name="Authorization") String authorizationHeader) throws Exception {
		String jwt = authorizationHeader.substring(7);
        String userid = jwtUtil.extractUsername(jwt);
        float computedAmount = 0;
        boolean isAddressValid = validateAddress(userid, orderRequestObject.getAddressid());
     
        if(!isAddressValid) {
        	throw new Exception(orderRequestObject.getAddressid()+" Address Id Invalid,Please Refresh and Try Again");
        }
        
        for (OrderItem item : orderRequestObject.getOrderlist()) {
        	
        	Float itemAmount = getItemAmountWithVendor(item.getItemid(),orderRequestObject.getVendorid());
        	if(itemAmount==null) {
        		throw new Exception(item.getItemid()+" Itemid not Found,Please Refresh and Try Again");
        	}
			computedAmount +=  (itemAmount*item.getQty());
		}
        
        Orders placedOrder ;
        if(computedAmount==orderRequestObject.getTotalamount() && isAddressValid) {
        //All Good 	Insert into order
        	
        	Orders order = new Orders(null,userid,orderRequestObject.getVendorid(),orderRequestObject.getAddressid(),orderRequestObject.getOrderlist().toString(),computedAmount);
        	placedOrder = orderRepository.save(order);
        }
        else {
        	// yo amount prob throw error
        	throw new Exception("Amount Mismatch,Please Refresh and Try Again");
        }
        // total amount , status
        return ResponseEntity.ok(new OrderResponse(awaiting_confirmation,placedOrder.getOrderid()));
	}
	
	private boolean validateAddress(String userid,int addressid) {
		//need address repository
		Address address = addressRepository.findOneByUseridAndAddressid(userid, addressid);
		if(address!=null) {
			return true;
		}
		return false;
	}
	
	private Float getItemAmountWithVendor(int itemid,int vendorid) throws Exception{
		
		Menu item=menuRepository.findByVendoridAndItemid(vendorid, itemid);
		if(item!=null) {
			return item.getAmount();
		}
		//return amount
		return null;
	}
	
}
