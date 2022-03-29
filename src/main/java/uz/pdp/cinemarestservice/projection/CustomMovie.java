package uz.pdp.cinemarestservice.projection;

import java.sql.Date;

public interface CustomMovie {
    Integer getId();

    String getTitle();

    Date getReleaseDate();

   Integer getCoverImgId();
}
