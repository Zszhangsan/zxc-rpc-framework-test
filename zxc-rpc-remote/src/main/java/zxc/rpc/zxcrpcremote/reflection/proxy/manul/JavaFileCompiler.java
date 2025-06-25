package zxc.rpc.zxcrpcremote.reflection.proxy.manul;


import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class JavaFileCompiler {

    /**
     * 编译单个 Java 文件
     *
     * @param javaFilePath Java 文件路径
     * @return 编译是否成功
     */
    public static boolean compileFile(String javaFilePath) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            System.err.println("JDK 编译器不可用。请确保使用 JDK 而不是 JRE 运行此程序。");
            return false;
        }

        int result = compiler.run(null, null, null, javaFilePath);
        return result == 0;
    }

    /**
     * 编译目录下的所有 Java 文件
     *
     * @param directoryPath 目录路径
     * @return 编译是否成功
     */
    public static boolean compileDirectory(String directoryPath) {
        List<String> javaFiles = findJavaFiles(directoryPath);
        if (javaFiles.isEmpty()) {
            System.out.println("在目录 " + directoryPath + " 中未找到 Java 文件。");
            return false;
        }

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            System.err.println("JDK 编译器不可用。请确保使用 JDK 而不是 JRE 运行此程序。");
            return false;
        }

        int result = compiler.run(null, null, null, javaFiles.toArray(new String[0]));
        return result == 0;
    }

    /**
     * 递归查找目录中的所有 Java 文件
     *
     * @param directoryPath 目录路径
     * @return Java 文件路径列表
     */
    private static List<String> findJavaFiles(String directoryPath) {
        List<String> javaFiles = new ArrayList<>();
        Path startPath = Paths.get(directoryPath);

        try {
            Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    if (file.toString().endsWith(".java")) {
                        javaFiles.add(file.toString());
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    System.err.println("无法访问文件: " + file + " (" + exc + ")");
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            System.err.println("遍历目录时出错: " + e.getMessage());
        }

        return javaFiles;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("用法: JavaFileCompiler <Java文件路径或目录>");
            System.out.println("示例:");
            System.out.println("  JavaFileCompiler HelloWorld.java");
            System.out.println("  JavaFileCompiler src/");
            return;
        }

        String path = args[0];
        File file = new File(path);

        boolean success;
        if (file.isDirectory()) {
            System.out.println("正在编译目录: " + path);
            success = compileDirectory(path);
        } else if (file.isFile() && path.endsWith(".java")) {
            System.out.println("正在编译文件: " + path);
            success = compileFile(path);
        } else {
            System.err.println("无效的路径或文件类型: " + path);
            return;
        }

        if (success) {
            System.out.println("编译成功！");
        } else {
            System.err.println("编译失败！");
        }
    }
}