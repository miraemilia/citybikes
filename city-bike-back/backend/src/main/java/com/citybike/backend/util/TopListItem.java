package com.citybike.backend.util;

public class TopListItem {
    public int id;
    public String name;
    public int count;

    public TopListItem(int id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public boolean equals(Object obj){
        TopListItem item = (TopListItem) obj;
        boolean status = false;
        if(this.id == item.id && this.name.equals(item.name) && this.count == item.count) {
            status = true;
        }
        return status;
    }

    public String toString() {
        return this.id + ", " + this.name + " : " + this.count;
    }
}
