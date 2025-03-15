package iuh.fit.se.bai1;

public class File implements FileComponent{
    private String name;
    private int size;
    private String path;

    public File(String name, int size, String path) {
        this.name = name;
        this.size = size;
        this.path = path;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void display(int indentLevel) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < indentLevel; i++) {
            indent.append("    ");  // 4 spaces for each level
        }
        System.out.println(indent + "File: " + name + " [Size: " + size + " bytes, Path: " + path + "]");
    }
}
