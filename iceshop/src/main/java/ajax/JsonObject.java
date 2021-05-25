package ajax;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonObject {
	public List<Ices> ices;
	public List<Integer> tax_discounts;
	public int total;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Integer> getTax_discounts() {
		return tax_discounts;
	}

	public void setTax_discounts(List<Integer> tax_discounts) {
		this.tax_discounts = tax_discounts;
	}

	public List<Ices> getIces() {
		return ices;
	}

	public void setIces(List<Ices> ices) {
		this.ices = ices;
	}

}
