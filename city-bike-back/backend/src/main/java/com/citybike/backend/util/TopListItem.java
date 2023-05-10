package com.citybike.backend.util;

public class TopListItem {
    public int id;
    public int count;

    public TopListItem(int id, int count) {
        this.id = id;
        this.count = count;
    }

    public boolean equals(Object obj){
        TopListItem item = (TopListItem) obj;
        boolean status = false;
        if(this.id == item.id && this.count == item.count) {
            status = true;
        }
        return status;
    }

    public String toString() {
        return this.id + " : " + this.count;
    }
}
