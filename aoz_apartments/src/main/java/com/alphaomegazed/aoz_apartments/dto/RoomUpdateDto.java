package com.alphaomegazed.aoz_apartments.dto;

public class RoomUpdateDto {

    private String roomType;
    private Integer roomCount;

    // Getters and setters
    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }
}
