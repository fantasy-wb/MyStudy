package fantasy.StaticProxy.Retrofit;

//更新实体类
public class CheckUpdate {

    private boolean hasUpdate;
    private String newVersion;

    public boolean isHasUpdate() {
        return hasUpdate;
    }

    public void setHasUpdate(boolean hasUpdate) {
        this.hasUpdate = hasUpdate;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public void setNewVersion(String newVersion) {
        this.newVersion = newVersion;
    }

    @Override
    public String toString() {
        return "Has update : " + hasUpdate + " ; The newest version is : " + newVersion;
    }
}