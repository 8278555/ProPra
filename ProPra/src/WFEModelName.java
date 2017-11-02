
public class WFEModelName {
    WFEModelValue value;
    
    WFEModelName() {
        value = new WFEModelValue();
    }
    
    public void setname(String value) {
        this.value.setvalue(value);
    }
    public String getname() {
        return value.getvalue();
    }
}
