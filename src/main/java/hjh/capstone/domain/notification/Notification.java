package hjh.capstone.domain.notification;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Notification
{
    private String title;
    private String body;
    private String icon;

    public Notification(String title, String body, String icon)
    {
        this.title = title;
        this.body = body;
        this.icon = icon;
    }
}
