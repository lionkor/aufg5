class Directory extends Node {
    private Entry[] entries;

    private Entry addEntry (Entry e) {
        // copy entries to new array with length + 1
        Entry[] newEntries = new Entry[entries.length + 1];
        for (int i = 0; i < entries.length; i++) {
            newEntries[i] = entries[i];
        }
        // add new entry at last index
        newEntries[entries.length] = e;
        // replace old entries with new entries
        entries = newEntries;
        return e;
    }

    public Directory () {
        entries = new Entry [0];
    }

    public Entry[] getEntries () {
        return entries;
    }

    public boolean containsEntry (Entry entry) {
        for (Entry e : entries) {
            if (e == entry) {
                return true;
            }
        }
        return false;
    }

    public Entry getEntry (String name) {
        for (Entry e : entries) {
            if (e.getName () == name) {
                return e;
            }
        }
        // nothing found
        return null;
    }

    public Entry createHardlink (String name, Entry entry) {
        if (getEntry (name) != null) {
            System.out.println ("Error: Entry with name \"" + name + "\" already exists.");
            return null;
        }
        Entry e = entry.createHardlink (name);
        return addEntry (e);
    }

    public Entry createFile (String name, String content) {
        if (getEntry (name) != null) {
            System.out.println ("Error: Entry with name \"" + name + "\" already exists.");
            return null;
        }
        File file = new File (content);
        Entry entry = new Entry (name, file);
        return addEntry (entry);
    }

    public Entry createDirectory (String name) {
        if (getEntry (name) != null) {
            System.out.println ("Error: Entry with name \"" + name + "\" already exists.");
            return null;
        }
        Directory dir = new Directory ();
        Entry entry = new Entry (name, dir);
        return addEntry (entry);
    }

    public void accept (String name, Visitor visitor) {
        visitor.visitDirectory (name, this);
        for (Entry e : entries) {
            if (e.getAsFile () != null) {
                visitor.visitFile (e.getName (), e.getAsFile ());
            }
            else if (e.getAsDirectory () != null) {
                e.getAsDirectory ().accept (e.getName (), visitor);
            }
        }
        visitor.visitedDirectory ();
    }

    public static Directory createEmpty () {
        return new Directory ();
    }

}