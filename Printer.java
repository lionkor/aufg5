class Printer implements Visitor {
    private String dir = "";
    private String root = null;

    @Override
    public void visitDirectory(String name, Directory directory) {
        if (root == null) {
            root = name + "/";
        }
        dir += name + "/";
        System.out.println (directory.getLastModified () + " " + dir);
    }

    @Override
    public void visitFile(String name, File file) {
        System.out.println (file.getLastModified () + " " + dir + name);
        System.out.println ("> " + file.readContent ());
    }

    @Override
    public void visitedDirectory() {
        dir = root;
    }

    public Printer () {
    }
}