package com.fithsemproject.cs.teachersassistant;


public class sItem {
    public String img;
    public String title;
    public String content;
    public int id;
    public String Department;
    public String date;
    public String link;


    public String getDepartment() {
        return img;
    }

    public void setDepartment(String Department){this.Department=Department; }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(){this.date=date;}

    public String getLink() {
        return link;
    }

    public void setLink(){this.link=link;}

}

