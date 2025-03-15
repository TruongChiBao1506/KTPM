package iuh.fit.se.bai1;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileComponent{
    private String name;
    private List<FileComponent> children;
    private String path;

    public Directory(String name, String path) {
        this.name = name;
        this.path = path;
        this.children = new ArrayList<>();
    }

    public void add(FileComponent component) {
        children.add(component);
    }

    public void remove(FileComponent component) {
        children.remove(component);
    }

    public FileComponent getChild(int index) {
        return children.get(index);
    }

    public List<FileComponent> getChildren() {
        return children;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        for (FileComponent component : children) {
            totalSize += component.getSize();
        }
        return totalSize;
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

        System.out.println(indent + "Directory: " + name + " [Path: " + path + ", Total Size: " + getSize() + " bytes]");

        // Display children with increased indent level
        for (FileComponent component : children) {
            component.display(indentLevel + 1);
        }
    }
}
