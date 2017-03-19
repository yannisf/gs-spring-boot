package fraglab.jersey.xml;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "messageType", propOrder = {
        "date",
        "type",
        "content",
        "hash"
})
@XmlRootElement(name = "message")
public class Message {

    @XmlElement(required = true)
    protected Date date;

    @XmlElement(required = true)
    protected String type;

    @XmlElement(required = true)
    protected String content;

    @XmlAttribute(required = false)
    protected String hash;

    public Message() {
        date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type='" + type + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
