package designpatterns.CompositePattern.filesystem;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystem {

    String dirName;
    List<FileSystem> componentsList = new ArrayList<>();

    public Directory(String dirName) {
        this.dirName = dirName;
    }

    public void add(FileSystem fileSystem) {
        componentsList.add(fileSystem);
    }

    @Override
    public void ls() {
        for(FileSystem fileSystem : componentsList) {
            fileSystem.ls();
        }
    }
}
