class Entry {
    private String name;
    private Node   node;

    public String getName () {
        return name;
    }

    public Entry createHardlink (String newName) {
        return new Entry (newName, node);
    }

    public Directory getAsDirectory () {
        if (node instanceof Directory) {
            return (Directory) node;
        } else {
            return null;
        }
    }

    public File getAsFile () {
        if (node instanceof File) {
            return (File) node;
        } else {
            return null;
        }
    }

    public Entry (String name, Node node) {
        this.name = name;
        this.node = node;
    }
}