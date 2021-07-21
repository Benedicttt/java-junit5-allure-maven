package model.dto;

public class ContractType {
    public Integer id;
    public String name;
    public Number vat_rate;
    public String type;

    public ContractType(Integer id, String name, Number vat_rate, String type) {
        this.id = id;
        this.name = name;
        this.vat_rate = vat_rate;
        this.type = type;
    }

//    public void setId(Integer id) { this.id = id; }
//    public void setName(String name) { this.name = name; }
//    public void setVatRate(BigDecimal vat_rate) { this.vat_rate = vat_rate; }
//    public void setType(String type) { this.type = type; }
//
//    public Integer getId() { return this.id; }
//    public String getName() { return this.name; }
//    public Number getVatRate() { return this.vat_rate; }
//    public String getType() { return this.type; }
}
