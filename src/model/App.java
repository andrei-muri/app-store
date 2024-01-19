package model;

public record App(int appId, String name, double size, String sizeType, String details, Categories category, String img) {
    public boolean isDownloaded() {
        for(App app : User.usersApps) {
            if(app.appId == this.appId) {
                return true;
            }
        }
        return false;
    }
}
