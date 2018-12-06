class File extends Node {
    private String content;

    public File (String content) {
        this.content = content;
    }

    public String readContent () {
        return content;
    }

    public void writeContent (String content) {
        this.content = content;
        touch ();
    }
}