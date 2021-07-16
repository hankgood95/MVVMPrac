package wook.co.mvvm_prac.models;

public class NicePlace {

    private String title; //사진 제목을 담을 변수
    private String imageUrl; // imageurl을 담을 변수

    public NicePlace() {}

    public NicePlace(String imageUrl,String title) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "NicePlace{" + "title='" + title + '\'' + ", imageUrl='" + imageUrl + '\'' + '}';
    }
}
