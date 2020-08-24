package com.gudnig.vesseldemo.position;

import java.time.ZonedDateTime;

public class PositionFormatRequest {
    
    public PositionFormatRequest() {
        this.vessel = new Vessel();
        this.position = new Position();
    }

    public class Vessel {
    
        public String name;
        public String country;
    }

    public class Position {
        public ZonedDateTime date;
        public Double latitude;
        public Double longitude;
        public Double speed;
    }

    public Vessel vessel;
    public Position position;
}

