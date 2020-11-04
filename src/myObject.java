public abstract class myObject {
    public String showShortObject(){
        return String.format("id: %s, name: %s ", getId(), getClass().getSimpleName());
    }
    protected abstract String getId();
    public abstract String showFullObject();
}
