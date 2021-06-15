package growOnlearningInstituteitaberia.example.growonlearninginstitute;

public class Contact {

    private String Text,Sender,SenderDate,ReceiverDate,Image,Name;

    public Contact()
    {

    }

    public Contact(String TEXT, String SENDER, String SenderDate, String ReceiverDate,String Image,String Name) {
        this.Text = TEXT;
        this.Sender = SENDER;
        this.SenderDate = SenderDate;
        this.ReceiverDate = ReceiverDate;
        this.Image = Image;
        this.Name = Name;



    }


    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getSenderDate() {
        return SenderDate;
    }

    public void setSenderDate(String senderDate) {
        SenderDate = senderDate;
    }

    public String getReceiverDate() {
        return ReceiverDate;
    }

    public void setReceiverDate(String receiverDate) {
        ReceiverDate = receiverDate;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
