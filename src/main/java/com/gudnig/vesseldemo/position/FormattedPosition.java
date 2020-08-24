package com.gudnig.vesseldemo.position;

import java.time.ZonedDateTime;

public class FormattedPosition {

    public class Position {
        public ZonedDateTime date;
        public ZonedDateTime receivedDate;
        public Double latitude;
        public Double longitude;
        public Double speed;
    }

    public class Vessel {
        public String name;
    }
    
    public Vessel vessel;
    public Position position;

    public FormattedPosition() {
        vessel = new Vessel();
        position = new Position();
    }
}