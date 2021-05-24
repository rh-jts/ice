package packw;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonObject {
	public List<Ices> ices;


	public List<Ices> getIces() {
		return ices;
	}

	public void setIces(List<Ices> ices) {
		this.ices = ices;
	}

}
