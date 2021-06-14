package growOnlearningInstituteitaberia.example.growonlearninginstitute;

public class FAQModel {

    private String qn,an;

    public FAQModel()
    {

    }

    public FAQModel(String qn, String an) {
        this.qn = qn;
        this.an = an;



    }

    public String getQn() {
        return qn;
    }

    public void setQn(String qn) {
        this.qn = qn;
    }

    public String getAn() {
        return an;
    }

    public void setAn(String an) {
        this.an = an;
    }
}
