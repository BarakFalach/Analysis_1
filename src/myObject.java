public abstract class myObject {
    public String showShortObject(){
        return String.format("id: %s, name: %s ", getId(), getClass().getSimpleName());
    }
    public String getClassName(){return getClass().getSimpleName();};
    public abstract String getId();
    public abstract void deleteObject();
}
