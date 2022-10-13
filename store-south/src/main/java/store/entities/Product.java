package store.entities;

import static store.validations.Validates.validateDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;;

public class Product {

	private String id;

	private String barCode;

	private String description;

	private String name;

	private String category;

	private BigDecimal price;

	private Integer amount;

	private String color;

	private String material;

	private LocalDate fabricationDate;

	private LocalDate dateValidity;

	private String serialNumber;

	public Product() {

	}

	public Product(String id, String barCode, String description, String name, String category, BigDecimal price,
			Integer amount, String color, String material, LocalDate fabricationDate, LocalDate dateValidity,
			String serialnumber) {
		this.id = id;
		this.barCode = barCode;
		this.description = description;
		this.name = name;
		this.category = category;
		this.price = price;
		this.amount = amount;
		this.color = color;
		this.material = material;
		this.fabricationDate = fabricationDate;
		this.dateValidity = dateValidity;
		this.serialNumber = serialnumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public LocalDate getFabricationDate() {
		return fabricationDate;
	}

	public void setFabricationDate(LocalDate fabricationDate) {
		this.fabricationDate = fabricationDate;
	}

	public LocalDate getDateValidity() {
		return dateValidity;
	}

	public void setDateValidity(LocalDate dateValidity) {
		this.dateValidity = dateValidity;
	}

	public String getSerialnumber() {
		return serialNumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialNumber = serialnumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "\nID PRODUTO: " + id + ",\nCODIGO: " + barCode + "\nSÉRIE: " + this.serialNumber + ",\nNOME: " + name
				+ ",\nDESCRIÇÃO: " + description + ",\nCATEGORIA: " + category + ",\nVALOR: R$" + price
				+ ",\nQUANTIDADE: " + amount + ",\nCOR: " + color + ",\nMATERIAL: " + material
				+ ",\nDATA DE FABRICAÇÃO: " + validateDate(fabricationDate) + ",\nDATA DE VÁLIDADE: "
				+ validateDate(dateValidity) + "\n";
	}

	public String toStringFile() {
		return this.id + "," + this.barCode + "," + this.serialNumber + "," + this.name + "," + this.description + ","
				+ this.category + "," + this.price + "," + validateDate(fabricationDate) + ","
				+ validateDate(dateValidity) + "," + this.color + "," + this.material + "," + this.amount;
	}

}
