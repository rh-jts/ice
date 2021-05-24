package ajax;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ices {
	public int container;
	public List<List<String>> ice;
	public int icenum;
	public boolean is_active;
	public int price;
	public int product_num;
	public int quantity;
	public int getContainer() {
		return container;
	}
	public void setContainer(int container) {
		this.container = container;
	}
	public List<List<String>> getIce() {
		return ice;
	}
	public void setIce(List<List<String>> ice) {
		this.ice = ice;
	}
	public int getIcenum() {
		return icenum;
	}
	public void setIcenum(int icenum) {
		this.icenum = icenum;
	}
	public boolean isIs_active() {
		return is_active;
	}
	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
