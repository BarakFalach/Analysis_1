public abstract class myObject {
    public String showShortObject(){
        return String.format("id: %s, name: %s ,class name: %s ", getId(), getName() , getClass().getSimpleName());
    }
    public String getClassName(){return getClass().getSimpleName();};
    public abstract String getId();
    public abstract String getName();
    public abstract void deleteObject();
}
