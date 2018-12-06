abstract class Node {
    private long lastModified;
    public long getLastModified () {
        return lastModified;
    }
    public void touch () {
        lastModified = System.currentTimeMillis ();
    }
    public Node () {
        touch ();
    }
}