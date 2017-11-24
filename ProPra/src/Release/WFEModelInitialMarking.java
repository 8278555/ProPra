package Release;

public class WFEModelInitialMarking {
    WFEModelToken token;
    public WFEModelInitialMarking() {
        token = new WFEModelToken();
    }
    public void settoken(String value) {
        this.token.setvalue(value);
    }
    public String gettoken() {
        return this.token.getvalue();
    }
}
