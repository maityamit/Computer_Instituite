package growOnlearningInstituteitaberia.example.growonlearninginstitute;

public class CourseModel {

    private String Text,Image,Price,Duration;

    public CourseModel()
    {

    }

    public CourseModel(String Text, String Image,String Price,String Duration) {
        this.Text = Text;
        this.Image = Image;
        this.Price = Price;
        this.Duration = Duration;



    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }
}
