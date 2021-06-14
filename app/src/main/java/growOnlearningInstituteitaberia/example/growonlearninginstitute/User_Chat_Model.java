package growOnlearningInstituteitaberia.example.growonlearninginstitute;

public class User_Chat_Model {


    private String Name,Classes,Image;

    public User_Chat_Model()
    {

    }

    public User_Chat_Model(String Name, String Image,String Classes) {
        this.Name = Name;
        this.Image = Image;
        this.Classes = Classes;



    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }



    public String getClasses() {
        return Classes;
    }

    public void setClasses(String classes) {
        Classes = classes;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
