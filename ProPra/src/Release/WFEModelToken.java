package Release;

public class WFEModelToken {
    WFEModelValue value;
    
    public WFEModelToken() {
    	value = new WFEModelValue();
    }
    
    public void setvalue(String value) {
        this.value.setvalue(value);
    }
    public String getvalue() {
        return this.value.getvalue();
    }
}
