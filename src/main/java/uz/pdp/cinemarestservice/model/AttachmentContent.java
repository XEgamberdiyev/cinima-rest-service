package uz.pdp.cinemarestservice.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.cinemarestservice.model.abcClass.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "attachment_controller")
public class AttachmentContent extends AbsEntity {

    private byte[] bytes;

    @OneToOne
    private Attachment attachment;

    public AttachmentContent(byte[] data, Attachment attachment) {
        this.bytes = data;
        this.attachment = attachment;
    }
}
