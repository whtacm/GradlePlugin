package org.example.greeting;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputDirectory;
import org.gradle.api.tasks.Nested;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

/**
 * Created by robin on 2/27/18.
 */

public class WriteBookTask extends DefaultTask {
    private File targetDir;
    private File srcDir = new File("./");
    private boolean clearAll = false;
    private boolean generateAll = true;
    private BookManagerExtension bookManagerExtension;

    @Input
    public File getSrcDir() {
        return srcDir;
    }

    @OutputDirectory
    public File getTargetDir() {
        return targetDir;
    }

    public void setTargetDir(File targetDir) {
        this.targetDir = targetDir;
    }

    @Nested
    public BookManagerExtension getBookManagerExtension() {
        return bookManagerExtension;
    }

    public void setBookManagerExtension(BookManagerExtension bookManagerExtension) {
        this.bookManagerExtension = bookManagerExtension;
    }

    @Input
    public boolean isClearAll() {
        return clearAll;
    }

    public void setClearAll(boolean clearAll) {
        this.clearAll = clearAll;
    }

    @Input
    public boolean isGenerateAll() {
        return generateAll;
    }

    public void setGenerateAll(boolean generateAll) {
        this.generateAll = generateAll;
    }

    @TaskAction
    public void write() {
        System.out.printf("WriteBookTask::write-->>path: %s\n", targetDir.getAbsoluteFile());
        if (clearAll) {
            try {
                FileUtils.delAllFile(targetDir.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!generateAll) {
            return;
        }

        SortedSet<String> librariesName = bookManagerExtension.getLibraries().getNames();
        SortedMap<String, Book> bookSortedMap = bookManagerExtension.getBooks().getAsMap();

        if (librariesName.isEmpty() || bookSortedMap.isEmpty()) {
            return;
        }

        Set<String> names = new HashSet<>(librariesName);
        names.forEach(item -> {
            System.out.printf("WriteBookTask::write-->>libname: %s \n", item);
        });

        Map<String, List<Book>> cat = new HashMap<>();
        Iterator<Map.Entry<String, Book>> iterator = bookSortedMap.entrySet().iterator();
        for (; iterator.hasNext(); ) {
            Map.Entry<String, Book> entry = iterator.next();
            String libname = entry.getValue().getBookLocation().getName();
            System.out.printf("WriteBookTask::write-->>book: %s, location: %s\n", entry.getKey(), libname);
            if (names.contains(libname)) {
                if (!cat.containsKey(libname)) {
                    cat.put(libname, new ArrayList<>());
                }
                cat.get(libname).add(entry.getValue());
            }
        }

        Iterator<Map.Entry<String, List<Book>>> iter = cat.entrySet().iterator();
        for (; iter.hasNext(); ) {
            Map.Entry<String, List<Book>> entry = iter.next();
            System.out.printf("WriteBookTask::write-->>libname: %s, booksize: %d\n", entry.getKey(), entry.getValue().size());

            File libDir = new File(targetDir, entry.getKey());
            if (!libDir.exists()) {
                libDir.mkdir();
            }

            entry.getValue().forEach(book -> {
                File bookFile = new File(libDir, book.getName() + ".txt");
                if (!bookFile.exists()) {
                    try {
                        bookFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }
}
